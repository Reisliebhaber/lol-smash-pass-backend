package com.example.entities;
//set attributes to public cause its necessary for Serialization, there might be better alternative solutions
public class ChampionDTO {
    public String id;
    public int key;
    public String name;
    public String title;
    public String blurb;
    public String img;

    public ChampionDTO(Champion champion, String img) {
        this.id = champion.getId();
        this.key = champion.getKey();
        this.name = champion.getName();
        this.title = champion.getTitle();
        this.blurb = champion.getBlurb();
        this.img = img;
    }

    public ChampionDTO() {

    }
}


