<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/11/27
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.0.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            var max = 10000;
            for(var i=1;i<=max;i++){
                $.post({
                    url: "./user/grap.do?redPacketId=2&userId=" + i,
                    success:function(result){

                    }
                });
            }
        });


    </script>
</head>
<body>
    hello.
</body>
</html>
