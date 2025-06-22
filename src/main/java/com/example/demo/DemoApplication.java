package com.example.demo;

import com.example.demo.bootstrap.SpringbootJavaFxApplication;
import javafx.application.Application;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.example.demo.antlr.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		Application.launch(SpringbootJavaFxApplication.class, args);
	}

	@Bean
	public FxWeaver fxWeaver(ConfigurableApplicationContext context) {
		return new SpringFxWeaver(context);
	}
}
