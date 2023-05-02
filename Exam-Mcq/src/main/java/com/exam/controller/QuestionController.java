package com.exam.controller;

import com.exam.entity.Question;
import com.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@Controller
@RequestMapping("/mcq")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
/*
    @GetMapping("getByCategoryAndLevel/{category}/{level}")
    public List<Question> getByCatogoryAndLevel(@PathVariable String category, @PathVariable String level) {
        return questionService.getQuestionsByCategoryAndLevel(category, level);
    }

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/startExam")
    public String startExam(Model model,@PathVariable String category,@PathVariable String level) {

        List<Question> questions = questionService.getQuestionsByCategoryAndLevel(category,level);
        model.addAttribute("questions", questions);
        return "exam";
    }

    @PostMapping("/submitExam")
    public String submitExam(@RequestParam Map<String, String> answers, Model model) {
        int totalMarks = 0;
        for (String questionId : answers.keySet()) {
            Long id = Long.parseLong(questionId);
            String selectedOption = answers.get(questionId);
            Question question = questionService.getQuestionById(id);
            Boolean correctOption = question.getIsCorrectAnswer();
            if (selectedOption.equals(correctOption.getClass())) {
                question.setIsCorrectAnswer(true);
                totalMarks += question.getTotalMarks();
            }
        }
        model.addAttribute("totalMarks", totalMarks);
        return "result";
    }

   *//* @GetMapping("/addQuestion")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "addQuestion";
    }*//*

    *//*@PostMapping("/saveQuestion")
    public String saveQuestion(@ModelAttribute("question") Question question,List<Option> options) {
        questionService.saveQuestion(question,options);
        return "redirect:/mcq/addQuestion";
    }*//*
    @GetMapping("/startExam")
    public String startExam(@RequestParam(required = false) String category,
                            @RequestParam(required = false) String level,
                            Model model) {

        // Check if category and level are present in the request parameters
        if (category != null && level != null) {
            // Fetch questions from the database based on the selected category and level
            List<Question> questions = questionService.getQuestionsByCategoryAndLevel(category, level);
            model.addAttribute("questions", questions);
        }

        return "exam";
    }

    @PostMapping("/saveQuestion")
    public Question saveQuestion(@RequestBody Question question) {
        questionService.saveQuestion(question);
        return question;
    }*/

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/addQuestion")
    public String addQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "addQuestion";
    }

    @PostMapping("/addQuestion")
    public String addQuestionSubmit(@ModelAttribute("question") Question question) {
        questionService.save(question);
        return "redirect:/addQuestion";
    }

    @GetMapping("/startExam")
    public String startExam(Model model, @RequestParam("category") String category,
                            @RequestParam("level") String level) {
        List<Question> questions = questionService.getQuestionsByCategoryAndLevel(category, level);
        model.addAttribute("questions", questions);
        return "startExam";
    }

    @PostMapping("/submitExam")
    public String submitExam(@RequestParam Map<String, String> answers) {
        int score = examService.calculateScore(answers);
        return "redirect:/result?score=" + score;
    }

    @GetMapping("/result")
    public String showResult(@RequestParam("score") int score, Model model) {
        model.addAttribute("score", score);
        return "result";
    }


}