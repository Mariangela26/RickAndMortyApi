package com.example.rickandmortyapi.clases;

public class Utils {
    //Constantes de los campos de la tapla Personajes
    public static final String TABLE_PERSONAJES = "personajes";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NAME = "name";
    public static final String CAMPO_STATUS = "status";
    public static final String CAMPO_SPECIES = "species";
    public static final String CAMPO_TYPE = "type";
    public static final String CAMPO_GENDER = "gender";
    public static final String CAMPO_URL_IMAGE = "urlImage";
    public static final String CAMPO_URL_CHARACTER = "urlCharacter";
    public static final String CAMPO_CREATED = "created";

    public static final String CREAR_TABLA_PERSONAJE = "CREATE TABLE "+TABLE_PERSONAJES+"("+CAMPO_ID+" INTEGER, "+CAMPO_NAME+" TEXT, "
            +CAMPO_STATUS+ " TEXT, "+CAMPO_SPECIES+" TEXT, "+CAMPO_TYPE+" TEXT, "+CAMPO_GENDER+" TEXT, " + CAMPO_URL_IMAGE+
            " TEXT, "+CAMPO_URL_CHARACTER+" TEXT, "+CAMPO_CREATED+" TEXT)";

}
