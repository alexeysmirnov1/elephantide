package com.example.demo.bootstrap;

import com.example.demo.DemoApplication;
import com.example.demo.ide.editor.EditorIntegration;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringbootJavaFxApplication extends Application {
    private ConfigurableApplicationContext context;

//    private EditorIntegration editor;

    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer = new ApplicationContextInitializer<GenericApplicationContext>() {
            @Override
            public void initialize(GenericApplicationContext applicationContext) {
                applicationContext.registerBean(Application.class, () -> SpringbootJavaFxApplication.this);
                applicationContext.registerBean(Parameters.class, () -> getParameters());
                applicationContext.registerBean(HostServices.class, () -> getHostServices());
            }
        };

        this.context = new SpringApplicationBuilder()
            .sources(DemoApplication.class)
            .initializers(initializer)
            .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.context.getBeanFactory().registerSingleton("primaryStage", primaryStage);
        this.context.publishEvent(new StageReadyEvent(primaryStage));

//        editor = new EditorIntegration();
//        editor.initialize();
//
//        this.context.getBeanFactory().registerSingleton("phpActor", editor);
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }
}
