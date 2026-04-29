package com.cibertec.jama.service.usuario;

import com.cibertec.jama.dto.usuario.UsuarioDataDto;
import com.cibertec.jama.entities.usuario.UserData;
import com.cibertec.jama.entities.usuario.UserPosition;
import com.cibertec.jama.entities.usuario.UserRol;
import com.cibertec.jama.entities.usuario.Users;
import com.cibertec.jama.repositories.usuario.UserDataRepository;
import com.cibertec.jama.repositories.usuario.UserPositionRepository;
import com.cibertec.jama.repositories.usuario.UserRepository;
import com.cibertec.jama.repositories.usuario.UserRolRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService   {
    private final UserRepository userRepository;

    private final UserRolRepository userRolRepository;
    private final UserPositionRepository userPositionRepository;
    private final UserDataRepository userDataRepository;


    public void initRolTable() {

        var checkingListEmpty = new ArrayList<UserRol>();
        checkingListEmpty = (ArrayList<UserRol>) userRolRepository.findAll();


        if (!checkingListEmpty.isEmpty()) {
            return;
        }

        var rolsList = new ArrayList<String>();
        rolsList.add("owner");
        rolsList.add("admin");
        rolsList.add("invited");

        var rolNewRol = new ArrayList<UserRol>();
        for (var rol : rolsList) {
            var userRol = new UserRol();
            userRol.setRol(rol);
            userRol.getDescription();
            rolNewRol.add(userRol);

        }

        userRolRepository.saveAll(rolNewRol);
    }

    public void initPositionTable() {

        var checkingList = new ArrayList<UserPosition>();
        checkingList = (ArrayList<UserPosition>) userPositionRepository.findAll();

        if (!checkingList.isEmpty()) {
            return;
        }

        var positionList = new ArrayList<String>();
        positionList.add("cocinero");
        positionList.add("mesero");


        var positionNewRol = new ArrayList<UserPosition>();
        for (var position : positionList) {
            var userPosition = new UserPosition();
            userPosition.setNombre(position);
            userPosition.setDescripcion("");
            positionNewRol.add(userPosition);

        }

        userPositionRepository.saveAll(positionNewRol);
    }

    public UsuarioDataDto getUserDataDto() {
        var positions = userPositionRepository.findAll();
        var rols = userRolRepository.findAll();


        return new UsuarioDataDto(
                new Users(),
                new UserData(),
                (List<UserRol>) rols,
                positions

        );
    }

    public void saveUserDataDto(UsuarioDataDto dto) {

        var user = dto.users();
        var userData = dto.userData();

        user.setUserData(userData);
        userData.setUsers(user);


        userRepository.save(user);

    }

    public boolean validateLoginAuth(String loginid, String password, HttpSession session) {
        boolean bool = userRepository.existsByloginidAndPassword(loginid, password);

        if (bool) {
            session.setAttribute("loginid", loginid);
        }
        return bool;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    public UsuarioDataDto getUserDataById(int id) {
        if (id == 0) {
            return null;
        }
        Optional<Users> optionalUser = userRepository.findById(id);
        var user = optionalUser.orElse(null);

        Optional<UserData> optionalData = userDataRepository.findById(id);
        var userData = optionalData.orElse(null);

        var positions = userPositionRepository.findAll();
        var rols = userRolRepository.findAll();

        return new UsuarioDataDto(user, userData, (List<UserRol>) rols, positions);
    }

    public void updateUserDataDto(UsuarioDataDto dto) {
        //user form is the detach modal

        var optionalUser = userRepository.findById(dto.users().getId());
        var user = optionalUser.orElse(null);
        var optionalData = userDataRepository.findById(dto.users().getId());
        var data = optionalData.orElse(null);

        if (user == null) {
            return;
        }

        user.setEstaBloqueado(dto.users().isEstaBloqueado());
        user.setPassword(dto.users().getPassword());
        user.setLoginid(dto.users().getLoginid());
        user.setUserData(dto.userData());
        user.setUserRol(dto.users().getUserRol());

        assert data != null;
        data.setNombre(dto.userData().getNombre());
        data.setApellido(dto.userData().getApellido());
        data.setDni(dto.userData().getDni());
        data.setCargo(dto.userData().getCargo());


        userDataRepository.save(data);
        userRepository.save(user);


    }

    public void saveCargos(UserPosition userPosition) {

        userPositionRepository.save(userPosition);
    }

    public void updateUserCargo(UserPosition pos, Integer id) {

        var userOption = userPositionRepository.findById(id);

        if (userOption.isEmpty()) {
            return;
        }

        var position = userOption.get();

        position.setNombre(pos.getNombre());
        position.setDescripcion(pos.getDescripcion());

        userPositionRepository.save(position);
    }
}
