package com.nj.repository;

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

    public void addCountry(Country country) {
        StringBuilder sql = new StringBuilder("INSERT INTO countries (country_name, region_id) VALUES (?, ?)");
        jdbcTemplate.update(sql.toString(), country.getCountryName(), country.getRegionId());
    }

    public List<CountryWithRegionDTO> getAllCountriesWithRegionNames() {
        StringBuilder sql = new StringBuilder("SELECT c.country_id, c.country_name, r.region_name ")
                .append("FROM countries c ")
                .append("JOIN regions r ON c.region_id = r.region_id");
        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(CountryWithRegionDTO.class));
    }

    public List<CountryWithRegionDTO> findByRegionId(int regionId) {
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
    }

}
