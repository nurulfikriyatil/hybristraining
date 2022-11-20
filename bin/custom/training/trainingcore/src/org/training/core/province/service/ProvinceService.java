package org.training.core.province.service;


import org.training.core.model.ProvinceModel;

import java.util.List;

public interface ProvinceService {
    List<ProvinceModel> getAllProvince();
    ProvinceModel getProvinceByCode(final String code);
    ProvinceModel getProvinceByName(final String name);

}
