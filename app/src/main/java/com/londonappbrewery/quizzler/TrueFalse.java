package com.londonappbrewery.quizzler;


public class TrueFalse {

    private int questionId;

    private boolean correctAnswer;

    public TrueFalse(int questionId, boolean correctAnswer){
        this.questionId = questionId;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionId(){
        return questionId;
    }

    public boolean isCorrectAnswer(){
        return correctAnswer;
    }
}
