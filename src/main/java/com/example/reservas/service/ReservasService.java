package com.example.reservas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reservas.domain.Reservas;
import com.example.reservas.repository.ReservasRepository;
import com.example.reservas.service.exceptions.InvalidStatusException;
import com.example.reservas.service.exceptions.ObjectNotFoundException;

@Service
public class ReservasService {

    @Autowired
    private ReservasRepository reservasRepository;

    private static final String STATUS_CANCELADO = "CANCELADO";

    /**
     * Cria uma nova reserva.
     *
     * @param objReserva Reserva a ser criada.
     * @return Reserva criada.
     */
    public Reservas create(Reservas objReserva) {
        objReserva.setId(null);
        objReserva.setStatus("CONFIRMADA");
        return reservasRepository.save(objReserva);
    }

    /**
     * Busca uma reserva por ID.
     *
     * @param id ID da reserva a ser buscada.
     * @return A reserva encontrada.
     * @throws ObjectNotFoundException Se a reserva não for encontrada.
     */
    public Reservas findById(Integer id) {
        Optional<Reservas> reserva = reservasRepository.findById(id);
        return reserva.orElseThrow(() -> new ObjectNotFoundException(
                "Ops! Objeto não encontrado: " + id + ", Tipo: " + Reservas.class.getName()));
    }

    /**
     * Retorna uma lista de todas as reservas.
     *
     * @return Lista de reservas.
     */
    public List<Reservas> listarReservas() {
        return reservasRepository.findAll();
    }

    /**
     * Atualiza o status de uma reserva para "CANCELADO".
     *
     * @param id ID da reserva a ser cancelada.
     * @return A reserva após a atualização do status.
     */
    public Reservas updateStatusCancelado(Integer id) {
        Reservas newObj = findById(id);
        newObj.setStatus(STATUS_CANCELADO);
        return reservasRepository.save(newObj);
    }

    /**
     * Atualiza os detalhes de uma reserva.
     *
     * @param id  ID da reserva a ser atualizada.
     * @param obj Reserva com os detalhes atualizados.
     * @return A reserva após a atualização.
     * @throws InvalidStatusException Se o status informado não for válido.
     */
    public Reservas update(Integer id, Reservas obj) {

        Reservas attObj = findById(id);

        String validarStatus = obj.getStatus().toUpperCase();

        if (STATUS_CANCELADO.equalsIgnoreCase(attObj.getStatus())) {
            throw new InvalidStatusException("As reservas canceladas não podem ser editadas.");
        } else {
            attObj.setStatus(obj.getStatus());
        }

        if (!validarStatus.equals("PENDENTE") && !validarStatus.equals("CONFIRMADA")) {
            throw new InvalidStatusException(
                    "Ops! O status informado não é válido. Utilize 'PENDENTE' ou 'CONFIRMADA' neste campo.");
        }

        attObj.setNomeHospede(obj.getNomeHospede());
        attObj.setDataFim(obj.getDataFim());
        attObj.setDataInicio(obj.getDataInicio());
        attObj.setQuantidadePessoas(obj.getQuantidadePessoas());

        return reservasRepository.save(attObj);
    }

}
