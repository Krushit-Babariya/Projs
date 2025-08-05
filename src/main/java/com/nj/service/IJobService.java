package com.nj.service;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.Jobs;
import java.util.List;

public interface IJobService {

    List<Jobs> getAllJobs() throws ApplicationException;

    Jobs getJobById(int id) throws ApplicationException;

    void addJob(Jobs job) throws ApplicationException;

    void updateJob(Jobs job) throws ApplicationException;

    void deleteJob(int id) throws ApplicationException;
}
