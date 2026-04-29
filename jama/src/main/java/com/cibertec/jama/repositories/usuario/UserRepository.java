package com.cibertec.jama.repositories.usuario;

import com.cibertec.jama.entities.usuario.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

boolean existsByloginidAndPassword(String loginid, String password);
Optional<Users> findByLoginid(String username);
}
