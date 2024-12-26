package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.domain.consulta.validaciones.ValidadorHorarioConAnticipacion;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private med.voll.api.domain.medico.MedicoRepository MedicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private med.voll.api.domain.consulta.ConsultaRepository consultaRepository;

    @Autowired
    private med.voll.api.domain.medico.MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorDeConsultas> validadores;

    @Autowired
    private List<ValidadorHorarioConAnticipacion> validadoresCancelamiento;

    public DatosDetalleConsulta reservar(DatosReservaConsulta datos) {

        if (!pacienteRepository.existsById(datos.idPaciente())) {
            throw new ValidacionException("No existe un paciente con el id informado");
        }
        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionException("No existe un médico con el id informado");
        }

        //validaciones
        validadores.forEach(v -> v.validar(datos));

        var medico = elegirMedico(datos);
        if (medico == null) {
            throw new ValidacionException("No existe un médico disponible en ese horario");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionException("¡El Id informado de la consulta no existe!");
        }

        validadoresCancelamiento.forEach(v -> v.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.setMotivoCancelamiento(datos.motivo());
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {
        if (datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null) {
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elige un médico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
    }
}
