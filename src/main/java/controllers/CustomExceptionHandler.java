/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author ISLEM
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final String handleAllExceptions(Exception ex, WebRequest request, HttpServletResponse response) throws IOException {
        
        ex.printStackTrace();
        System.out.println("spring.CustomExceptionHandler.handleAllExceptions()");
        response.sendRedirect("500.html");
        return null;
    }
}
