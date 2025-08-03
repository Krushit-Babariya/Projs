package com.nj.repository;

import com.nj.entity.Jobs;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JobRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JobRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Jobs> getAllJobs() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM jobs");

        return jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
            Jobs job = new Jobs();
            job.setJobId(rs.getInt("job_id"));
            job.setJobTitle(rs.getString("job_title"));
            job.setMinSalary(rs.getDouble("min_salary"));
            job.setMaxSalary(rs.getDouble("max_salary"));
            return job;
        });
    }

    public Jobs getJobById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM jobs WHERE job_id = ?");

        return jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, (rs, rowNum) -> {
            Jobs job = new Jobs();
            job.setJobId(rs.getInt("job_id"));
            job.setJobTitle(rs.getString("job_title"));
            job.setMinSalary(rs.getDouble("min_salary"));
            job.setMaxSalary(rs.getDouble("max_salary"));
            return job;
        });
    }

    public int addJob(Jobs job) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO jobs (job_id, job_title, min_salary, max_salary) ");
        sql.append("VALUES (?, ?, ?, ?)");

        return jdbcTemplate.update(sql.toString(),
                job.getJobId(),
                job.getJobTitle(),
                job.getMinSalary(),
                job.getMaxSalary());
    }

    public int updateJob(Jobs job) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? ");
        sql.append("WHERE job_id = ?");

        return jdbcTemplate.update(sql.toString(),
                job.getJobTitle(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getJobId());
    }

    public int deleteJob(int jobId) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM jobs WHERE job_id = ?");

        return jdbcTemplate.update(sql.toString(), jobId);
    }
}
