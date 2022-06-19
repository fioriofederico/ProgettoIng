package com.ProgettoIng.FedericoIoan.service.IService;

import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.model.dto.NewPasswordDto;

import java.util.List;

public interface UserService {

    List<User> findUsers();

    User findUser(Long id);

    User updatePwd(Long userId, NewPasswordDto password);

    User deleteUser(Long id);

}
