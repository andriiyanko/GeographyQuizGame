package com.example.medve.flagsquizapp.Model;


public class QuestionFlags {
    private int ID;
    private String Image;
    private String AnswerA;
    private String AnswerB;
    private String AnswerC;
    private String AnswerD;
    private String CorrectAnswer;

    public QuestionFlags(int ID, String answerA, String image, String answerB, String answerC, String answerD, String correctAnswer) {
        this.ID = ID;
        AnswerA = answerA;
        Image = image;
        AnswerB = answerB;
        AnswerC = answerC;
        AnswerD = answerD;
        CorrectAnswer = correctAnswer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String answerA) {
        AnswerA = answerA;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public void setAnswerC(String answerC) {
        AnswerC = answerC;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String answerB) {
        AnswerB = answerB;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public void setAnswerD(String answerD) {
        AnswerD = answerD;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

}
