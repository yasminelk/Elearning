/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utils.Calculators;
import Utils.DateUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Cours;
import entities.Exam;
import entities.Mark;
import entities.Question;
import entities.SubQuestion;
import entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.*;

/**
 *
 * @author ISLEM
 */
@Controller
public class ExamController {

    @RequestMapping("/exam")
    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        String coursName = null;

        coursName = request.getParameter("coursName");
        String[] questions = request.getParameterValues("questions");
        String[] points = request.getParameterValues("points");
        String[] answers;
        String answer;
        String date = request.getParameter("startdate") + " " + request.getParameter("starttime") + ":00";

        Exam e = new Exam();

        CoursService coursService = new CoursService();
        ExamService examService = new ExamService();
        QuestionService questionService = new QuestionService();
        SubquestionService subquestionService = new SubquestionService();

        e.setTotalPoints(Calculators.calculateTotalPoints(points));
        e.setStartDate(DateUtility.stringToDateTime(date));
        e.setDuration(Calculators.timeToSeconds(request.getParameter("duration")));

        Cours c = coursService.getDao().getCoursByName(coursName);

        if (c != null) {
            e = examService.insertExam(e, coursService.getDao().getCoursByName(coursName));
        } else {
            response.sendRedirect("exampage?direction=" + coursName);
            return null;
        }
        if (e == null) {
            response.sendRedirect("exampage?direction=" + coursName);
            return null;
        }

        for (int i = 0; i < questions.length; i++) {
            Question q = new Question();
            SubQuestion sq;
            q.setText(questions[i]);
            q.setPoints(Integer.parseInt(points[i]));
            q.setQuestionNumber(i);
            q.setExam(e);
            q = questionService.insertQuestion(q);
            answer = request.getParameter("answer" + i);
            answers = request.getParameterValues("answers" + i);
            if (q != null) {
                for (int j = 0; j < 3; j++) {
                    sq = new SubQuestion();
                    sq.setQuestion(q);
                    sq.setText(answers[j]);
                    if (j == Integer.parseInt(answer)) {
                        sq.setCorrect(true);
                    }
                    subquestionService.insertSubQuestion(sq);
                }
            }
        }

        response.sendRedirect("exampage?direction=" + coursName);
        return null;

    }

    @RequestMapping("/examsubmited")
    public ModelAndView submited(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        mv.setViewName("index");
        String coursName = (String) request.getParameter("coursName");
        List<Question> questions;
        List<ArrayList<SubQuestion>> subQuestions;

        ExamService examService = new ExamService();
        CoursService coursService = new CoursService();
        QuestionService questionService = new QuestionService();
        SubquestionService subquestionService = new SubquestionService();

        Exam exam = examService.getDao().getExamByCours(coursService.getDao().getCoursByName(coursName).getId());
        if (exam != null) {
            questions = questionService.getDao().getQuestionsByExam(exam.getId());
            subQuestions = new ArrayList<>();
            for (Question q : questions) {
                subQuestions.add((ArrayList<SubQuestion>) subquestionService.getDao().getSubQuestionsByQuestion(q.getId()));
            }
            mv.addObject("exam", exam);
            mv.addObject("coursName", coursName);
            mv.addObject("questions", questions);
            mv.addObject("subQuestions", subQuestions);
        }
        mv.setViewName("examsubmited");

        LoginController.refreshSession(request, user);

        return mv;

    }

    @RequestMapping("/exampage")
    public ModelAndView exampage(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        CoursService service = new CoursService();
        ExamService examService = new ExamService();

        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        if (request.getParameter("direction") == null) {
            response.sendRedirect("home");
            return null;
        }

        String direction = request.getParameter("direction");

        if (!user.isTeacher()) {
            response.sendRedirect("exampass?username=" + user.getUsername() + "&coursName=" + direction);
            return null;
        }

        Cours c = service.getDao().getCoursByName(direction);

        if (c != null) {
            if (c.getUser().getUsername().equals(user.getUsername())) {
                if (examService.getDao().getExamByCours(c.getId()) != null) {
                    response.sendRedirect("examsubmited?coursName=" + direction);
                    return null;
                }
                mv.setViewName("exam");
                mv.addObject("coursName", c.getCoursName());
            } else {
                response.sendRedirect("home");
                return null;
            }
        } else {
            response.sendRedirect("home");
            return null;
        }

        LoginController.refreshSession(request, user);

        return mv;
    }

    @RequestMapping("/exampass")
    public ModelAndView exampass(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        mv.setViewName("index");
        String coursName = (String) request.getParameter("coursName");
        List<Question> questions;
        List<ArrayList<SubQuestion>> subQuestions;

        ExamService examService = new ExamService();
        CoursService coursService = new CoursService();
        QuestionService questionService = new QuestionService();
        SubquestionService subquestionService = new SubquestionService();
        mv.addObject("coursName", coursName);

        Exam exam = examService.getDao().getExamByCours(coursService.getDao().getCoursByName(coursName).getId());
        if (exam != null) {
            if (!examService.examPassed(exam, user.getUsername())) {
                Date date = new Date();
                if (date.after(exam.getStartDate())) {
                    if (exam.getStartDate().getTime() + exam.getDuration() * 1000 >= (new Date()).getTime()) {
                        questions = questionService.getDao().getQuestionsByExam(exam.getId());
                        subQuestions = new ArrayList<>();
                        for (Question q : questions) {
                            subQuestions.add((ArrayList<SubQuestion>) subquestionService.getDao().getSubQuestionsByQuestion(q.getId()));
                        }
                        Date expiration = new Date(exam.getStartDate().getTime() + exam.getDuration()*1000);
                        mv.addObject("exam", exam);
                        mv.addObject("questions", questions);
                        mv.addObject("subQuestions", subQuestions);
                        mv.addObject("time", expiration);
                    } else {
                        mv.addObject("title", "Exam Expired ( 0 Pts / " + exam.getTotalPoints() + " Pts )");
                        mv.addObject("msg", "The exam of " + coursName + " has been expired and u missed it your mark is 0 of " + exam.getTotalPoints());
                    }
                } else {
                    mv.addObject("title", "Exam Not yet Started");
                    mv.addObject("msg", "The exam of class " + coursName + " is not yet started, this exam is scheduled on " + exam.getStartDate() + ". The exam duration is " + (int) exam.getDuration() / 60 + " mins");
                }
            } else {
                MarkService markService = new MarkService();
                Mark m = markService.studentMark(exam, user.getUsername());
                mv.addObject("title", "Exam Passed");
                mv.addObject("msg", "You have been passed The exam of " + coursName + " And your mark is " + m.getMark() + " of " + m.getTotalPoints());
            }
        } else {
            mv.addObject("title", "Exam is Not yet scheduled");
            mv.addObject("msg", "You will recieve an email when it will be scheduled");
        }
        mv.setViewName("exampass");

        LoginController.refreshSession(request, user);

        return mv;
    }

}
