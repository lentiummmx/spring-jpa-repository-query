package mx.com.xoco.nuniez.jpa_repo_qry.repositories.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import mx.com.xoco.nuniez.jpa_repo_qry.models.Tutorial;
import mx.com.xoco.nuniez.jpa_repo_qry.repositories.CustomTutorialRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.QueryHintBinder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTutorialRepositoryImpl implements CustomTutorialRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public SessionFactory getSessionFactoryFromJPA() {
        EntityManagerFactory entityManagerFactory = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        // JPA and Hibernate SessionFactory example
        // EntityManagerFactory emf =
        // Persistence.createEntityManagerFactory("jpa-tutorial");
        // EntityManager entityManager = emf.createEntityManager();
        // Get the Hibernate Session from the EntityManager in JPA
        if (entityManager == null
                || (entityManagerFactory = entityManager.getEntityManagerFactory()) == null
                || (session = entityManager.unwrap(Session.class)) == null
                || (sessionFactory = session.getSessionFactory()) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return sessionFactory;
    }

    private Session getSessionFromSessionFactory() {
        EntityManagerFactory entityManagerFactory = null;
        SessionFactory sessionFactory = null;
        if (entityManager == null
                || (entityManagerFactory = entityManager.getEntityManagerFactory()) == null
                || (sessionFactory = entityManagerFactory.unwrap(SessionFactory.class)) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        // return sessionFactory.getCurrentSession();
        return sessionFactory.openSession();
    }

    private Session getCurrentSession() {
        Session session = null;
        if (entityManager == null || (session = entityManager.unwrap(Session.class)) == null) {
            throw new NullPointerException();
        }
        return session;
    }

    /**
     * @param isPublished
     * @return
     */
    @Override
    public List<Tutorial> findByPublishedNQ(boolean isPublished) {
        /*
         * Query query = entityManager .createNamedQuery("NQ_TUTORIALS_PUBLISHED",
         * Tutorial.class); query.setParameter("published", isPublished);
         */
        Query query = getCurrentSession().createNamedQuery("NQ_TUTORIALS_PUBLISHED", Tutorial.class);
        query.setParameter("published", isPublished);
        QueryHintBinder.getQueryHints(Tutorial.class, "NQ_TUTORIALS_PUBLISHED").forEach(query::addQueryHint);
        return query.getResultList();
    }

    /**
     * @param isPublished
     * @return
     */
    @Override
    public List<Tutorial> findByPublishedNNQ(String titleDesc, boolean isPublished) {
        Query query = getSessionFromSessionFactory().createNamedQuery("NNQ_TUTORIALS_PUBLISHED", Tutorial.class);
        query.setParameter("title", titleDesc);
        query.setParameter("description", "%" + titleDesc + "%");
        query.setParameter("published", isPublished);
        QueryHintBinder.getQueryHints(Tutorial.class, "NNQ_TUTORIALS_PUBLISHED").forEach(query::addQueryHint);
        return query.getResultList();
    }
}
