package com.gorkemersizer.userservice.service;

import com.gorkemersizer.userservice.entity.User;
import com.gorkemersizer.userservice.enums.UserExceptionEnum;
import com.gorkemersizer.userservice.exception.UserException;
import com.gorkemersizer.userservice.repo.UserRepository;
import com.gorkemersizer.userservice.source.request.country.CountryRequest;
import com.gorkemersizer.userservice.source.request.user.*;
import com.gorkemersizer.userservice.source.response.country.CountryResponse;
import com.gorkemersizer.userservice.source.response.user.UserResponse;
import com.gorkemersizer.userservice.source.response.user.UserWCountryInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CountryService countryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository, CountryService theCountryService) {
        userRepository = theUserRepository;
        countryService = theCountryService;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserWCountryInfoResponse> findUserWCountryInfoById(FindUserWCountryRequest request) {
        List<User> users = userRepository.findUsersByCountryCode(request.getId());

        CountryResponse countryInfo = countryService.findCountryInfoById(
                CountryRequest.builder().id(request.getId()).build()
        );

        return users.stream()
                .map(user -> {
                    UserWCountryInfoResponse userWCountryInfoResponse = new UserWCountryInfoResponse();
                    userWCountryInfoResponse.setName(user.getName());
                    userWCountryInfoResponse.setSurname(user.getSurname());
                    userWCountryInfoResponse.setEmail(user.getEmail());
                    userWCountryInfoResponse.setCountryCode(countryInfo.getCode());
                    userWCountryInfoResponse.setCurrency(countryInfo.getCurrency());
                    userWCountryInfoResponse.setBalance(user.getBalance());
                    return userWCountryInfoResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findUserById(FindIdUserRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow( () ->
                new UserException(UserExceptionEnum.USER_NOT_FOUND)
        );
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse findUserByName(FindNameUserRequest request) {
        User user = userRepository.findUserByName(request.getName()).orElseThrow( () ->
                new UserException(UserExceptionEnum.USER_NOT_FOUND)
        );
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User savedEmployee = userRepository.save(modelMapper.map(userRequest, User.class));
        return modelMapper.map(savedEmployee, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        userRepository.findById(userRequest.getId()).orElseThrow( () ->
                new UserException(UserExceptionEnum.USER_NOT_FOUND)
        );
        User savedUser = modelMapper.map(userRequest, User.class);
        return modelMapper.map(userRepository.save(savedUser), UserResponse.class);
    }

    @Override
    public void deleteUser(DeleteUserByIdRequest userRequest) {
        userRepository.deleteById(userRequest.getId());
    }

}
