package com.example.reservas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.reservas.domain.Reservas;
import com.example.reservas.repository.ReservasRepository;
import com.example.reservas.service.exceptions.ObjectNotFoundException;

@SpringBootTest
public class ReservasServiceTest {
    
    @Mock
    private ReservasRepository reservaRepository;

    @InjectMocks
    private ReservasService reservaService;

    @Test
    public void testInsertReserva() throws ParseException{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Reservas reserva = new Reservas(1, "Maria da Silva", sdf.parse("2023-10-20"), sdf.parse("2023-11-20"), 5, "CONFIRMADA");
        
        when(reservaRepository.save(any(Reservas.class))).thenReturn(reserva);

        Reservas reservaCriada = reservaService.create(reserva);

        assertNotNull(reservaCriada);
        assertEquals(reserva.getNomeHospede(), reservaCriada.getNomeHospede());
        assertEquals(reserva.getDataInicio(), reservaCriada.getDataInicio());
        assertEquals(reserva.getDataFim(), reservaCriada.getDataFim());
        assertEquals(reserva.getQuantidadePessoas(), reservaCriada.getQuantidadePessoas());
        assertEquals(reserva.getStatus(), reservaCriada.getStatus());
        assertNull(reservaCriada.getId());

        verify(reservaRepository, times(1)).save(any(Reservas.class));
        
    }

    @Test
    public void testFindReservaByIdExistente() throws ParseException{
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Reservas reserva = new Reservas(1, "Maria da Silva", sdf.parse("2023-10-20"), sdf.parse("2023-11-20"), 5, "CONFIRMADA");
        
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));

        Reservas reservaEncontrada = reservaService.findById(1);

        assertNotNull(reservaEncontrada);
        assertEquals(reserva.getNomeHospede(), reservaEncontrada.getNomeHospede());
        assertEquals(reserva.getDataInicio(), reservaEncontrada.getDataInicio());
        assertEquals(reserva.getDataFim(), reservaEncontrada.getDataFim());
        assertEquals(reserva.getQuantidadePessoas(), reservaEncontrada.getQuantidadePessoas());
        assertEquals(reserva.getStatus(), reservaEncontrada.getStatus());

        verify(reservaRepository, times(1)).findById(1);
    }

    @Test
    public void testFindReservaByIdNaoExistente(){
        Integer reservaId = 10;

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> reservaService.findById(reservaId));

        verify(reservaRepository, times(1)).findById(reservaId);
    }

    @Test
    public void testUpdateReservaStatusParaCancelado() throws ParseException{

        String statusCancelado = "CANCELADO";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Reservas reserva = new Reservas(1, "Maria da Silva", sdf.parse("2023-10-20"), sdf.parse("2023-11-20"), 5, "CONFIRMADA");

        when(reservaRepository.findById(1)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reservas.class))).thenReturn(reserva);

        Reservas reservaCancelada = reservaService.updateStatusCancelado(1);

        assertEquals(statusCancelado, reservaCancelada.getStatus());

        verify(reservaRepository, times(1)).findById(1);
        verify(reservaRepository, times(1)).save(any(Reservas.class));

    }

    @Test
    public void testUpdateReserva() throws ParseException{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Reservas reservaExistente = new Reservas(1, "Maria da Silva", sdf.parse("2023-10-20"), sdf.parse("2023-11-20"), 5, "CONFIRMADA");

        Reservas reservaAtualizada = new Reservas(1, "João da Silva", sdf.parse("2023-10-20"), sdf.parse("2023-11-20"), 5, "PENDENTE");

        when(reservaRepository.findById(1)).thenReturn(Optional.of(reservaExistente));
        when(reservaRepository.save(any(Reservas.class))).thenReturn(reservaAtualizada);

        Reservas resultado = reservaService.update(1, reservaAtualizada);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals(reservaAtualizada.getNomeHospede(), resultado.getNomeHospede());
        assertEquals(reservaAtualizada.getDataInicio(), resultado.getDataInicio());
        assertEquals(reservaAtualizada.getDataFim(), resultado.getDataFim());
        assertEquals(reservaAtualizada.getQuantidadePessoas(), resultado.getQuantidadePessoas());
        assertEquals(reservaAtualizada.getStatus(), resultado.getStatus());
        

        verify(reservaRepository, times(1)).findById(1);
        verify(reservaRepository, times(1)).save(any(Reservas.class));
    }

}
