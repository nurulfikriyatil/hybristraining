package org.training.facades.province;

import org.training.facades.province.data.ProvinceData;

import java.util.List;

public interface ProvinceFacade {

    List<ProvinceData> getAllProvince();

    ProvinceData getProvinceByCode(final String code);

    ProvinceData getProvinceByName( final String name);
}
