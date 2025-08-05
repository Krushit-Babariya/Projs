package com.nj.repository;

import com.nj.common.exception.DBException;
import com.nj.entity.Location;
import com.nj.dto.LocationWithCountryRegionDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addLocation(Location location) throws DBException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO locations ")
                .append("(street_address, postal_code, city, state_province, country_id) ")
                .append("VALUES (?, ?, ?, ?, ?)");

        try {
            jdbcTemplate.update(sql.toString(),
                    location.getStreetAddress(),
                    location.getPostalCode(),
                    location.getCity(),
                    location.getStateProvince(),
                    location.getCountryId());
        } catch (Exception e) {
            throw new DBException("Error while adding location", e);
        }
    }

    public void deleteLocation(int locationId) throws DBException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM locations WHERE location_id = ?");

        try {
            jdbcTemplate.update(sql.toString(), locationId);
        } catch (Exception e) {
            throw new DBException("Error while deleting location with ID: " + locationId, e);
        }
    }

    public void updateLocation(Location location) throws DBException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE locations SET ")
                .append("street_address = ?, ")
                .append("postal_code = ?, ")
                .append("city = ?, ")
                .append("state_province = ?, ")
                .append("country_id = ? ")
                .append("WHERE location_id = ?");

        try {
            jdbcTemplate.update(sql.toString(),
                    location.getStreetAddress(),
                    location.getPostalCode(),
                    location.getCity(),
                    location.getStateProvince(),
                    location.getCountryId(),
                    location.getLocationId());
        } catch (Exception e) {
            throw new DBException("Error while updating location with ID: " + location.getLocationId(), e);
        }
    }

    public List<LocationWithCountryRegionDTO> getAllLocationsWithCountryAndRegion() throws DBException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT l.location_id, l.street_address, l.postal_code, l.city, l.state_province, ")
                .append("c.country_name, r.region_name ")
                .append("FROM locations l ")
                .append("JOIN countries c ON l.country_id = c.country_id ")
                .append("JOIN regions r ON c.region_id = r.region_id");

        try {
            return jdbcTemplate.query(sql.toString(), new RowMapper<LocationWithCountryRegionDTO>() {
                public LocationWithCountryRegionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LocationWithCountryRegionDTO loc = new LocationWithCountryRegionDTO();
                    loc.setLocationId(rs.getInt("location_id"));
                    loc.setStreetAddress(rs.getString("street_address"));
                    loc.setPostalCode(rs.getInt("postal_code"));
                    loc.setCity(rs.getString("city"));
                    loc.setStateProvince(rs.getString("state_province"));
                    loc.setCountryName(rs.getString("country_name"));
                    loc.setRegionName(rs.getString("region_name"));
                    return loc;
                }
            });
        } catch (Exception e) {
            throw new DBException("Error while fetching all locations with country and region details", e);
        }
    }
}
