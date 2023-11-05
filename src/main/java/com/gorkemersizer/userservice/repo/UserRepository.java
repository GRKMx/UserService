package com.gorkemersizer.userservice.repo;

import com.gorkemersizer.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{name:'?0'}")
    Optional<User> findUserByName(String name);

    @Query("{countryId:'?0'}")
    List<User> findUsersByCountryCode(String countryId);

}
