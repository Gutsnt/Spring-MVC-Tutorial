package net.charlie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.charlie.model.Solicitud;

public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
