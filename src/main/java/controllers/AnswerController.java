/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Exam;
import entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.AnswerService;
import services.CoursService;
import services.ExamService;
import services.SessionService;

/**
 *
 * @author ISLEM
 */
@Controller
public class AnswerController {

    @RequestMapping("/examanswer")
    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        String paramName = parameterNames.nextElement();
        String value, coursName = null;
        CoursService coursService = new CoursService();
        ExamService examService = new ExamService();
        AnswerService answerService = new AnswerService();
        List<Integer> questionIds, subquestionIds;
        Exam exam;
        
        if (paramName.equals("coursName")) {
            coursName = request.getParameter("coursName");
            if (coursService.coursExist(coursName)) {
                exam = examService.getDao().getExamByCours(coursService.getDao().getCoursByName(coursName).getId());
                questionIds = new ArrayList<>();
                subquestionIds = new ArrayList<>();
                while (parameterNames.hasMoreElements()) {
                    paramName = parameterNames.nextElement();
                    value = request.getParameter(paramName);
                    questionIds.add(Integer.parseInt(paramName));
                    subquestionIds.add(Integer.parseInt(value));
                }
                if (answerService.insertAnswers(exam, questionIds, subquestionIds, (String) user.getUsername())) {
                    System.out.println("Success");
                } else {
                    System.out.println("Failed");
                }
            }
        }

        response.sendRedirect("exampass?coursName=" + coursName);
        return null;

    }

}
