package com.gorkemersizer.userservice.controller;

import com.gorkemersizer.userservice.base.BaseResponse;
import com.gorkemersizer.userservice.base.ResponseHandler;
import com.gorkemersizer.userservice.service.CountryService;
import com.gorkemersizer.userservice.service.UserService;
import com.gorkemersizer.userservice.source.request.country.SaveCountryRequest;
import com.gorkemersizer.userservice.source.request.user.*;
import com.gorkemersizer.userservice.source.response.country.CountryResponse;
import com.gorkemersizer.userservice.source.response.user.UserResponse;
import com.gorkemersizer.userservice.source.response.user.UserWCountryInfoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    private final CountryService countryService;

    @Autowired
    public UserRestController(UserService theUserService, CountryService theCountryService) {
        userService = theUserService;
        countryService = theCountryService;
    }

    @PostMapping("/getUsers")
    public BaseResponse<List<UserResponse>> findAllUsers() {
        return ResponseHandler
                .responseEntityHandler(HttpStatus.OK,userService.getAllUsers());
    }

    @PostMapping("/getCountries")
    public BaseResponse<List<CountryResponse>> findCountries() {
        return ResponseHandler
                .responseEntityHandler(HttpStatus.OK,countryService.getAllCountryInfo());
    }

    @PostMapping("/addCountry")
    public BaseResponse<CountryResponse> addCountry(SaveCountryRequest request) {
        return ResponseHandler
                .responseEntityHandler(HttpStatus.OK,countryService.addCountryInfo(request));
    }

    @PostMapping("/getUsersByCountryId")
    public BaseResponse<List<UserWCountryInfoResponse>> findUsersByCountryId(@Valid @RequestBody FindUserWCountryRequest request) {
        return ResponseHandler
                .responseEntityHandler(HttpStatus.OK,userService.findUserWCountryInfoById(request));
    }

    @PostMapping("/getUserById")
    public BaseResponse<UserResponse> findUserById(@Valid @RequestBody FindIdUserRequest request) {
        return ResponseHandler
                .responseEntityHandler(HttpStatus.OK,userService.findUserById(request));
    }

    @PostMapping("/getUserByName")
    public BaseResponse<UserResponse> findUserById(@Valid @RequestBody FindNameUserRequest request) {
        return ResponseHandler
                .responseEntityHandler(HttpStatus.OK,userService.findUserByName(request));
    }

    @PostMapping("/createUser")
    public BaseResponse<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseHandler
                .responseEntityHandler(HttpStatus.OK,userService.createUser(request));
    }

    @PostMapping("/updateUser")
    public BaseResponse<UserResponse> updateUser(@Valid @RequestBody UserRequest request) {
        return ResponseHandler
                .responseEntityHandler(HttpStatus.OK,userService.updateUser(request));
    }

    @PostMapping("/deleteUser")
    public BaseResponse<String> deleteUser(@Valid @RequestBody DeleteUserByIdRequest request) {
        userService.deleteUser(request);
        return ResponseHandler
                .responseEntityHandler(HttpStatus.OK,"User Deleted");
    }
}
