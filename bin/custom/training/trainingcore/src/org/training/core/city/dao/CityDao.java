package org.training.core.city.dao;

import org.training.core.model.CityModel;

import java.util.List;

public interface CityDao {
    List<CityModel> getAllCity();

    CityModel getCityByCode(final String code);

    CityModel getCityByName(final String name);

    List<CityModel> getCityByProvinceCode(final String code);
}
