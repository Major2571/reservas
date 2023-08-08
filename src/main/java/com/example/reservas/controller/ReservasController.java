package com.example.reservas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.reservas.domain.Reservas;
import com.example.reservas.service.ReservasService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservaService;

    /**
     * Obtém uma lista de todas as reservas.
     *
     * @return ResponseEntity contendo a lista de reservas.
     */
    @GetMapping
    public ResponseEntity<List<Reservas>> listarReservas() {
        List<Reservas> reservasList = reservaService.listarReservas();
        return ResponseEntity.ok().body(reservasList);
    }

    /**
     * Busca a reserva pelo seu ID.
     *
     * @param id O ID da reserva a ser buscada.
     * @return ResponseEntity contendo a reserva encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reservas> findById(@PathVariable Integer id) {
        Reservas objReserva = reservaService.findById(id);
        return ResponseEntity.ok().body(objReserva);
    }

    /**
     * Cria uma nova reserva
     * 
     * @param objReserva Reserva a ser criada.
     * @return ResponseEntity com a resposta da criação da reserva.
     */
    @PostMapping
    public ResponseEntity<Reservas> create(@RequestBody Reservas objReserva) {
        Reservas createReserva = reservaService.create(objReserva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createReserva.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createReserva);
    }

    /**
     * Cancela uma reserva.
     *
     * @param id ID da reserva a ser cancelada.
     * @return ResponseEntity contendo a reserva cancelada.
     */
    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<Reservas> delete(@PathVariable Integer id) {
        Reservas cancelarReserva = reservaService.updateStatusCancelado(id);
        return ResponseEntity.ok().body(cancelarReserva);
    }

    /**
     * Atualiza os detalhes de uma reserva.
     *
     * @param id  ID da reserva a ser atualizada.
     * @param obj Detalhes atualizados da Reserva.
     * @return ResponseEntity contendo a reserva atualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reservas> update(@PathVariable Integer id, @RequestBody Reservas obj) {
        Reservas attObj = reservaService.update(id, obj);
        return ResponseEntity.ok().body(attObj);
    }

}
