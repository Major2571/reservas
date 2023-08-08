package com.example.reservas.controller;

import com.example.reservas.domain.Reservas;
import com.example.reservas.service.ReservasService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservasController.class)
public class ReservasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservasService reservaService;

    @InjectMocks
    private ReservasControllerTest reservaControllerTest;

    @Test
    public void testInsertReserva() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Reservas reservaInserida = new Reservas(1, "Maria da Silva", sdf.parse("2023-10-20"), sdf.parse("2023-11-20"),
                5, "CONFIRMADA");

        when(reservaService.create(any(Reservas.class))).thenReturn(reservaInserida);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nomeHospede\": \"Fulano\", \"dataInicio\": \"2023-08-10\", "
                        + "\"dataFim\": \"2023-08-15\",\"quantidadePessoas\": 4, "
                        + "\"status\": \"CONFIRMADA\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/reservas/1"));

    }

    @Test
    public void testFindReservaByIdExistente() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Reservas reservaEncontrada = new Reservas(1, "Maria da Silva", sdf.parse("2023-10-20"), sdf.parse("2023-11-20"),
                5, "CONFIRMADA");

        when(reservaService.findById(1)).thenReturn(reservaEncontrada);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeHospede").value("Maria da Silva"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataInicio").value("2023-10-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataFim").value("2023-11-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantidadePessoas").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("CONFIRMADA"));
    }

}
