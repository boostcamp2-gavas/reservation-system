package kgw.reservation.handler;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {
	@ExceptionHandler(BindException.class)
	public String bindhandle(BindException ex) {
		return "error";
	}
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
       return "error";
   }
}
