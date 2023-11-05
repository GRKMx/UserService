package com.gorkemersizer.userservice.service;

import com.gorkemersizer.userservice.entity.Country;
import com.gorkemersizer.userservice.enums.UserExceptionEnum;
import com.gorkemersizer.userservice.exception.UserException;
import com.gorkemersizer.userservice.repo.CountryRepository;
import com.gorkemersizer.userservice.source.request.country.CountryRequest;
import com.gorkemersizer.userservice.source.request.country.SaveCountryRequest;
import com.gorkemersizer.userservice.source.response.country.CountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository theCountryRepository) {
        countryRepository = theCountryRepository;
    }

    @Override
    public List<CountryResponse> getAllCountryInfo() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(country -> modelMapper.map(country, CountryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CountryResponse findCountryInfoById(CountryRequest request) {
        Country country = countryRepository.findById(request.getId()).orElseThrow( () ->
                new UserException(UserExceptionEnum.USER_NOT_FOUND)
        );
        return modelMapper.map(country, CountryResponse.class);
    }

    @Override
    public CountryResponse addCountryInfo(SaveCountryRequest request) {
        Country savedCountry = countryRepository.save(modelMapper.map(request, Country.class));
        return modelMapper.map(savedCountry, CountryResponse.class);
    }
}
