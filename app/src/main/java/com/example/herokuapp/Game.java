package com.example.herokuapp;

/**
 * Created by James on 2/24/2016.
 */
public class Game {
    //TODO make the game model

    /*
    {
    "id": "56cb6bbb0fb67b2e1bf6bcee",
    "name": "Fallout 5",
    "platform": "PlayStation 4",
    "genre": "Role-Playing",
    "__v": 0
  }
    */

    private String id;
    private String name;
    private String platform;
    private String genre;
    private int __v;

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
