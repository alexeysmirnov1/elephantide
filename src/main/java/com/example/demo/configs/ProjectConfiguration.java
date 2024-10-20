package com.example.demo.configs;

import com.example.demo.ide.Common.Contracts.FileIndexContract;
import com.example.demo.ide.Domain.Project.Contracts.ComposerJsonStubContract;
import com.example.demo.ide.Infrastructure.Stubs.ComposerJson;
import com.example.demo.ide.Search.FileIndex;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ProjectConfiguration {
    @Bean
    public ComposerJsonStubContract composerJsonStub() {
        return new ComposerJson();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public FileIndexContract fileIndex() {
        return new FileIndex();
    }
}
