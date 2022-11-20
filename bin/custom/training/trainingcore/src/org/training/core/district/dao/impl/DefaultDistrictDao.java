package org.training.core.district.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.district.dao.DistrictDao;
import org.training.core.model.CityModel;
import org.training.core.model.DistrictModel;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultDistrictDao implements DistrictDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    final private static String ALL_DISTRICT_QUERY = "SELECT {pk} FROM {district}";

    final private static String DISTRICT_QUERY_BY_CODE =ALL_DISTRICT_QUERY + " WHERE {code}=?code";

    final private static String DISTRICT_QUERY_BY_NAME =ALL_DISTRICT_QUERY + " WHERE {name}=?name";

    @Override
    public List<DistrictModel> getAllDistrict() {
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(ALL_DISTRICT_QUERY);
        final SearchResult<DistrictModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<DistrictModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result;
        }
        return Collections.emptyList();
    }

    @Override
    public DistrictModel getDistrictByCode(final String code) {
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(DISTRICT_QUERY_BY_CODE);
        flexibleSearchQuery.addQueryParameter(CityModel.CODE, code);
        final SearchResult<DistrictModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<DistrictModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }

        return null;
    }

    @Override
    public DistrictModel getDistrictByName(final String name) {
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(DISTRICT_QUERY_BY_NAME);
        flexibleSearchQuery.addQueryParameter(CityModel.NAME, name);
        final SearchResult<DistrictModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<DistrictModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }

        return null;
    }

    @Override
    public List<DistrictModel> getDistrictByCityCode(final String code) {
        final String query = "select {d.pk} from {district as d join city as c on {d.city}={c.pk}} " +
                "where {c.code} like '?code'" ;
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("code", code);
        final SearchResult<DistrictModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<DistrictModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result;
        }
        return Collections.emptyList();
    }

    @Override
    public List<DistrictModel> getDistrictByProvinceCode(final String code) {
        final String query = "select {d.pk} from {district as d join city as c on {d.city}={c.pk} " +
                "join province as p on {c.province}={p.pk}} where {p.code} like '?code'";
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("code", code);
        final SearchResult<DistrictModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<DistrictModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result;
        }
        return Collections.emptyList();
    }
}
