package org.training.core.province.dao;

import org.training.core.model.ProvinceModel;

import java.util.List;

public interface ProvinceDao {

    List<ProvinceModel> getAllProvince();

    ProvinceModel getProvinceByCode(final String code);

    ProvinceModel getProvinceByName(final String name);


}
