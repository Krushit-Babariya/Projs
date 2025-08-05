package com.nj.repository;

import com.nj.common.enums.GeoLocationType;
import com.nj.common.exception.DBException;
import com.nj.entity.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GeoLocationRepository {

    private final JdbcTemplate jdbcTemplate;

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

    public List<String> getAllContinents(GeoLocationType type) throws DBException {
        String sql = "SELECT name FROM geo_locations WHERE type = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{type.name()}, (rs, rowNum) -> rs.getString("name"));
        } catch (Exception e) {
            throw new DBException("Failed to fetch all continents of type: " + type, e);
        }
    }

    public List<GeoLocation> getContinents() throws DBException {
        String sql = "SELECT * FROM geo_locations WHERE type = ? AND parent_id IS NULL";
        try {
            return jdbcTemplate.query(sql, new Object[]{GeoLocationType.CONTINENT.name()}, rowMapper);
        } catch (Exception e) {
            throw new DBException("Failed to fetch continents", e);
        }
    }

    public List<GeoLocation> getCountriesByContinentId(int continentId) throws DBException {
        String sql = "SELECT * FROM geo_locations WHERE type = ? AND parent_id = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{GeoLocationType.COUNTRY.name(), continentId}, rowMapper);
        } catch (Exception e) {
            throw new DBException("Failed to fetch countries for continent ID: " + continentId, e);
        }
    }

    public List<GeoLocation> getStatesByCountryId(int countryId) throws DBException {
        String sql = "SELECT * FROM geo_locations WHERE type = ? AND parent_id = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{GeoLocationType.STATE.name(), countryId}, rowMapper);
        } catch (Exception e) {
            throw new DBException("Failed to fetch states for country ID: " + countryId, e);
        }
    }

    public List<GeoLocation> getCitiesByStateId(int stateId) throws DBException {
        String sql = "SELECT * FROM geo_locations WHERE type = ? AND parent_id = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{GeoLocationType.CITY.name(), stateId}, rowMapper);
        } catch (Exception e) {
            throw new DBException("Failed to fetch cities for state ID: " + stateId, e);
        }
    }
}
