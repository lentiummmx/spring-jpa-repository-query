package mx.com.xoco.nuniez.jpa_repo_qry.repositories;

import mx.com.xoco.nuniez.jpa_repo_qry.models.Tutorial;

import java.util.List;

public interface CustomTutorialRepository {

    List<Tutorial> findByPublishedNQ(boolean isPublished);

    List<Tutorial> findByPublishedNNQ(String titleDesc, boolean isPublished);

}
