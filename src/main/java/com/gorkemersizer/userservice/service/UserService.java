package com.gorkemersizer.userservice.service;

import com.gorkemersizer.userservice.source.request.user.*;
import com.gorkemersizer.userservice.source.response.user.UserResponse;
import com.gorkemersizer.userservice.source.response.user.UserWCountryInfoResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    List<UserWCountryInfoResponse> findUserWCountryInfoById(FindUserWCountryRequest request);
    UserResponse findUserById(FindIdUserRequest request);
    UserResponse findUserByName(FindNameUserRequest request);
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(UserRequest request);
    void deleteUser(DeleteUserByIdRequest request);
}
