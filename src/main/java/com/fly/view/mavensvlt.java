package com.fly.view;


import com.fly.bean.business.MyStudent;
import com.fly.service.StuBus;
import com.fly.bean.security.Page;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/stu")
public class mavensvlt extends HttpServlet {

    private StuBus stuBus;

    @Override
    public void init() throws ServletException {
        ServletContext context=this.getServletContext();
        WebApplicationContext webApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(context);
        stuBus=webApplicationContext.getBean(StuBus.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        switch (id) {
            case "1":
                Boolean b = stuBus.deleteStudent(2);
                resp.getWriter().print(b);
                break;
            case "2":
                Page page=stuBus.selectStudent(1,10);
                List<MyStudent> list= (List<MyStudent>) page.getData();
                resp.getWriter().print(list);
        }
    }
}
