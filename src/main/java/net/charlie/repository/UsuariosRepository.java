package net.charlie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.charlie.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
