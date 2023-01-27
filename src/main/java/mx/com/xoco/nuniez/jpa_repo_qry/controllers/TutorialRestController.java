package mx.com.xoco.nuniez.jpa_repo_qry.controllers;

import mx.com.xoco.nuniez.jpa_repo_qry.models.Tutorial;
import mx.com.xoco.nuniez.jpa_repo_qry.repositories.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/tutorials")
public class TutorialRestControler {
    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping(value = "/")
    List<Tutorial> findAll() {
        List<Tutorial> tutorials = tutorialRepository.findAll();
        showAll(tutorials);
        return tutorials;
    }

    @GetMapping(value = "/published")
    List<Tutorial> findByPublished() {
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
        showAll(tutorials);
        return tutorials;
    }

    @GetMapping(value = "/publishedNQ")
    List<Tutorial> findByPublishedNQ() {
        List<Tutorial> tutorials = tutorialRepository.findByPublishedNQ(true);
        showAll(tutorials);
        return tutorials;
    }

    @GetMapping(value = "/publishedNNQ")
    List<Tutorial> findByPublishedNNQ() {
        List<Tutorial> tutorials = tutorialRepository.findByPublishedNNQ("00", true);
        showAll(tutorials);
        return tutorials;
    }

    @GetMapping(value = "/titleAndPublishedNNQ")
    Tutorial findByTitleAndPublished() {
        Tutorial tutorial = tutorialRepository.findByTitleAndPublished("00", true);
        show(tutorial);
        return tutorial;
    }

    @GetMapping(value = "/title")
    List<Tutorial> findByTitleLike() {
        List<Tutorial> tutorials = tutorialRepository.findByTitleLike("awesome");
        showAll(tutorials);
        return tutorials;
    }

    @GetMapping(value = "/iTitle")
    List<Tutorial> findByTitleLikeCaseInsensitive() {
        List<Tutorial> tutorials = tutorialRepository.findByTitleLikeCaseInsensitive("AwEsOmE");
        showAll(tutorials);
        return tutorials;
    }

    @PostMapping(value = "/")
    Tutorial save() {
        int randomTick = Double.valueOf(Math.ceil(Math.random() * 10) * 1000).intValue();
        return tutorialRepository.save(Tutorial.builder()
                .title(String.format("Title %s", randomTick))
                .description(String.format("Description %s", randomTick))
                .level(randomTick)
                .published(Boolean.TRUE)
                .build());
    }

    private void show(Tutorial tutorial) {
        System.out.println(tutorial);
    }

    private void showAll(List<Tutorial> tutorials) {
        tutorials.forEach(System.out::println);
    }
}
