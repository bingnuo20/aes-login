<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>登录</title>
        <script src="js/aes.js"></script>
    </head>
    <body>
        <form  id="form">
            用户：<input type="text" name="username" id="username"><br>
            密码：<input type="password" name="password" id="password"><br>
            <button type="button" onclick="doLogin()">提交</button>
        </form>

        <script type="text/javascript">
            function doLogin(){
                var username=document.getElementById('username').value;
                var password=document.getElementById('password').value;

                var key = CryptoJS.enc.Utf8.parse("1qaz2WSX3edc4RFV");//指定一个加密混淆串，后台要与这个保持一致
                var pwd = CryptoJS.AES.encrypt(password, key, {
                    mode: CryptoJS.mode.ECB,
                    padding: CryptoJS.pad.Pkcs7
                });
                //可能有特殊字符，我们用encodeURIComponent处理下
                password=encodeURIComponent(pwd);
                //模拟一个提交
                location.href="login?username="+username+"&password="+password;
            }

        </script>
    </body>
</html>
