package com.gorkemersizer.userservice.controller;

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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @PostMapping("/getCountries")
    public ResponseEntity<List<CountryResponse>> findCountries() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(countryService.getAllCountryInfo());
    }

    @PostMapping("/addCountry")
    public ResponseEntity<CountryResponse> addCountry(SaveCountryRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(countryService.addCountryInfo(request));
    }

    @PostMapping("/getUsersByCountryId")
    public ResponseEntity<List<UserWCountryInfoResponse>> findUsersByCountryId(@Valid @RequestBody FindUserWCountryRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findUserWCountryInfoById(request));
    }

    @PostMapping("/getUserById")
    public ResponseEntity<UserResponse> findUserById(@Valid @RequestBody FindIdUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findUserById(request));
    }

    @PostMapping("/getUserByName")
    public ResponseEntity<UserResponse> findUserById(@Valid @RequestBody FindNameUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findUserByName(request));
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.createUser(request));
    }

    @PostMapping("/updateUser")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUser(request));
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody DeleteUserByIdRequest request) {
        userService.deleteUser(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User deleted");
    }
}
