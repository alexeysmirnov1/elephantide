package com.example.demo.ide.Infrastructure.Stubs;

import com.example.demo.ide.Domain.Project.Contracts.ComposerJsonStubContract;
import org.springframework.stereotype.Component;

@Component
public class ComposerJson implements ComposerJsonStubContract {
    @Override
    public String content() {
        return "{" + "\n" +
            "    \"project\":\"MyProject\"" + "\n" +
            "}";
    }
}
