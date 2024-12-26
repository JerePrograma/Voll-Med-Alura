package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DatosListaConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime fecha) {

    public DatosListaConsulta(Consulta consulta) {
        this(consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getFecha());
    }
}
