package org.training.facades.city;

import org.training.facades.city.data.CityData;

import java.util.List;

public interface CityFacade {

    List<CityData> getAllCity();

    CityData getCityByCode(final String code);

    CityData getCityByName(final String name);

    List<CityData> getCityByProvinceCode(final String code);
}
