<#assign base=request.contextPath />
<#import "../spring.ftl" as spring/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content='easyui,jui,jquery easyui,easyui demo,easyui中文'/>
    <title><@spring.message "sys.userlogin"/></title>
    <!-- 浏览器标签图片 -->
    <link rel="shortcut icon" href="${base}/static/topjui/image/favicon.ico"/>
    <link rel="stylesheet" href="${base}/static/static/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base}/static/static/plugins/font-awesome/css/font-awesome.min.css">
    <!-- TopJUI框架样式 -->
    <link type="text/css" href="${base}/static/topjui/css/topjui.core.min.css" rel="stylesheet">
    <link type="text/css" href="${base}/static/topjui/themes/default/topjui.blue.css" rel="stylesheet" id="dynamicTheme"/>
    <!-- layui框架js -->
    <script src="${base}/static/static/plugins/layui/layui.js" charset="utf-8"></script>

    <style type="text/css">
        html, body {
            height: 100%;
        }

        .box {
            background: url("${base}/static/topjui/images/loginBg.jpg") no-repeat center center;
            background-size: cover;

            margin: 0 auto;
            position: relative;
            width: 100%;
            height: 100%;
        }

        .login-box {
            width: 100%;
            max-width: 500px;
            height: 400px;
            position: absolute;
            top: 50%;

            margin-top: -200px;
            /*设置负值，为要定位子盒子的一半高度*/

        }

        @media screen and (min-width: 500px) {
            .login-box {
                left: 50%;
                /*设置负值，为要定位子盒子的一半宽度*/
                margin-left: -250px;
            }
        }

        .form {
            width: 100%;
            max-width: 500px;
            height: 275px;
            margin: 2px auto 0px auto;
        }

        .login-content {
            border-bottom-left-radius: 8px;
            border-bottom-right-radius: 8px;
            height: 250px;
            width: 100%;
            max-width: 500px;
            background-color: rgba(255, 250, 2550, .6);
            float: left;
        }

        .input-group {
            margin: 30px 0px 0px 0px !important;
        }

        .form-control,
        .input-group {
            height: 40px;
        }

        .form-actions {
            margin-top: 30px;
        }

        .form-group {
            margin-bottom: 0px !important;
        }

        .login-title {
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
            padding: 20px 10px;
            background-color: rgba(0, 0, 0, .6);
        }

        .login-title h1 {
            margin-top: 10px !important;
        }

        .login-title small {
            color: #fff;
        }

        .link p {
            line-height: 20px;
            margin-top: 30px;
        }

        .btn-sm {
            padding: 8px 24px !important;
            font-size: 16px !important;
        }

        .flag {
            position: absolute;
            top: 10px;
            right: 10px;
            color: #fff;
            font-weight: bold;
            font: 14px/normal "microsoft yahei", "Times New Roman", "宋体", Times, serif;
        }
    </style>

    <!-- 引入jQuery -->
    <script type="text/javascript" src="${base}/static/static/plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/static/plugins/jquery/jquery.cookie.js"></script>
    <!-- TopJUI框架配置 -->
    <script type="text/javascript" src="${base}/static/static/public/js/topjui.config.js"></script>
    <!-- TopJUI框架核心-->
    <script type="text/javascript" src="${base}/static/topjui/js/topjui.core.min.js"></script>
    <!-- TopJUI中文支持 -->
    <script type="text/javascript" src="${base}/static/topjui/js/locale/topjui.lang.${Session["language"]!"zh_CN"}.js"></script>
    <!-- layui框架js -->
    <script type="text/javascript" src="${base}/static/static/plugins/layui/layui.js" charset="utf-8"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the error via file:// -->
    <!--[if lt IE 9]>
    <script src="${base}/static/static/plugins/bootstrap/plugins/html5shiv.min.js"></script>
    <script src="${base}/static/static/plugins/bootstrap/plugins/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
        if (navigator.appName == "Microsoft Internet Explorer" &&
                (navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE6.0" ||
                        navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE7.0" ||
                        navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE8.0")
        ) {
        }
    </script>

    <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?71559c3bdac3e45bebab67a5a841c70e";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
    <script>
        /* 静态演示中获取_ctx，动态演示非必须 开始 */
        var _ctx = "${base}";
        /* 静态演示中获取_ctx，动态演示非必须 结束 */
    </script>
</head>