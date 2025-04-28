package com.example.demo.ide.Infrastructure.Controllers.Editor;

import com.example.demo.ide.Presentation.Editor.Views.DatabaseView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DatabaseViewModel extends DatabaseView {
    public ArrayList<String> tabs = new ArrayList<>();
}
