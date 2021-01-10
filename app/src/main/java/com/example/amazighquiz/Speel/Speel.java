package com.example.amazighquiz.Speel;

public class Speel {
    private String Categorie, Vraag,
            Ned, Amazigh, Image, Geluid, Score;

    public Speel() {
    }

    public String getVraag() {
        return Vraag;
    }
    public void setVraag(String vraag) {
        Vraag = vraag;
    }

    public String getAmazigh() {
        return Amazigh;
    }
    public void setAmazigh(String amazigh) {
        Amazigh = amazigh;
    }

    public String getImage() {
        return Image;
    }
    public void setImage(String image) {
        Image = image;
    }

    public String getGeluid() {
        return Geluid;
    }
    public void setGeluid(String geluid) {
        Geluid = geluid;
    }

    public String getScore() {
        return Score;
    }
    public void setScore(String score) {
        Score = score;
    }
}
