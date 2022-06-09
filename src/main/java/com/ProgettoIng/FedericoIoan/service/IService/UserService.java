package com.ProgettoIng.FedericoIoan.service.IService;

import com.ProgettoIng.FedericoIoan.model.User;

import java.util.List;

public interface UserService {

    List<User> findUsers();

    User findUser(Long id);

    User updatePwd(Long userId, User userDto);

    User deleteUser(Long id);

}
