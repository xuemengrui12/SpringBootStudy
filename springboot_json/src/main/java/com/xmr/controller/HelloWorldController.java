package com.xmr.controller;

import com.alibaba.fastjson.JSONObject;
import com.xmr.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    //调用方式为url?id=
    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String hello1(@RequestParam("id") Integer id) {
        return "index==" + id;
    }

    //调用方式为：url/{id}
    @RequestMapping(value = "/hello1/{id}", method = RequestMethod.GET)
    public String hello2(@PathVariable("id") Integer id) {
        return "index==" + id;
    }

//    @ResponseBody
    @RequestMapping(value = "/get-user", method = RequestMethod.GET)
    public User getUser() {
        User user = new User();
        user.setName("xxx");
        user.setAge(30);
        return user;
    }

    @RequestMapping(value = "/resp/data", method = RequestMethod.GET)
    public void writeByResp(HttpServletResponse resp) {
        // 将获取的json数据封装一层，然后再返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "HttpServletResponse");

        //写json数据到客户端
        writeJson(resp, result);
    }

    public void writeJson(HttpServletResponse resp, JSONObject json) {
        PrintWriter out = null;
        try {
            //设定类容为json的格式
            resp.setContentType("application/json;charset=UTF-8");
            out = resp.getWriter();
            //写到客户端
            out.write(json.toJSONString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @RequestMapping(value = "/json/data", method = RequestMethod.POST)
    public String getByJSON(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data", jsonParam);

        return result.toJSONString();
    }

    @RequestMapping(value = "/request/data", method = RequestMethod.POST)
    public String getByRequest(HttpServletRequest request) {

        //获取到JSONObject
        JSONObject jsonParam = getJSONParam(request);

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "success");
        result.put("method", "request");
        result.put("data", jsonParam);

        return result.toJSONString();
    }

    public JSONObject getJSONParam(HttpServletRequest request){
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
            // 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }
}