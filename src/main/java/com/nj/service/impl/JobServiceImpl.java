package com.nj.service.impl;

import com.nj.common.exception.ApplicationException;
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
    public List<Jobs> getAllJobs() throws ApplicationException {
        return jobRepository.getAllJobs();
    }

    @Override
    public Jobs getJobById(int id) throws ApplicationException {
        return jobRepository.getJobById(id);
    }

    @Override
    public void addJob(Jobs job) throws ApplicationException {
        jobRepository.addJob(job);
    }

    @Override
    public void updateJob(Jobs job) throws ApplicationException {
        jobRepository.updateJob(job);
    }

    @Override
    public void deleteJob(int id) throws ApplicationException {
        jobRepository.deleteJob(id);
    }
}
