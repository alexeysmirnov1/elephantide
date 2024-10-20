package com.example.demo.ide.Common.Contracts;

import java.util.ArrayList;

public interface FileIndexContract {
    void add(String file);

    ArrayList<String> search(String substring);
}
