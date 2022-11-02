package com.span;

public class Team {
    private String name;
    private int points;
    private int score;


    public Team() {
    }

    public Team(String name, int points, int score) {
        this.name = name;
        this.points = points;
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", points='" + getPoints() + "'" +
            ", score='" + getScore() + "'" +
            "}";
    }
  
    
}
