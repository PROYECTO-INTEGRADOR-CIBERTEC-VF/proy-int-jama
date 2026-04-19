package com.cibertec.jama.repositories.usuario;

import com.cibertec.jama.entities.usuario.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

boolean existsByloginidAndPassword(String loginid, String password);
}
