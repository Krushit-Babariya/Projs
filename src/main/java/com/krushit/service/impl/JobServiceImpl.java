package com.nj.service.impl;

import com.nj.entity.Jobs;
import com.nj.repository.JobRepository;
import com.nj.service.IJobService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Jobs> getAllJobs() {
        return jobRepository.getAllJobs();
    }

    @Override
    public Jobs getJobById(int id) {
        return jobRepository.getJobById(id);
    }

    @Override
    public void addJob(Jobs job) {
        jobRepository.addJob(job);
    }

    @Override
    public void updateJob(Jobs job) {
        jobRepository.updateJob(job);
    }

    @Override
    public void deleteJob(int id) {
        jobRepository.deleteJob(id);
    }
}
