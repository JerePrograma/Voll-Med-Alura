package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario implements ValidadorDeConsultas  {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos) {
        var medicoTieneConsultaEnElMismoHorario = repository.existsByMedicoIdAndFechaAndMotivoCancelamientoIsNull(datos.idMedico(), datos.fecha());
        if (medicoTieneConsultaEnElMismoHorario) {
            throw new ValidacionException("Medico ya tiene una consulta reservada para esa misma fecha y hora");
        }
    }
}
