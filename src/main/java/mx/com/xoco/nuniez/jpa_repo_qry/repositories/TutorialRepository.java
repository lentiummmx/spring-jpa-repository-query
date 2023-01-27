package mx.com.xoco.nuniez.jpa_repo_qry.repositories;

import java.util.List;
import javax.persistence.QueryHint;
import mx.com.xoco.nuniez.jpa_repo_qry.models.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>, CustomTutorialRepository {

    List<Tutorial> findAll();

    @Query("SELECT t FROM Tutorial t WHERE t.published=?1")
    @QueryHints(value = {@QueryHint(name = "QWERTY_ASDFGH", value = "qwerty_asdfgh")})
    List<Tutorial> findByPublished(boolean isPublished);

    @Query("SELECT t FROM Tutorial t WHERE t.title LIKE %?1%")
    List<Tutorial> findByTitleLike(String title);

    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))")
    List<Tutorial> findByTitleLikeCaseInsensitive(String title);

    Tutorial findByTitleAndPublished(String title, boolean published);
}
