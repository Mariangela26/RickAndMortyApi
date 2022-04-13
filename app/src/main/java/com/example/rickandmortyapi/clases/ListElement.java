package com.example.rickandmortyapi.clases;

public class ListElement {

    public String name;
    public String species;
    public String status;
    public String gender;
    public String image;

    public ListElement(String name, String species, String status, String gender) {
        this.name = name;
        this.species = species;
        this.status = status;
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
