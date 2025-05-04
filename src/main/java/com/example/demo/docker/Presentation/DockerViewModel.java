package com.example.demo.docker.Presentation;

import com.example.demo.docker.Domain.Entities.Service;
import com.example.demo.docker.Infrastructure.Commands.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class DockerViewModel {
    @FXML
    private GridPane table;

    @FXML
    public void initialize() {
        System.out.println("docker ");
        var dockerEngine = new GetStatuses();
        try {
            ArrayList<Service> services = dockerEngine.run();

            int i = 1;
            for (Service service: services) {
                Label name = new Label();
                name.setText(service.name);
                name.setTextFill(Paint.valueOf("white"));
                name.setTextAlignment(TextAlignment.LEFT);

                Label image = new Label();
                image.setText(service.image);

                Label status = new Label();
                status.setText(service.status);
                status.setOnMouseClicked(event -> System.out.println("change service status (" + service.name + ")"));

                Label ports = new Label();
                ports.setText(service.ports.toString());

                this.table.addRow(i, name, image, status, ports);
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void docker_up() {
        UpAllServices services = new UpAllServices();
        try {
            services.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    @FXML
    protected void docker_down() {
        StopAllServices stop = new StopAllServices();
        try {
            stop.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    @FXML
    protected void docker_up_app() {
        UpService stop = new UpService("app");
        try {
            stop.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    @FXML
    protected void docker_stop_app() {
        StopService stop = new StopService("app");
        try {
            stop.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    @FXML
    protected void docker_status() {
        GetStatuses statuses = new GetStatuses();
        try {
            statuses.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
}
