package mx.com.xoco.nuniez.jpa_repo_qry.repositories;

import mx.com.xoco.nuniez.jpa_repo_qry.models.Tutorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface TutorialRepositoryCustom {

    List<Tutorial> findByPublishedNQ(boolean isPublished);
}
