package com.tasd.search;

import java.util.Date;
import java.util.List;

public interface JobsRepositoryCustom {
    public List<JobEntity> findUserByQuery(String q, String location);
}