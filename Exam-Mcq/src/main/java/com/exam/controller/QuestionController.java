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

    @GetMapping("getByCategoryAndLevel/{category}/{level}")
    public List<Question> getByCatogoryAndLevel(@PathVariable String category, @PathVariable String level) {
        return questionService.getQuestionsByCategoryAndLevel(category, level);
    }/*@GetMapping("getByCategoryAndLevel/{category}/{level}")
    public List<Question> getByCatogoryAndLevel(@PathVariable String category, @PathVariable String level) {
        return questionService.getQuestionsByCategoryAndLevel(category, level);
    }*/

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

   /* @GetMapping("/addQuestion")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "addQuestion";
    }*/

    /*@PostMapping("/saveQuestion")
    public String saveQuestion(@ModelAttribute("question") Question question,List<Option> options) {
        questionService.saveQuestion(question,options);
        return "redirect:/mcq/addQuestion";
    }*/

    @PostMapping("/saveQuestion")
    public Question saveQuestion(@RequestBody Question question) {
        questionService.saveQuestion(question);
        return question;
    }

}