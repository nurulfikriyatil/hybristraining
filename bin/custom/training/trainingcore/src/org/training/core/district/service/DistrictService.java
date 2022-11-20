package org.training.core.district.service;

import org.training.core.model.DistrictModel;

import java.util.List;

public interface DistrictService {
    List<DistrictModel> getAllDistrict();
    DistrictModel getDistrictByCode(final String code);
    DistrictModel getDistrictByName(final String name);

    List<DistrictModel> getDistrictByCityCode(final String code);

    List<DistrictModel> getDistrictByProvinceCode(final String code);

}
