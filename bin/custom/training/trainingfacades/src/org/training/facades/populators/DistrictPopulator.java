package org.training.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.training.core.model.CityModel;
import org.training.core.model.DistrictModel;
import org.training.facades.city.data.CityData;
import org.training.facades.district.data.DistrictData;

import javax.annotation.Resource;

public class DistrictPopulator implements Populator<DistrictModel, DistrictData> {

    @Resource(name = "cityBasicConverter")
    private Converter<CityModel, CityData> cityBasicConverter;

    @Override
    public void populate(DistrictModel districtModel, DistrictData districtData) throws ConversionException {

        districtData.setCode(districtModel.getCode());
        districtData.setName(districtModel.getName());
        if(null!= districtModel.getCity()){
            districtData.setCity(cityBasicConverter.convert(districtModel.getCity()));
        }
    }
}
