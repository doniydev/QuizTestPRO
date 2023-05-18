package com.company.backend.model;

public record Answer(String answer, boolean correct) {
    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                ", correct=" + correct +
                '}';
    }
}
