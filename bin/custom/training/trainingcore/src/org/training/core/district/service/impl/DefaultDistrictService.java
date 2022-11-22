package org.training.core.district.service.impl;

import org.training.core.district.dao.DistrictDao;
import org.training.core.district.service.DistrictService;
import org.training.core.model.DistrictModel;

import javax.annotation.Resource;
import java.util.List;

public class DefaultDistrictService implements DistrictService {

    @Resource(name = "districtDao")
    private DistrictDao districtDao;

    @Override
    public List<DistrictModel> getAllDistrict() {
        return districtDao.getAllDistrict();
    }

    @Override
    public DistrictModel getDistrictByCode(String code) {
        return districtDao.getDistrictByCode(code);
    }

    @Override
    public DistrictModel getDistrictByName(String name) {
        return districtDao.getDistrictByName(name);
    }

    @Override
    public List<DistrictModel> getDistrictByCityCode(String code) {
        return districtDao.getDistrictByCityCode(code);
    }

    @Override
    public List<DistrictModel> getDistrictByProvinceCode(String code) {
        return districtDao.getDistrictByProvinceCode(code);
    }
}
