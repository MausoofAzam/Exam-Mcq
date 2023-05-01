package com.exam.service;

import com.exam.entity.Option;
import com.exam.entity.Question;
import com.exam.repository.OptionRepository;
import com.exam.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionRepository optionRepository;

    public void saveQuestion(Question question) {
        // Set the options for the question
        Option options = new Option();
        options.setQuestion(question.getOptions().getQuestion());
        options.setTitle(question.getOptions().getTitle());
        options.setOption1(question.getOptions().getOption1());
        options.setOption2(question.getOptions().getOption2());
        options.setOption3(question.getOptions().getOption3());
        options.setOption4(question.getOptions().getOption4());
        question.setOptions(options);
        // Save the question to the database
        questionRepository.save(question);
    }

    public List<Question> getQuestionsByCategoryAndLevel(String category, String level) {
        List<Question> questions = questionRepository.findByCategoryAndLevel(category, level);

        return questions;
    }

    public Question getQuestionById(Long id) {
        // Call the repository method to get the question by id
        Optional<Question> questionOptional = questionRepository.findById(id);

        // Check if the question exists in the database
        if (questionOptional.isPresent()) {
            return questionOptional.get();
        } else {
            throw new EntityNotFoundException("Question with id " + id + " not found.");
        }
    }

   /* public boolean performMCQExam(Long questionId, Long optionId) {
        // Getting the question by id
        Question question = getQuestionById(questionId);

        // Checking if the selected option is the correct answer
        boolean isCorrectAnswer = false;
        for (Option option : question.getOptions()) {
            if (option.getId().equals(optionId) && option.isCorrect()) {
                isCorrectAnswer = true;
                break;
            }
        }

        // Setting the isCorrectAnswer flag in the question entity
        question.setIsCorrectAnswer(isCorrectAnswer);
        questionRepository.save(question);

        return isCorrectAnswer;
    }*/


}