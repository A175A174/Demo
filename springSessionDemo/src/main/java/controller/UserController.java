package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login(HttpSession session){
        for (int i = 0; i < 100 ; i++) {
            session.setAttribute("kekeke"+i,"hahaha"+i);
        }
        String kekeke = (String) session.getAttribute("kekeke1");
        return kekeke;
    }
}
