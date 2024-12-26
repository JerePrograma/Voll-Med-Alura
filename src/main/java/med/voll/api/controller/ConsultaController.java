package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @Autowired
    private ConsultaRepository repository;

    @GetMapping
    public Page<DatosListaConsulta> listar(Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListaConsulta::new);
    }

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {
        DatosDetalleConsulta consulta = reserva.reservar(datos);
        return ResponseEntity.ok(consulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        reserva.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
