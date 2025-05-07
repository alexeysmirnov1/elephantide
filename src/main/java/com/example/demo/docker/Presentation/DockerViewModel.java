package com.example.demo.docker.Presentation;

import com.example.demo.docker.Domain.Entities.Service;
import com.example.demo.docker.Infrastructure.Commands.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class DockerViewModel {
    @FXML
    private GridPane table;

    @FXML
    public void initialize() {
        var dockerEngine = new GetServices();

        try {
            ArrayList<Service> services = dockerEngine.run();

            int i = 1;
            for (Service service: services) {
                Label name = new Label();
                name.setText(service.name);
                name.setTextFill(Paint.valueOf("white"));

                Label image = new Label();
                image.setText(service.image);
                image.setTextFill(Paint.valueOf("white"));

                Text ports = new Text();
                ports.setText(service.ports);
//                ports.setTextFill(Paint.valueOf("white"));
                ports.setFill(Paint.valueOf("white"));

                Label status = new Label();
                status.setText(service.status);
                status.setTextFill(Paint.valueOf("white"));
                status.setOnMouseClicked(event -> this.changeServiceState(service.name, status, ports));

                this.table.addRow(i, name, image, status, ports);
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeServiceState(String service, Label state, Text ports) {
        System.out.println("change service status (" + service + ") from " + state.getText());

        switch(state.getText()) {
            case "exited":
            case "stopped":
                state.setText("waiting...");
                this.dockerUpService(service);
                state.setText("running");
                break;
            case "running":
                state.setText("waiting...");
                this.dockerStopService(service);
                state.setText("stopped");
                break;
        }

        this.updateServicesTable(service, ports);
    }

    private void updateServicesTable(String serviceName, Text ports) {
        var dockerEngine = new GetServices();

        try {
            ArrayList<Service> services = dockerEngine.run();

            for (Service service: services) {
                if(service.name.equals(serviceName)) {
                    ports.setText(service.ports);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void dockerUpAll() {
        UpAllServices services = new UpAllServices();
        try {
            services.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void dockerDownAll() {
        StopAllServices stop = new StopAllServices();
        try {
            stop.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void dockerUpService(String service) {
        UpService up = new UpService(service);
        try {
            up.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void dockerStopService(String service) {
        StopService stop = new StopService(service);
        try {
            stop.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
