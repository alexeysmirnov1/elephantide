package com.example.demo.ide.Infrastructure.Controllers.Welcome;

import com.example.demo.ide.Application.Loading.LoadApplication;
import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Scenes.Welcome.Fork;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadController extends Controller {

    @Autowired
    private LoadApplication loadApplication;

    @FXML
    public void initialize() throws InterruptedException {
//        System.out.println(this.loadApplication.getState());
//        this.loadApplication.start();
//        this.loadApplication.join();
//        System.out.println(this.loadApplication.getState());

    }

    @FXML
    public void toFork() {
        this.stage.switchScene(this.context.getBean(Fork.class).load());
    }
}
