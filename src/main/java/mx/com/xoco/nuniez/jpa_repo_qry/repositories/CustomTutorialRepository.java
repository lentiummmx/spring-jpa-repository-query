package mx.com.xoco.nuniez.jpa_repo_qry.repositories;

import java.util.List;
import mx.com.xoco.nuniez.jpa_repo_qry.models.Tutorial;

public interface CustomTutorialRepository {

    List<Tutorial> findByPublishedNQ(boolean isPublished);

    List<Tutorial> findByPublishedNNQ(String titleDesc, boolean isPublished);
}
