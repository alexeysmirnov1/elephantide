package com.example.demo.docker.Infrastructure.Commands;

import com.example.demo.docker.Domain.Entities.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GetServices {
    public ArrayList<Service> run() throws IOException {
        ArrayList<Service> result = new ArrayList<>();

        JsonElement root = this.loadConfig();
        ArrayList<String> services = this.loadAllServices();
        Map<String, Map<String, String>> running = this.loadRunningServices();

        for (String service: services) {
            result.add(this.makeService(
                service,
                root.getAsJsonObject().get("services").getAsJsonObject().get(service),
                running.getOrDefault(service, new HashMap())
            ));
        }

        return result;
    }

    private JsonElement loadConfig() throws IOException {
        Path tempFilePath = Files.createTempFile(null, ".tmp");
        tempFilePath.toFile().deleteOnExit();

        Process formated = new ProcessBuilder()
                .command("bash", "-c", "docker compose config --format json -o " + tempFilePath.toFile().getPath())
                .directory(new File("/root/projects/mdat"))
                .redirectErrorStream(true)
                .start();

        try {
            formated.waitFor();

            String json = Files.readString(Path.of(tempFilePath.toFile().getPath()));
            return new JsonParser().parse(json);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Map<String, String>> loadRunningServices() throws IOException {
        Map<String, Map<String, String>> services = new HashMap<>();

        Process process = new ProcessBuilder()
                .command("bash", "-c", "docker compose ps --format json")
                .directory(new File("/root/projects/mdat"))
                .redirectErrorStream(true)
                .start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            if(line.startsWith("{")) {
                JsonElement root = new JsonParser().parse(line);

                ArrayList<String> ports = new ArrayList<>();
                for (JsonElement jsonPort: root.getAsJsonObject().get("Publishers").getAsJsonArray()) {
                    String url = jsonPort.getAsJsonObject().get("URL").getAsString();
                    if(url.startsWith(":")) url = "[" + url + "]";

                    String targetPort = jsonPort.getAsJsonObject().get("TargetPort").getAsString();
                    String publishedPort = jsonPort.getAsJsonObject().get("PublishedPort").getAsString();
                    String protocol = jsonPort.getAsJsonObject().get("Protocol").getAsString();

                    String tmpPorts = "";

                    if(!url.isEmpty()) tmpPorts += url + ":";
                    tmpPorts += targetPort;
                    if(!publishedPort.isEmpty() && !publishedPort.equals("0")) tmpPorts += "->" + publishedPort;
                    tmpPorts += "/" + protocol;

                    ports.add(tmpPorts);
                }

                Map<String, String> map = new HashMap<>();
                map.put("state", root.getAsJsonObject().get("State").getAsString());
                map.put("ports", ports.toString());

                services.put(root.getAsJsonObject().get("Service").getAsString(), map);
            }
        }

        return services;
    }

    private ArrayList<String> loadAllServices() throws IOException {
        Process services = new ProcessBuilder()
                .command("bash", "-c", "docker compose config --services")
                .directory(new File("/root/projects/mdat"))
                .redirectErrorStream(true)
                .start();

        ArrayList<String> servicesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(services.getInputStream()))) {
            String service;
            while ((service = reader.readLine()) != null) {
                if (service.startsWith("time")) continue;

                servicesList.add(service);
            }
        }

        Collections.sort(servicesList);
        return servicesList;
    }

    private Service makeService(String serviceName, JsonElement jsonService, Map<String, String> details) {
        JsonElement image = jsonService.getAsJsonObject().get("image");
        String imageName = null;
        if(image == null) {
            imageName = jsonService.getAsJsonObject().get("container_name").getAsString().replace("_", "-");
        } else {
            imageName = image.getAsString();
        }

        String state = details.getOrDefault("state", "stopped");

        String ports = details.getOrDefault("ports", "");

        return new Service(
            serviceName,
            imageName,
            state,
            ports
        );
    }
}
