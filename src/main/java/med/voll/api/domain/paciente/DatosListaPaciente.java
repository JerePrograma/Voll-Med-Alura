package med.voll.api.domain.paciente;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosListaPaciente(Long id, String nombre, String email, String documentoIdentidad) {
    public DatosListaPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}