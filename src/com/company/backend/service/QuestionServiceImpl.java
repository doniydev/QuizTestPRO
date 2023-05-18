package com.company.backend.service;

import com.company.backend.dto.QuestionDTO;
import com.company.backend.model.Answer;
import com.company.backend.model.Question;

import java.sql.Array;
import java.util.Arrays;

public class QuestionServiceImpl implements QuestionService {
    private static int size = 0;

    static {
        questions[size++] = new Question("10*5 = ?", new Answer[]{
                new Answer("50", true),
                new Answer("60", false),
                new Answer("40", false),
                new Answer("55", false)
        });
        questions[size++] = new Question("55*5 = ?", new Answer[]{
                new Answer("505", false),
                new Answer("555", false),
                new Answer("525", true),
                new Answer("550", false)
        });
        questions[size++] = new Question("10*79 = ?", new Answer[]{
                new Answer("675", false),
                new Answer("790", true),
                new Answer("405", false),
                new Answer("551", false)
        });
        questions[size++] = new Question("11*11 = ?", new Answer[]{
                new Answer("121", true),
                new Answer("60", false),
                new Answer("40", false),
                new Answer("55", false)
        });
    }

    @Override
    public String addQuestion(QuestionDTO questionDTO) {
        if (size == questions.length) return "Question list is full";
        String check = checkParam(questionDTO);
        if (!check.equalsIgnoreCase("ok")) return check;
        Answer[] answers ={
            new Answer(questionDTO.v1(),false),
            new Answer(questionDTO.v2(),false),
            new Answer(questionDTO.v3(),false),
            new Answer(questionDTO.correctAnswer(),true),
        };
        questions[size++] = new Question(questionDTO.question(),answers);
        return "Added new question";

    }

    private String checkParam(QuestionDTO questionDTO) {
        if (questionDTO == null) return "Data is required";

        String question = questionDTO.question();
        if (question == null || question.isBlank()) return "Question is required";

        String correctAnswer = questionDTO.correctAnswer();
        if (correctAnswer == null || correctAnswer.isBlank()) return "Correct answer is required";

        String v1 = questionDTO.v1();
        if (v1 == null || v1.isBlank()) return "v1 is required";

        String v2 = questionDTO.v2();
        if (v2 == null || v2.isBlank()) return "v2 is required";

        String v3 = questionDTO.v3();
        if (v3 == null || v3.isBlank()) return "v3 is required";

        if (correctAnswer.equalsIgnoreCase(v1) || correctAnswer.equalsIgnoreCase(v2)
                || correctAnswer.equalsIgnoreCase(v3)) return "Multiple correct answers";

        return "ok";
    }

    @Override
    public String deleteQuestion(Integer index) {

        if (index < 1 || index > size) return "Wrong index";

        for (int i = index - 1; i < size - 1; i++) {
            questions[i] = questions[i + 1];
        }
        questions[--size] = null;

        return "Question deleted";
    }

    @Override
    public String updateQuestion(Integer index, QuestionDTO questionDTO) {
        if (index < 1 || index > size) return "Wrong index";

        String check = checkParam(questionDTO);
        if (!check.equalsIgnoreCase("ok")) return check;

        Answer[] answers = {
                new Answer(questionDTO.v1(), false),
                new Answer(questionDTO.v2(), false),
                new Answer(questionDTO.v3(), false),
                new Answer(questionDTO.correctAnswer(), true)
        };
        questions[index - 1] = new Question(questionDTO.question(), answers);

        return "Question updated";
    }

    @Override
    public Question[] getQuestions() {
        return Arrays.copyOf(questions, size);
    }
}
