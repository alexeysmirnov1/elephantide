package com.example.demo.database.postgresql.Presentation.Views;

import com.example.demo.ide.UI.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class DatabaseView {
    @Autowired
    protected ConfigurableApplicationContext context;

    @Autowired
    protected Stage stage;
}
