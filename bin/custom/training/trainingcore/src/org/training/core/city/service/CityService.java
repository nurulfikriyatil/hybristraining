package org.training.core.city.service;

import org.training.core.model.CityModel;

import java.util.List;

public interface CityService {

    List<CityModel> getAllCity();
    CityModel getCityByCode(final String code);
    CityModel getCityByName(final String name);
    List<CityModel> getCityByProvinceCode(final String code);

}
