package guru.springframework.controllers;

import guru.springframework.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatError(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception.getMessage());
        return modelAndView;
    }
}
