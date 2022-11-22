package org.training.facades.populators;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.model.CityModel;
import org.training.core.model.DistrictModel;
import org.training.core.model.ProvinceModel;
import org.training.facades.city.data.CityData;
import org.training.facades.district.data.DistrictData;
import org.training.facades.province.data.ProvinceData;

import javax.annotation.Resource;

public class ProvincePopulator extends ProvinceBasicPopulator{

    @Resource(name = "cityConverter")
    private Converter<CityModel, CityData> cityConverter;


    @Override
    public void populate(final ProvinceModel provinceModel, final ProvinceData provinceData)
    {
        super.populate(provinceModel, provinceData);
        if(CollectionUtils.isNotEmpty(provinceModel.getCity())){
            provinceData.setCities(cityConverter.convertAll(provinceModel.getCity()));
        }
    }
}
