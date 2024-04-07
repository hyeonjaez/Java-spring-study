package com.nhnacademy.jaehyeon.exercise5_7;

import java.util.ArrayList;
import java.util.List;

public class InQuestionList {

    private List<InQuestion> questionList;

    public InQuestionList() {
        questionList = new ArrayList<>();
    }

    public void addQuestion(InQuestion inQuestion) {
        this.questionList.add(inQuestion);
    }

    public List<InQuestion> getQuestionList() {
        return questionList;
    }
}
