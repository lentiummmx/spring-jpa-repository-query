package mx.com.xoco.nuniez.jpa_repo_qry.repositories.impl;

import mx.com.xoco.nuniez.jpa_repo_qry.models.Tutorial;
import mx.com.xoco.nuniez.jpa_repo_qry.repositories.TutorialRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TutorialRepositoryCustomImpl implements TutorialRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * @param isPublished
     * @return
     */
    @Override
    public List<Tutorial> findByPublishedNQ(boolean isPublished) {
        return null;
    }
}
