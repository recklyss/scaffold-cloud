<#assign base=request.contextPath />
<#import "../spring.ftl" as spring/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <link type="text/css" href="${base}/static/static/public/css/font.css" rel="stylesheet"/>
    <link type="text/css" href="${base}/static/static/public/css/main.css" rel="stylesheet"/>
    <title>${title!''}</title>
    <!-- FontAwesome字体图标 -->
    <link type="text/css" href="${base}/static/static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- layui框架样式 -->
    <link type="text/css" href="${base}/static/static/plugins/layui/css/layui.css" rel="stylesheet"/>
    <!-- jQuery相关引用 -->
    <script type="text/javascript" src="${base}/static/static/plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/static/plugins/jquery/jquery.cookie.js"></script>
    <!-- TopJUI框架配置 -->
    <script type="text/javascript" src="${base}/static/static/public/js/topjui.config.js"></script>
    <!-- TopJUI框架核心-->
    <script type="text/javascript" src="${base}/static/topjui/js/topjui.core.min.js"></script>
    <!-- TopJUI中文支持 -->
    <script type="text/javascript" src="${base}/static/topjui/js/locale/topjui.lang.${Session["language"]!"zh_CN"}.js"></script>
    <!-- layui框架js -->
    <script src="${base}/static/static/plugins/layui/layui.js" charset="utf-8"></script>
    <style>
        body {
            margin: 10px;
        }

        blockquote p {
            padding: 5px;
        }

        .layui-table {
            margin-top: 0 !important;
        }

        .layui-elem-quote {
            margin-bottom: 0 !important;
        }

        .layui-table {
            margin-top: 0;
            border-left: 5px solid #e2e2e2 !important;
        }

        .title .icon-new1 {
            margin-left: 10px;
            color: #f00;
        }
    </style>
    <script>
        /* 静态演示中获取_ctx，动态演示非必须 开始 */
        var _ctx = "${base}";
        /* 静态演示中获取_ctx，动态演示非必须 结束 */
    </script>
</head>