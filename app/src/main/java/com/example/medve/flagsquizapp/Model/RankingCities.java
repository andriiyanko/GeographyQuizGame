package com.example.medve.flagsquizapp.Model;


public class RankingCities {
    private int Id;
    private double Score;

    public RankingCities(int id, double score) {
        Id = id;
        Score = score;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }
}
