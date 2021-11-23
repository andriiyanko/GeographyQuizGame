package com.example.medve.flagsquizapp.Model;



public class RankingFamousPlaces {
    private int Id;
    private double Score;

    public RankingFamousPlaces(int id, double score) {
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
