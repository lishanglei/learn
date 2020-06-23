package com.learn.web;

import com.learn.listener.MyHttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/10              lishanglei      v1.0.0           Created
 */
@Controller
@Slf4j
public class UserController {

    private String message ="Hello World";

    @GetMapping("/asd/{name}")
    public String welcome(@PathVariable String name, Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object foo() {
        log.info("打印日志----------------------");
        return  "login";
    }

    @RequestMapping("/index")
    @ResponseBody
    public Object index(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("zxc", "zxc");
        return  "index";
    }

    @RequestMapping("/online")
    @ResponseBody
    public Object online() {
        return  "当前在线人数：" + MyHttpSessionListener.online + "人";
    }
}
