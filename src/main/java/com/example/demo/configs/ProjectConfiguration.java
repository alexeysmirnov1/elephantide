package com.example.demo.configs;

import com.example.demo.ide.Domain.Project.Contracts.ComposerJsonStubContract;
import com.example.demo.ide.Infrastructure.Stubs.ComposerJson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {
    @Bean
    public ComposerJsonStubContract composerJsonStub() {
        return new ComposerJson();
    }
}
