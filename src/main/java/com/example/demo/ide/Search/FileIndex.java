package com.example.demo.ide.Search;

import com.example.demo.ide.Common.Contracts.FileIndexContract;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIndex implements FileIndexContract {
    private ArrayList<String> files = new ArrayList<>();

    public void add(String file) {
        this.files.add(file);
    }

    public ArrayList<String> search(String substring) {
        ArrayList<String> results = new ArrayList<>();

        Pattern pattern = Pattern.compile(substring, Pattern.CASE_INSENSITIVE);

        for (String file: this.files) {
            Matcher matcher = pattern.matcher(file);
            if (matcher.find()) {
                results.add(file);
            }
        }

        return results;
    }
}
