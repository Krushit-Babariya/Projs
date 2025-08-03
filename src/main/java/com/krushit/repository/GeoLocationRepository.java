package com.nj.repository;

import com.nj.common.enums.GeoLocationType;
import com.nj.entity.Country;
import com.nj.dto.CountryWithRegionDTO;
import com.nj.entity.Employees;
import com.nj.entity.GeoLocation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GeoLocationRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GeoLocationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<GeoLocation> rowMapper = (rs, num) -> {
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setId(rs.getInt("id"));
        geoLocation.setName(rs.getString("name"));
        geoLocation.setGeoLocationType(GeoLocationType.valueOf(rs.getString("type")));
        geoLocation.setParentId(rs.getInt("parent_id"));
        return geoLocation;
    };

    public List<String> getAllContinents(GeoLocationType type) {
        StringBuilder sql = new StringBuilder("SELECT name FROM geo_locations WHERE type = ")
                .append(type);
        return jdbcTemplate.query(sql.toString(), new Object[]{type.name()}, (rs, rowNum) -> rs.getString("name"));
    }

    public List<GeoLocation> getContinents() {
        String sql = "SELECT * FROM geo_locations WHERE type = ? AND parent_id IS NULL";
        return jdbcTemplate.query(sql, new Object[]{GeoLocationType.CONTINENT.name()}, rowMapper);
    }

    public List<GeoLocation> getCountriesByContinentId(int continentId) {
        String sql = "SELECT * FROM geo_locations WHERE type = ? AND parent_id = ?";
        return jdbcTemplate.query(sql, new Object[]{GeoLocationType.COUNTRY.name(), continentId}, rowMapper);
    }

    public List<GeoLocation> getStatesByCountryId(int countryId) {
        String sql = "SELECT * FROM geo_locations WHERE type = ? AND parent_id = ?";
        return jdbcTemplate.query(sql, new Object[]{GeoLocationType.STATE.name(), countryId}, rowMapper);
    }

    public List<GeoLocation> getCitiesByStateId(int stateId) {
        String sql = "SELECT * FROM geo_locations WHERE type = ? AND parent_id = ?";
        return jdbcTemplate.query(sql, new Object[]{GeoLocationType.CITY.name(), stateId}, rowMapper);
    }
}
