package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
    select m from Medico m
    where
        m.activo = true
        and m.especialidad = :especialidad
        and not exists (
            select 1 from Consulta c
            where
                c.medico.id = m.id
                and c.fecha = :fecha
                and c.motivoCancelamiento is null
        )
    order by function('rand')
    """)
    Medico elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad especialidad, LocalDateTime fecha);

    @Query("""
                select m.activo from Medico m
                where
                m.id = :idMedico
            """)
    boolean findActivoById(Long idMedico);
}
