package com.company.backend.service;

import com.company.backend.dto.QuestionDTO;
import com.company.backend.model.Question;

public interface QuestionService {
    Question[] questions = new Question[10];
    String addQuestion(QuestionDTO questionDTO);
    String deleteQuestion( Integer index);
    String updateQuestion(Integer integer , QuestionDTO questionDTO);

    Question[] getQuestions();
}
