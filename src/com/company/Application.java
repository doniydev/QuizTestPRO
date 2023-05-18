package com.company;

import com.company.backend.dto.QuestionDTO;
import com.company.backend.model.Answer;
import com.company.backend.model.Question;
import com.company.backend.service.QuestionServiceImpl;
import com.company.backend.service.UserServiceImpl;

import java.util.Arrays;

public class Application{
    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    1. Login
                    2. Register
                    0. Exit
                    """);
            String operation = ScannerUtil.getString("?: ");

            if (operation.equals("0")) break;

            switch (operation) {
                case "1" -> login();
                case "2" -> register();
            }
        }
    }

    public static void login() {
        String username = ScannerUtil.getString("Enter username: ");
        String password = ScannerUtil.getString("Enter password: ");

        UserServiceImpl userService = new UserServiceImpl();
        String response = userService.login(username, password);
        System.out.println(response);
        if (response.equals("STUDENT")) {
            studentPage(username);
        } else if (response.equals("TEACHER")) {
            teacherPage(username);
        }
    }

    private static void register() {
        String username = ScannerUtil.getString("Enter username: ");
        String password = ScannerUtil.getString("Enter password: ");

        UserServiceImpl userService = new UserServiceImpl();
        String response = userService.register(username, password);
        System.out.println(response);
    }

    private static void studentPage(String username) {
        System.out.println("Welcome " + username + "!");

        while (true) {
            System.out.println("""
                    1. Start
                    2. Exit
                    """);
            String operation = ScannerUtil.getString("?: ");

            if (operation.equals("2")) break;

            if (operation.equals("1")) {
                QuestionServiceImpl questionService = new QuestionServiceImpl();
                Question[] questions = questionService.getQuestions();
                if (questions.length == 0) {
                    System.out.println("No questions");
                    return;
                }
                String[][] userResults = new String[10][3];
                for (int i = 0; i < questions.length; i++) {
                    System.out.println((i + 1) + ") " + questions[i]);
                    Integer answer = ScannerUtil.getInteger("Enter answer");
                    userResults[i][0] = questions[i].getQuestion();
                    userResults[i][1] = answer.toString();
                    Answer[] answers = questions[i].getAnswers();
                    userResults[i][2] = String.valueOf(answers[answer - 1].correct());
                }
                System.out.println("Result:");
                for (String[] userResult : userResults) {
                    System.out.println(Arrays.toString(userResult));
                }
            }
        }
    }

    private static void teacherPage(String username) {
        System.out.println("Welcome " + username + "!");
        while (true) {
            System.out.println("""
                    1. Add question
                    2. Delete question
                    3. Update question
                    4. Show questions
                    5. Exit
                    """);
            String operation = ScannerUtil.getString("?: ");
            if (operation.equals("5")) break;

            switch (operation) {
                case "1" -> addQuestion();
                case "2" -> deleteQuestion();
                case "3" -> updateQuestion();
                case "4" -> showQuestions();
            }
        }
    }

    private static void addQuestion() {
        String question = ScannerUtil.getString("Enter question's description");
        String correctAns = ScannerUtil.getString("Enter correct answer");
        String wa1 = ScannerUtil.getString("Enter wrong answer 1");
        String wa2 = ScannerUtil.getString("Enter wrong answer 2");
        String wa3 = ScannerUtil.getString("Enter wrong answer 3");

        QuestionServiceImpl questionService = new QuestionServiceImpl();
        String response = questionService.addQuestion(new QuestionDTO(
                question, wa1, wa2, wa3, correctAns
        ));
        System.out.println(response);
    }

    private static void deleteQuestion() {
        showQuestions();
        int index = ScannerUtil.getInteger("Enter question index");

        QuestionServiceImpl questionService = new QuestionServiceImpl();
        String response = questionService.deleteQuestion(index);

        System.out.println(response);
    }

    private static void updateQuestion() {
        showQuestions();
        int index = ScannerUtil.getInteger("Enter question index");
        String question = ScannerUtil.getString("Enter question");
        String correctAns = ScannerUtil.getString("Enter correct answer");
        String wa1 = ScannerUtil.getString("Enter wrong answer 1");
        String wa2 = ScannerUtil.getString("Enter wrong answer 2");
        String wa3 = ScannerUtil.getString("Enter wrong answer 3");

        QuestionServiceImpl questionService = new QuestionServiceImpl();
        QuestionDTO questionDTO = new QuestionDTO(question, wa1, wa2, wa3, correctAns);

        String response = questionService.updateQuestion(index, questionDTO);

        System.out.println(response);
    }

    private static void showQuestions() {
        QuestionServiceImpl questionService = new QuestionServiceImpl();

        Question[] questions = questionService.getQuestions();
        if (questions.length == 0) {
            System.out.println("No any questions");
            return;
        }
        for (int i = 0; i < questions.length; i++) {
            System.out.println((i + 1) + ") " + questions[i]);
        }
    }
}
