package cn.com.alien.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Root on 2017/6/25.
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public void index(HttpServletResponse response) {
        try {
            response.getWriter().print("Hi!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
