package com.manasses.school.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {
@ExceptionHandler(Exception.class)
//@ExceptionHandler will register the given method for a given
// exception type, so that the ControllerAdvice can invoke this method
//logic if a given exception type is throen indide the web application

public ModelAndView exceptionHandler(Exception exception){
    ModelAndView errorPage=new ModelAndView();
    errorPage.setViewName("error");
    errorPage.addObject("errormsg",exception.getMessage());
    return errorPage;
}
}
