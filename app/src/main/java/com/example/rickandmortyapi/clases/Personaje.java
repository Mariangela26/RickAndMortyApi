package com.example.rickandmortyapi.clases;


import com.example.rickandmortyapi.clases.Origin;

import org.json.JSONArray;

public class Personaje {
    private  int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private com.example.rickandmorty.Clases.Location location;
    private String urlImage;
    private JSONArray episode;
    private String urlCharacter;
    private String created;

    public Personaje() {
    }

    public Personaje(int id, String name, String status, String species, String type, String gender, Origin origin, com.example.rickandmorty.Clases.Location location, String urlImage, JSONArray episode, String urlCharacter, String created) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location = location;
        this.urlImage = urlImage;
        this.episode = episode;
        this.urlCharacter = urlCharacter;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public com.example.rickandmorty.Clases.Location getLocation() {
        return location;
    }

    public void setLocation(com.example.rickandmorty.Clases.Location location) {
        this.location = location;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public JSONArray getEpisode() {
        return episode;
    }

    public void setEpisode(JSONArray episode) {
        this.episode = episode;
    }

    public String getUrlCharacter() {
        return urlCharacter;
    }

    public void setUrlCharacter(String urlCharacter) {
        this.urlCharacter = urlCharacter;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "" + id ;
    }
}
