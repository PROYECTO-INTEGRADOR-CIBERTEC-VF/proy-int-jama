package com.cibertec.jama.dto.usuario;

import com.cibertec.jama.entities.usuario.UserData;
import com.cibertec.jama.entities.usuario.UserPosition;
import com.cibertec.jama.entities.usuario.UserRol;
import com.cibertec.jama.entities.usuario.Users;

import java.util.List;

public record UsuarioDataDto(
        Users users,
        UserData userData,
        List<UserRol> userRols,
        List<UserPosition> userPositions
) {}

