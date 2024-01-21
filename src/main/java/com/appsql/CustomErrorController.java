package com.appsql;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        // Add custom error information to the model if needed
        return "error"; // Return the name of your error page template (e.g., "error.html")
    }

/*    @Override
    public String getErrorPath() {
        return "/error";
    }*/
}