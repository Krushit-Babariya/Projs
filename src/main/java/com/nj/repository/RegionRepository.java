package com.nj.repository;

import com.nj.common.exception.DBException;
import com.nj.entity.Regions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RegionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Regions> findAll() throws DBException {
        StringBuilder sql = new StringBuilder("SELECT region_id, region_name FROM regions ORDER BY region_id");
        try {
            return jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
                Regions region = new Regions();
                region.setRegionId(rs.getInt("region_id"));
                region.setRegionName(rs.getString("region_name"));
                return region;
            });
        } catch (Exception e) {
            throw new DBException("Error while fetching all regions", e);
        }
    }

    public Regions findById(int regionId) throws DBException {
        StringBuilder sql = new StringBuilder("SELECT region_id, region_name FROM regions WHERE region_id = ?");
        try {
            return jdbcTemplate.queryForObject(sql.toString(), new Object[]{regionId}, (rs, rowNum) -> {
                Regions region = new Regions();
                region.setRegionId(rs.getInt("region_id"));
                region.setRegionName(rs.getString("region_name"));
                return region;
            });
        } catch (Exception e) {
            throw new DBException("Error while fetching region with ID: " + regionId, e);
        }
    }

    public void deleteById(int regionId) throws DBException {
        String sql = "DELETE FROM regions WHERE region_id = ?";
        try {
            jdbcTemplate.update(sql, regionId);
        } catch (Exception e) {
            throw new DBException("Error while deleting region with ID: " + regionId, e);
        }
    }

    public void addRegion(Regions region) throws DBException {
        try {
            String countSql = "SELECT COUNT(*) FROM regions";
            Integer count = jdbcTemplate.queryForObject(countSql, Integer.class);
            if (count != null && count >= 7) {
                throw new IllegalStateException("Only seven continents can be added.");
            }
            String insertSql = "INSERT INTO regions (region_name) VALUES (?)";
            jdbcTemplate.update(insertSql, region.getRegionName());

        } catch (Exception e) {
            throw new DBException("Error while adding new region", e);
        }
    }

    @Transactional
    public void changeStatus(int regionId) throws DBException {
        try {
            // Get current status
            String getStatusQuery = "SELECT active FROM regions WHERE region_id = ?";
            Boolean currentStatus = jdbcTemplate.queryForObject(getStatusQuery, Boolean.class, regionId);
            boolean newStatus = !Boolean.TRUE.equals(currentStatus);

            // Update region
            String updateRegion = "UPDATE regions SET active = ? WHERE region_id = ?";
            jdbcTemplate.update(updateRegion, newStatus, regionId);

            // Update countries
            String updateCountries = "UPDATE countries SET active = ? WHERE region_id = ?";
            jdbcTemplate.update(updateCountries, newStatus, regionId);

            // Update locations
            String updateLocations = "UPDATE locations l "
                    + "JOIN countries c ON l.country_id = c.country_id "
                    + "SET l.active = ? "
                    + "WHERE c.region_id = ?";
            jdbcTemplate.update(updateLocations, newStatus, regionId);

        } catch (Exception e) {
            throw new DBException("Error while changing active status for region ID: " + regionId, e);
        }
    }

    public List<String> checkInactiveWarnings(int regionId) throws DBException {
        String query = "SELECT d.department_name "
                + "FROM departments d "
                + "JOIN locations l ON d.location_id = l.location_id "
                + "JOIN countries c ON l.country_id = c.country_id "
                + "WHERE c.region_id = ? AND c.active = 1";

        try {
            List<String> departments = jdbcTemplate.queryForList(query, String.class, regionId);

            List<String> warnings = new ArrayList<>();
            for (String dept : departments) {
                warnings.add("Department '" + dept + "' is in a country that will be deactivated. Move it first.");
            }

            return warnings;
        } catch (Exception e) {
            throw new DBException("Error while checking department warnings for region ID: " + regionId, e);
        }
    }
}
