package com.gorkemersizer.userservice.service;

import com.gorkemersizer.userservice.source.request.country.CountryRequest;
import com.gorkemersizer.userservice.source.request.country.SaveCountryRequest;
import com.gorkemersizer.userservice.source.response.country.CountryResponse;

import java.util.List;

public interface CountryService {

    List<CountryResponse> getAllCountryInfo();
    CountryResponse findCountryInfoById(CountryRequest request);
    CountryResponse addCountryInfo(SaveCountryRequest request);
}
