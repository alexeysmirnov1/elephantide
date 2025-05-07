package com.example.demo.docker.Infrastructure.Commands;

import com.example.demo.docker.Domain.Entities.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.*;
import java.util.ArrayList;

abstract public class Command {
    public Command() {}

    public abstract ArrayList<Service> run() throws IOException;

    protected ArrayList<Service> execute(String command) throws IOException {
        Process process = new ProcessBuilder()
            .command("bash", "-c", command)
            .directory(new File("/root/projects/mdat"))
            .redirectErrorStream(true)
            .start();

        ArrayList<Service> array = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
//            if(line.startsWith("{")) {
//                JsonElement root = new JsonParser().parse(line);
//
//                ArrayList<String> ports = new ArrayList<>();
//                for (JsonElement jsonPort: root.getAsJsonObject().get("Publishers").getAsJsonArray()) {
//                    String url = jsonPort.getAsJsonObject().get("URL").getAsString();
//                    if(url.startsWith(":")) url = "[" + url + "]";
//
//                    String targetPort = jsonPort.getAsJsonObject().get("TargetPort").getAsString();
//                    String publishedPort = jsonPort.getAsJsonObject().get("PublishedPort").getAsString();
//                    String protocol = jsonPort.getAsJsonObject().get("Protocol").getAsString();
//
//                    ports.add(url + ":" + targetPort + "->" + publishedPort + "/" + protocol);
//                }
//
//                array.add(new Service(
//                    root.getAsJsonObject().get("Service").getAsString(),
//                    root.getAsJsonObject().get("Image").getAsString(),
//                    root.getAsJsonObject().get("State").getAsString(),
//                    ports.toString()
//                ));
//            }
        }

        return array;
    }
}
