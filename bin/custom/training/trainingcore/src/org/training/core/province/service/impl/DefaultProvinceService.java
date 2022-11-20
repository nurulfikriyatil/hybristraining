package org.training.core.province.service.impl;

import org.training.core.model.ProvinceModel;
import org.training.core.province.dao.ProvinceDao;
import org.training.core.province.service.ProvinceService;

import javax.annotation.Resource;
import java.util.List;

public class DefaultProvinceService implements ProvinceService {
    @Resource(name = "provinceDao")
    private ProvinceDao provinceDao;

    @Override
    public List<ProvinceModel> getAllProvince() {
        return provinceDao.getAllProvince();
    }

    @Override
    public ProvinceModel getProvinceByCode(String code) {
        return provinceDao.getProvinceByCode(code);
    }

    @Override
    public ProvinceModel getProvinceByName(String name) {
        return provinceDao.getProvinceByName(name);
    }
}
