package cat.tecnocampus.webAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);


    /*
    This method is called whenever a EmptyResultDataAccessException is signalled from any of the
    @RequestMapping annotated methods in web controller.
    This method could be in one web controller and would only be executed when a Exception had been signalled from one
    method of the controller.
    */
    @ExceptionHandler(DuplicateKeyException.class)
    public String handleUsernameAlreadyExists(HttpServletRequest request, Exception ex) {

        logger.error("Request: " + request.getRequestURL() + " raised " + ex);

        return "error/usernameAlreadyExists";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleUsernameDoesNotExist(Model model, HttpServletRequest request, Exception ex) {
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("username", url.substring(url.lastIndexOf("/") + 1));
        return "error/usernameDoesNotExist";
    }

}
