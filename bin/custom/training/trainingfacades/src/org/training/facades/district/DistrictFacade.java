package org.training.facades.district;

import org.training.facades.district.data.DistrictData;

import java.util.List;

public interface DistrictFacade {

    List<DistrictData> getAllDistrict();
    DistrictData getDistrictByCode(final String code);
    DistrictData getDistrictByName(final String name);

    List<DistrictData> getDistrictByProvinceCode(final String code);
    List<DistrictData> getDistrictByCityCode(final String code);
}
