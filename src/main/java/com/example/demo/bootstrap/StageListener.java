package com.example.demo.bootstrap;

import com.example.demo.ide.Presentation.Project.UI.Scenes.Open;
import com.example.demo.video.scenes.PHPEditor;
import com.kieferlam.javafxblur.Blur;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {
    private final String applicationTitle;
    private final Resource fxml;

    private final ApplicationContext applicationContext;

    public StageListener(@Value("${spring.application.ui.title}") String applicationTitle, @Value("classpath:/view/ui.fxml") Resource resource, ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.fxml = resource;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        Stage stage = stageReadyEvent.getStage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        //todo вернуть стартовый экран
//        stage.setScene(this.applicationContext.getBean(Open.class).load());
        stage.setScene(this.applicationContext.getBean(PHPEditor.class).load());
        stage.setTitle(this.applicationTitle);
        stage.show();
//        Blur.applyBlur(stage, Blur.ACRYLIC);
    }
}
