package com.example.demo.docker.Infrastructere.Commands;

import com.example.demo.docker.Infrastructure.Commands.GetStatuses;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class ServicesCommandsTest {
    @Test
    public void testGetAllServicesWithStatuses() {
        var dockerEngine = new GetStatuses();
        try {
            var services = dockerEngine.run();
            System.out.println(services.get(1).ports);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
