package com.tasd.search;

import java.util.Date;
import java.util.List;

public interface JobsRepositoryCustom {
    public List<JobEntity> findUserByQuery(String q, Date sinceDate, Date toDate, String location);
}
