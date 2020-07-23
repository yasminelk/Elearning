/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Mark;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.MarkService;

/**
 *
 * @author ISLEM
 */
@Controller
public class MarkController {

    @RequestMapping("/marks")
    public ModelAndView marks(HttpSession session, @RequestParam("direction") String direction, HttpServletResponse response) throws IOException, Exception {
        
        ModelAndView mv = new ModelAndView();
        MarkService markService = new MarkService();
        List<Mark> marks = markService.getCoursMarks(direction, session.getAttribute("username").toString());
        
        if(marks == null){
            response.sendRedirect("home");
            return null;
        } else {
            mv.setViewName("marks");
            mv.addObject("marks",marks);
            mv.addObject("coursName",direction);
        }
        
        return mv;
    }
    

}
