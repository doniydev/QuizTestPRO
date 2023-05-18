package com.company.backend.model;

import com.company.backend.GenericClass;

public class Question extends GenericClass {
    private final String question;
    private final Answer[] answers;

    public Question(String question, Answer[] answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(question).append("\n");
        for (int i = 0; i < answers.length; i++) {
            sb.append(i+1).append(". ").append(answers[i].answer()).append('\t');
        }
        return sb.toString();
    }
}
