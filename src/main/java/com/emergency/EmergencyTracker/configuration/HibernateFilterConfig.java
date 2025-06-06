package com.emergency.EmergencyTracker.configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class HibernateFilterConfig {

    @PersistenceContext
    private EntityManager entityManager;

    public void habilitarFiltroDeletados() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("deletedFilter").setParameter("isDeleted", false);
    }
}
