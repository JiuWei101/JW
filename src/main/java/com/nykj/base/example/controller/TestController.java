package com.nykj.base.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nykj.base.example.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Scope("prototype")
public class TestController {

    @Value("#{config['config.testname']}")
    private String testname;

    @Autowired
    private TestService testService;

    private static Logger log = Logger.getLogger(TestController.class);


    @RequestMapping(value = "test", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String myTest(HttpServletRequest request, HttpServletResponse response) {
        JSONObject param = new JSONObject();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject tablethTop = new JSONObject();
        JSONArray responseArray = new JSONArray();

        param.put("begin_time", request.getParameter("begin_time"));
        param.put("end_time", request.getParameter("end_time"));

        tablethTop.put("choose", "选择");
        tablethTop.put("number", "序号");
        tablethTop.put("name", "姓名");
        tablethTop.put("workNum", "工号");
        tablethTop.put("position", "职位");
        tablethTop.put("createTime", "创建时间");
        tablethTop.put("option", "操作");
        responseArray.add(tablethTop);

        for (int i = 0; i < 35; i++) {
            JSONObject reponseObject = new JSONObject();
            reponseObject.put("number", i);
            reponseObject.put("name", i);
            reponseObject.put("workNum", i);
            reponseObject.put("position", i);
            reponseObject.put("createTime", System.currentTimeMillis());
            responseArray.add(reponseObject);
        }

        data.put("dataArray", responseArray);

        json.put("code", "1");
        json.put("msg", "success");
        json.put("param", param);
        json.put("time", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        json.put("data", data);


        log.info("返回数据：" + json.toString());

        return json.toString();
    }
}
