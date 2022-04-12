package com.example.rickandmortyapi.clases;

public class Origin {
    private String name;
    private String url;

    public Origin(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
