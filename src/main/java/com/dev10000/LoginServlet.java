package com.dev10000;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //解码还原特殊字符
        String encryptPwd=java.net.URLDecoder.decode(password,"UTF-8");
        String pwd="";
        try {
            //AES解密
            pwd=AesUtil.decrypt(encryptPwd);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(username.equals("admin") && pwd.equals("123456")){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
