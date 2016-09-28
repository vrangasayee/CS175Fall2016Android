package edu.sjsu.fall2016.cs175.catsandjokes.data;


public class JokeResponse {
    private String type;
    private JokeModel value;

    public String getJokeString() {
        return value.joke;
    }
}
