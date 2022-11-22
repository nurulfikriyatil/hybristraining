package org.training.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.training.core.model.CityModel;
import org.training.core.model.DistrictModel;
import org.training.core.model.ProvinceModel;
import org.training.core.model.StudentModel;
import org.training.facades.city.data.CityData;
import org.training.facades.district.data.DistrictData;
import org.training.facades.province.data.ProvinceData;
import org.training.facades.student.data.StudentData;

import javax.annotation.Resource;

public class CityPopulator implements Populator<CityModel, CityData> {

    @Resource(name = "provinceBasicConverter")
    private Converter<ProvinceModel, ProvinceData> provinceBasicConverter;

    @Resource(name = "districtConverter")
    private Converter<DistrictModel, DistrictData> districtConverter;

    @Override
    public void populate(CityModel cityModel, CityData cityData) throws ConversionException {
        cityData.setCode(cityModel.getCode());
        cityData.setName(cityModel.getName());
        if (null != cityModel.getProvince()){
            cityData.setProvince(provinceBasicConverter.convert(cityModel.getProvince()));
        }
        if(null != cityModel.getDistrict()){
            cityData.setDistricts(districtConverter.convertAll(cityModel.getDistrict()));
        }
    }
}
