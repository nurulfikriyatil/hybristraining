package org.training.core.city.service.impl;

import org.training.core.city.dao.CityDao;
import org.training.core.city.service.CityService;
import org.training.core.model.CityModel;

import javax.annotation.Resource;
import java.util.List;

public class DefaultCityService implements CityService {

    @Resource(name = "cityDao")
    private CityDao cityDao;

    @Override
    public List<CityModel> getAllCity() {
        return cityDao.getAllCity();
    }

    @Override
    public CityModel getCityByCode(final String code) {
        return cityDao.getCityByCode(code);
    }

    @Override
    public CityModel getCityByName( final String name) {
        return cityDao.getCityByName(name);
    }

    @Override
    public List<CityModel> getCityByProvinceCode(final String code) {
        return cityDao.getCityByProvinceCode(code);
    }

}
