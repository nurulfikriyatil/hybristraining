package org.training.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.training.core.model.CityModel;
import org.training.facades.city.data.CityData;

public class CityBasicPopulator implements Populator<CityModel, CityData> {

    @Override
    public void populate(CityModel cityModel, CityData cityData) throws ConversionException {
        cityData.setCode(cityModel.getCode());
        cityData.setName(cityModel.getName());
    }
}
