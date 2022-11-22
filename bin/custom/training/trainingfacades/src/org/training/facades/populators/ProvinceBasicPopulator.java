package org.training.facades.populators;

import de.hybris.platform.converters.Populator;
import org.training.core.model.ProvinceModel;
import org.training.facades.province.data.ProvinceData;

public class ProvinceBasicPopulator implements Populator<ProvinceModel, ProvinceData> {


    public void populate(ProvinceModel provinceModel, ProvinceData provinceData) {
        provinceData.setCode(provinceModel.getCode());
        provinceData.setName(provinceModel.getName());
    }
}
