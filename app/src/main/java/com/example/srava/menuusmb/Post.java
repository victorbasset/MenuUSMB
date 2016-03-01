package com.example.srava.menuusmb;

/**
 * Created by modenaq on 01/03/2016.
 */
public class Post {
    public String parametreUrl;

    public Post() {
    }

    public Post(String parametre) {
        this.parametreUrl = parametre;
    }

    public String getParametreUrl() {
        return parametreUrl;
    }

    @Override
    public String toString() {
        return "A completer";
    }
}
