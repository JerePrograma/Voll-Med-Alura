package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank(message = "Nombre es obligatorio")
        String nombre,
        @NotBlank(message = "Email es obligatorio")
        @Email(message = "Formato de email es inválido")
        String email,
        @NotBlank
        String telefono,
        @NotBlank(message = "CRM es obligatorio")
        @Pattern(regexp = "\\d{6,8}", message = "El documento debe tener entre 6 y 8 dígitos")
        String documento,
        @NotNull(message = "Especialidad es obligatorio")
        Especialidad especialidad,
        @NotNull(message = "Datos de dirección son obligatorios")
        @Valid
        DatosDireccion direccion) {}
