package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
//@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@Valid @RequestBody DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = DatosRespuestaMedico.from(medico);
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listarMedicos(@PageableDefault(size = 15, sort = "nombre") Pageable paginacion) {

        // return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(DatosRespuestaMedico.from(medico));
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable Long id) {
//        medicoRepository.deleteById(id);
//    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity darBajaMedico(@PathVariable long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.setActivo(false);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity retornaDatosMedico(@PathVariable long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = DatosRespuestaMedico.from(medico);
        return ResponseEntity.ok(datosMedico);
    }

//    @GetMapping("/{id}")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity detallar(@PathVariable Long id) {
//        var medico = medicoRepository.getReferenceById(id);
//        return ResponseEntity.ok(new DatosListadoMedico(medico));
//    }
}
