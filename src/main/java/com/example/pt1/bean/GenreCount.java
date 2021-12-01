package com.example.pt1.bean;

public class GenreCount {
    private String Genre;
    private int Count;

    public GenreCount(String genre, int count) {
        Genre = genre;
        Count = count;
    }

    public String Genrename() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
