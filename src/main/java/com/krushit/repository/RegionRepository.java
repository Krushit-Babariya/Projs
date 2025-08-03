package com.nj.repository;

import com.nj.entity.Regions;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class RegionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Regions> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT region_id, region_name FROM regions ORDER BY region_id");

        return jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
            Regions region = new Regions();
            region.setRegionId(rs.getInt("region_id"));
            region.setRegionName(rs.getString("region_name"));
            return region;
        });
    }

    public Regions findById(int regionId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT region_id, region_name FROM regions WHERE region_id = ?");

        return jdbcTemplate.queryForObject(sql.toString(), new Object[]{regionId}, (rs, rowNum) -> {
            Regions region = new Regions();
            region.setRegionId(rs.getInt("region_id"));
            region.setRegionName(rs.getString("region_name"));
            return region;
        });
    }

    public void deleteById(int regionId) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM regions WHERE region_id = ?");
        jdbcTemplate.update(sql.toString(), regionId);
    }

    public void addRegion(Regions region) {
        StringBuilder countSql = new StringBuilder();
        countSql.append("SELECT COUNT(*) FROM regions");

        Integer count = jdbcTemplate.queryForObject(countSql.toString(), Integer.class);
        if (count != null && count >= 7) {
            throw new IllegalStateException("Only seven continents can be added.");
        }

        StringBuilder insertSql = new StringBuilder();
        insertSql.append("INSERT INTO regions (region_name) VALUES (?)");
        jdbcTemplate.update(insertSql.toString(), region.getRegionName());
    }
}
