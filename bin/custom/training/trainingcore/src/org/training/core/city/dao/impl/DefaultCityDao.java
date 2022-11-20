package org.training.core.city.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.city.dao.CityDao;
import org.training.core.model.BookModel;
import org.training.core.model.CityModel;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultCityDao<code> implements CityDao {

    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    final private static String ALL_CITY_QUERY = "SELECT {pk} FROM {city}";

    final private static String CITY_QUERY_BY_CODE =ALL_CITY_QUERY + " WHERE {code}=?code";

    final private static String CITY_QUERY_BY_NAME =ALL_CITY_QUERY + " WHERE {name}=?name";


    @Override
    public List<CityModel> getAllCity() {

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(ALL_CITY_QUERY);
        final SearchResult<CityModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<CityModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result;
        }
        return Collections.emptyList();

    }

    @Override
    public CityModel getCityByCode(final String code) {

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(CITY_QUERY_BY_CODE);
        flexibleSearchQuery.addQueryParameter(CityModel.CODE, code);
        final SearchResult<CityModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<CityModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }

        return null;
    }

    @Override
    public CityModel getCityByName(final String name) {

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(CITY_QUERY_BY_NAME);
        flexibleSearchQuery.addQueryParameter(CityModel.NAME, name);
        final SearchResult<CityModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<CityModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }

        return null;
    }

    @Override
    public List<CityModel> getCityByProvinceCode(final String code) {
        final String query = "select {c.pk} from {city as c join province as p on {c.province}={p.pk}} " +
                "where {p.code} like '?code'";
        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
        flexibleSearchQuery.addQueryParameter("code", code);
        final SearchResult<CityModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        List<CityModel> result = searchResult.getResult();
        if(CollectionUtils.isNotEmpty(result)) {
            return result;
        }
        return Collections.emptyList();
    }
}
