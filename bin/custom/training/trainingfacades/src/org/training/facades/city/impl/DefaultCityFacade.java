package org.training.facades.city.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.book.service.BookService;
import org.training.core.city.service.CityService;
import org.training.core.model.BookModel;
import org.training.core.model.CityModel;
import org.training.facades.book.data.BookData;
import org.training.facades.city.CityFacade;
import org.training.facades.city.data.CityData;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultCityFacade implements CityFacade {

    @Resource(name="defaultCityService")
    private CityService cityService;

    @Resource(name = "cityConverter")
    private Converter<CityModel, CityData> cityConverter;

    @Override
    public List<CityData> getAllCity() {

        final List<CityModel> cityModels = cityService.getAllCity();
        if (CollectionUtils.isNotEmpty(cityModels)){
            return cityConverter.convertAll(cityModels);
        }
        return Collections.emptyList();

    }

    @Override
    public CityData getCityByCode( final String code) {
        final CityModel cityModel = cityService.getCityByCode(code);
        if (null != cityModel)
        {
            return cityConverter.convert(cityModel);
        }
        return null;
    }

    @Override
    public CityData getCityByName(String name) {
        final CityModel cityModel = cityService.getCityByName(name);
        if (null != cityModel)
        {
            return cityConverter.convert(cityModel);
        }
        return null;
    }

    @Override
    public List<CityData> getCityByProvinceCode(String code) {
        List<CityModel> cityModel = cityService.getCityByProvinceCode(code);
        if (CollectionUtils.isNotEmpty(cityModel)){
            return cityConverter.convertAll(cityModel);
        }
        return Collections.emptyList();
    }
}
