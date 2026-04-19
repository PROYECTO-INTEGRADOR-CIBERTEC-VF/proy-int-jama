package com.cibertec.jama.repositories.usuario;

import com.cibertec.jama.entities.usuario.UserData;
import com.cibertec.jama.entities.usuario.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository  extends JpaRepository<UserData, Integer> {

    UserData findByUsers(String users);
}
