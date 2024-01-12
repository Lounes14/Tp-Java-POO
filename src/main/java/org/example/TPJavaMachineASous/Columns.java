package org.example.TPJavaMachineASous;

import com.google.gson.Gson;

public class Columns {
    private String[][] columns;

    public Columns(String json) {
        Gson gson = new Gson();
        this.columns = gson.fromJson(json, String[][].class);
    }

    public String[][] getColumns() {
        return columns;
    }
}

