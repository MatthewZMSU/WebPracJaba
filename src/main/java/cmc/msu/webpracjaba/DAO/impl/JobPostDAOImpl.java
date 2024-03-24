package cmc.msu.webpracjaba.DAO.impl;

import cmc.msu.webpracjaba.DAO.JobPostDAO;
import cmc.msu.webpracjaba.models.JobPost;

public class JobPostDAOImpl extends CommonDAOImpl<JobPost, Integer> implements JobPostDAO {
    public JobPostDAOImpl() {
        super(JobPost.class);
    }
}
