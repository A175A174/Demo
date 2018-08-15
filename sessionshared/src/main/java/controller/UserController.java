package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login(HttpServletResponse response, HttpServletRequest request){
        Jedis jedis = RedisPool.getJedis();
        return request.getSession().getId()+ "<br/>" + jedis.get("kekeke");
    }
}
