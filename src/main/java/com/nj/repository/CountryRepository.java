package com.nj.repository;

import com.nj.common.exception.DBException;
import com.nj.entity.Country;
import com.nj.dto.CountryWithRegionDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CountryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCountry(Country country) throws DBException {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO countries (country_name, region_id) VALUES (?, ?)");
            jdbcTemplate.update(sql.toString(), country.getCountryName(), country.getRegionId());
        } catch (Exception e) {
            throw new DBException("Error while adding country", e);
        }
    }

    public List<CountryWithRegionDTO> getAllCountriesWithRegionNames() throws DBException {
        try {
            StringBuilder sql = new StringBuilder("SELECT c.country_id, c.country_name, r.region_name ")
                    .append("FROM countries c ")
                    .append("JOIN regions r ON c.region_id = r.region_id");
            return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(CountryWithRegionDTO.class));
        } catch (Exception e) {
            throw new DBException("Error while fetching countries with region names", e);
        }
    }

    public List<CountryWithRegionDTO> findByRegionId(int regionId) throws DBException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT c.country_id, c.country_name, r.region_name ")
                    .append("FROM countries c ")
                    .append("JOIN regions r ON c.region_id = r.region_id ")
                    .append("WHERE c.region_id = ?");

            return jdbcTemplate.query(
                    sql.toString(),
                    new Object[]{regionId},
                    new BeanPropertyRowMapper<>(CountryWithRegionDTO.class)
            );
        } catch (Exception e) {
            throw new DBException("Error while fetching countries for region id: " + regionId, e);
        }
    }
}
