package org.training.core.province.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.model.CityModel;
import org.training.core.model.ProvinceModel;
import org.training.core.province.dao.ProvinceDao;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultProvinceDao implements ProvinceDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    final private static String ALL_PROVINCE_QUERY = "SELECT {pk} FROM {province}";

    final private static String PROVINCE_QUERY_BY_CODE =ALL_PROVINCE_QUERY + " WHERE {code}=?code";

    final private static String PROVINCE_QUERY_BY_NAME =ALL_PROVINCE_QUERY + " WHERE {name}=?name";

    @Override
    public List<ProvinceModel> getAllProvince() {
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(ALL_PROVINCE_QUERY);
        final SearchResult<ProvinceModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<ProvinceModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result;
        }
        return Collections.emptyList();
    }

    @Override
    public ProvinceModel getProvinceByCode(final String code) {
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(PROVINCE_QUERY_BY_CODE);
        flexibleSearchQuery.addQueryParameter(CityModel.CODE, code);
        final SearchResult<ProvinceModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<ProvinceModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public ProvinceModel getProvinceByName(final String name) {
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(PROVINCE_QUERY_BY_NAME);
        flexibleSearchQuery.addQueryParameter(CityModel.NAME, name);
        final SearchResult<ProvinceModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<ProvinceModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }
}
