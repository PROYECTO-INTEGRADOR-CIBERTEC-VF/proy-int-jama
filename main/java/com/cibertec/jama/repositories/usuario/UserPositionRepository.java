package com.cibertec.jama.repositories.usuario;

import com.cibertec.jama.entities.usuario.UserPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserPositionRepository  extends JpaRepository<UserPosition, Integer> {
}
