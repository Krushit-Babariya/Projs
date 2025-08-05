package com.nj.repository;

import com.nj.common.exception.DBException;
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

    public List<Jobs> getAllJobs() throws DBException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM jobs");

        try {
            return jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
                Jobs job = new Jobs();
                job.setJobId(rs.getInt("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setMinSalary(rs.getDouble("min_salary"));
                job.setMaxSalary(rs.getDouble("max_salary"));
                return job;
            });
        } catch (Exception e) {
            throw new DBException("Error while fetching all jobs", e);
        }
    }

    public Jobs getJobById(int id) throws DBException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM jobs WHERE job_id = ?");

        try {
            return jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, (rs, rowNum) -> {
                Jobs job = new Jobs();
                job.setJobId(rs.getInt("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setMinSalary(rs.getDouble("min_salary"));
                job.setMaxSalary(rs.getDouble("max_salary"));
                return job;
            });
        } catch (Exception e) {
            throw new DBException("Error while fetching job with ID: " + id, e);
        }
    }

    public int addJob(Jobs job) throws DBException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO jobs (job_id, job_title, min_salary, max_salary) ");
        sql.append("VALUES (?, ?, ?, ?)");

        try {
            return jdbcTemplate.update(sql.toString(),
                    job.getJobId(),
                    job.getJobTitle(),
                    job.getMinSalary(),
                    job.getMaxSalary());
        } catch (Exception e) {
            throw new DBException("Error while adding job with ID: " + job.getJobId(), e);
        }
    }

    public int updateJob(Jobs job) throws DBException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? ");
        sql.append("WHERE job_id = ?");

        try {
            return jdbcTemplate.update(sql.toString(),
                    job.getJobTitle(),
                    job.getMinSalary(),
                    job.getMaxSalary(),
                    job.getJobId());
        } catch (Exception e) {
            throw new DBException("Error while updating job with ID: " + job.getJobId(), e);
        }
    }

    public int deleteJob(int jobId) throws DBException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM jobs WHERE job_id = ?");

        try {
            return jdbcTemplate.update(sql.toString(), jobId);
        } catch (Exception e) {
            throw new DBException("Error while deleting job with ID: " + jobId, e);
        }
    }
}
