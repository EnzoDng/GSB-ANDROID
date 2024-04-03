package com.example.gsb;

import java.sql.Date;

public class Mouvement {
    public String code;
    public String date;
    public String lib;
    public int update;

    public Mouvement(String code, String date, String lib, int update) {
        this.code = code;
        this.date = date;
        this.lib = lib;
        this.update = update;
    }

    public Mouvement() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }
}
