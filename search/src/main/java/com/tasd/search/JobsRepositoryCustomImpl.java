package com.tasd.search;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class JobsRepositoryCustomImpl implements JobsRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<JobEntity> findUserByQuery(String q, Date sinceDate, Date toDate, String location) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobEntity> query = cb.createQuery(JobEntity.class);
        Root<JobEntity> jobEntity = query.from(JobEntity.class);

        List<Predicate> termPredicates = new LinkedList<>();
        if (q != null && !q.isEmpty()) {
            Path<String> description = jobEntity.get("description");
            Path<String> position = jobEntity.get("position");
            Path<String> companyName = jobEntity.get("companyName");
            for (String term : q.split(" ")) {
                termPredicates.add(cb.like(description, term));
                termPredicates.add(cb.like(position, term));
                termPredicates.add(cb.like(companyName, term));
            }
        }

        if (location != null && !location.isEmpty()) {
            Path<String> loc = jobEntity.get("location");
            termPredicates.add(cb.like(loc, location));
        }
        Predicate termPredicate = cb.or(termPredicates.toArray(new Predicate[termPredicates.size()]));

        List<Predicate> finalPredicates = new LinkedList<>();
        Path<Date> createdAt = jobEntity.get("createdAt");
        if (sinceDate != null) {
            finalPredicates.add(cb.greaterThan(createdAt, sinceDate));
        }
        if (toDate != null) {
            finalPredicates.add(cb.lessThan(createdAt, toDate));
        }

        finalPredicates.add(termPredicate);

        query.select(jobEntity).where(
                cb.and(finalPredicates.toArray(new Predicate[finalPredicates.size()]))
        );

        return entityManager.createQuery(query).getResultList();
    }
}
