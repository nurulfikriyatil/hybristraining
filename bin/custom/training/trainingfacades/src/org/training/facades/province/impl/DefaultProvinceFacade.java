package org.training.facades.province.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.city.service.CityService;
import org.training.core.model.CityModel;
import org.training.core.model.ProvinceModel;
import org.training.core.province.service.ProvinceService;
import org.training.facades.city.data.CityData;
import org.training.facades.province.ProvinceFacade;
import org.training.facades.province.data.ProvinceData;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultProvinceFacade implements ProvinceFacade {

    @Resource(name="defaultProvinceService")
    private ProvinceService provinceService;

    @Resource(name = "provinceConverter")
    private Converter<ProvinceModel, ProvinceData> provinceConverter;

    @Override
    public List<ProvinceData> getAllProvince() {
        final List<ProvinceModel> provinceModels = provinceService.getAllProvince();
        if (CollectionUtils.isNotEmpty(provinceModels)){
            return provinceConverter.convertAll(provinceModels);
        }
        return Collections.emptyList();
    }

    @Override
    public ProvinceData getProvinceByCode(String code) {
        final ProvinceModel provinceModel= provinceService.getProvinceByCode(code);
        if (null != provinceModel)
        {
            return provinceConverter.convert(provinceModel);
        }
        return null;
    }

    @Override
    public ProvinceData getProvinceByName(String name) {
        final ProvinceModel provinceModel= provinceService.getProvinceByName(name);
        if (null != provinceModel)
        {
            return provinceConverter.convert(provinceModel);
        }
        return null;
    }
}
