package com.nj.service;

import com.nj.entity.Jobs;
import java.util.List;

public interface IJobService {
    List<Jobs> getAllJobs();
    Jobs getJobById(int id);
    void addJob(Jobs job);
    void updateJob(Jobs job);
    void deleteJob(int id);
}

