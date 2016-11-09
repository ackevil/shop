package com.huituopin.common.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * @author tangjingyuan
 * @msg.工具类
 * 
 */
public class OutPrint {

    /**
     * json\字符串格式输出,用于前台的异步交互
     * 
     * @author tangjingyuan
     * @create 2014-2-27
     */
    public void write(String result, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write(result);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JSONObject对象格式化后以字符串形式输出
     * 
     * @author tangjingyuan
     * @create 2014-3-01
     */
    public void writejson(JSONObject obj, HttpServletResponse response) {
        // TODO Auto-generated method stub
        String json = obj.toString();
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JSONObject对象格式化后以字符串形式输出
     * 
     * @author tangjingyuan
     * @create 2014-3-01
     */
    public void printString(String obj, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(obj);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JSONObject对象格式化后以字符串形式输出
     * 
     * @author tangjingyuan
     * @create 2014-3-01
     */
    public void printjson(JSONObject obj, HttpServletResponse response) {
        // TODO Auto-generated method stub
        String json = obj.toString();
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向页面输出分页信息
     * 
     * @param totol
     *            :总条数
     * @param end
     *            :终止数
     * @param start
     *            :起始数
     * @param msg
     *            :json对象
     */
    public void writepage(JSONObject msg, String start, String end, String totol, HttpServletResponse response) {
        String aa = msg.get("items").toString();
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Range", "items " + start + "-" + end + "/" + totol + "");
            PrintWriter out = response.getWriter();
            out.write(aa);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
