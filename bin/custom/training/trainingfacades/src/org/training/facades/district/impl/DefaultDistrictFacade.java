package org.training.facades.district.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.district.service.DistrictService;
import org.training.core.model.CityModel;
import org.training.core.model.DistrictModel;
import org.training.facades.district.DistrictFacade;
import org.training.facades.district.data.DistrictData;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultDistrictFacade implements DistrictFacade {

    @Resource(name="defaultDistrictService")
    private DistrictService districtService;

    @Resource(name = "districtConverter")
    private Converter<DistrictModel, DistrictData> districtConverter;
    @Override
    public List<DistrictData> getAllDistrict() {
        final List<DistrictModel> districtModels = districtService.getAllDistrict();
        if (CollectionUtils.isNotEmpty(districtModels)){
            return districtConverter.convertAll(districtModels);
        }
        return Collections.emptyList();
    }

    @Override
    public DistrictData getDistrictByCode(String code) {
        final DistrictModel districtModel= districtService.getDistrictByCode(code);
        if (null != districtModel)
        {
            return districtConverter.convert(districtModel);
        }
        return null;
    }

    @Override
    public DistrictData getDistrictByName(String name) {
        final DistrictModel districtModel= districtService.getDistrictByName(name);
        if (null != districtModel)
        {
            return districtConverter.convert(districtModel);
        }
        return null;
    }

    @Override
    public List<DistrictData> getDistrictByProvinceCode(String code) {
        List<DistrictModel> districtModels = districtService.getDistrictByProvinceCode(code);
        if (CollectionUtils.isNotEmpty(districtModels)){
            return districtConverter.convertAll(districtModels);
        }
        return Collections.emptyList();
    }

    @Override
    public List<DistrictData> getDistrictByCityCode(String code) {
        List<DistrictModel> districtModels = districtService.getDistrictByCityCode(code);
        if (CollectionUtils.isNotEmpty(districtModels)){
            return districtConverter.convertAll(districtModels);
        }
        return Collections.emptyList();
    }
}
