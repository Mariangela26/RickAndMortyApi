package com.example.rickandmortyapi.clases;

public class Utils {
    //Constantes de los campos de la tapla Personajes
    public static final String TABLE_CHARACTER = "character";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_SPECIES = "species";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_GENDER = "gender";
    public static final String FIELD_URL_IMAGE = "urlImage";
    public static final String FIELD_URL_CHARACTER = "urlCharacter";
    public static final String FIELD_CREATED = "created";
    public static final String FIELD_FAVORITE = "favorite";



    public static final String CREATE_TABLE_CHARACTER = "CREATE TABLE "+ TABLE_CHARACTER +"("+ FIELD_ID +" INTEGER, "+ FIELD_NAME +" TEXT, "
            + FIELD_STATUS + " TEXT, "+ FIELD_SPECIES +" TEXT, "+ FIELD_TYPE +" TEXT, "+ FIELD_GENDER +" TEXT, " + FIELD_URL_IMAGE +
            " TEXT, "+ FIELD_URL_CHARACTER +" TEXT, "+ FIELD_CREATED +" TEXT, "+ FIELD_FAVORITE +" INTEGER)";

}
