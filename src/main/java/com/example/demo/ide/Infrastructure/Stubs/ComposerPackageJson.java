package com.example.demo.ide.Infrastructure.Stubs;

import com.example.demo.ide.Domain.Project.Contracts.ComposerJsonStubContract;
import org.springframework.stereotype.Component;

@Component
public class ComposerPackageJson implements ComposerJsonStubContract {
    @Override
    public String content() {
        return "{" + "\n" +
            "    \"name\":\"my/package\"," + "\n" +
            "    \"description\":\"My package\"," + "\n" +
            "    \"license\": \"MIT\"," + "\n" +
            "    \"require\": {" + "\n" +
            "        \"php\": \"^8.0\"" + "\n" +
            "    }," + "\n" +
            "    \"autoload\": {" + "\n" +
            "        \"psr-4\": {" + "\n" +
            "            \"MyPackage\\\\\": \"src/\"" + "\n" +
            "        }" + "\n" +
            "    }," + "\n" +
            "    \"minimum-stability\": \"dev\"," + "\n" +
            "    \"prefer-stable\": true" + "\n" +
            "}";
    }
}
