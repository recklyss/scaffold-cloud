<#assign base=request.contextPath />
<#import "../spring.ftl" as spring/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content='easyui,jui,jquery easyui,easyui demo,easyui中文'/>
    <title>${title!''}</title>
    <!-- 浏览器标签图片 -->
    <link rel="shortcut icon" href="${base}/static/topjui/image/favicon.ico"/>
    <!-- TopJUI框架样式 -->
    <link type="text/css" href="${base}/static/topjui/css/topjui.core.min.css" rel="stylesheet">
    <link type="text/css" href="${base}/static/topjui/themes/default/topjui.red.css" rel="stylesheet" id="dynamicTheme"/>
    <!-- FontAwesome字体图标 -->
    <link type="text/css" href="${base}/static/static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- jQuery相关引用 -->
    <script type="text/javascript" src="${base}/static/static/plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/static/plugins/jquery/jquery.cookie.js"></script>
    <!-- TopJUI框架配置 -->
    <script type="text/javascript" src="${base}/static/static/public/js/topjui.config.js"></script>
    <!-- TopJUI框架核心 -->
    <script type="text/javascript" src="${base}/static/topjui/js/topjui.core.min.js"></script>
    <!-- TopJUI中文支持 -->
    <script type="text/javascript" src="${base}/static/topjui/js/locale/topjui.lang.${Session["language"]!"zh_CN"}.js"></script>
    <!-- 首页js -->
    <script type="text/javascript" src="${base}/static/static/public/js/topjui.index.js" charset="utf-8"></script>
    <!-- layui框架js -->
    <script src="${base}/static/static/plugins/layui/layui.js" charset="utf-8"></script>
    <!-- 自定义js -->
    <script type="text/javascript" src="${base}/static/common/js/self.js" charset="utf-8"></script>

    <style type="text/css">
        /* right */
        .top_right {
            /*width: 748px;*/
        }

        /* top_link */
        .top_link {
            padding-top: 24px;
            height: 26px;
            line-height: 26px;
            padding-right: 35px;
            text-align: right;
        }

        .top_link i {
            color: #686868;
        }

        .top_link span, .top_link a {
            color: #46AAFE;
        }

        .top_link a {
            font-size: 13px;
        }

        .top_link a:hover {
            text-decoration: underline;
        }

        .nav_bar {
            position: relative;
            z-index: 999;
            color: #333;
            margin-right: 10px;
            height: 50px;
            line-height: 50px;
        }

        .nav_bar ul {
            padding: 0;
        }

        .nav {
            position: relative;
            margin: 0 auto;
            font-family: "Microsoft YaHei", SimSun, SimHei;
            font-size: 14px;
        }

        .nav a {
            color: #333;
        }

        .nav h3 {
            font-size: 100%;
            font-weight: normal;
            height: 50px;
            line-height: 50px;
        }

        .nav h3 a {
            display: block;
            padding: 0 20px;
            text-align: center;
            font-size: 14px;
            color: #fff;
            height: 50px;
            line-height: 50px;
        }

        .nav .m {
            float: left;
            position: relative;
            z-index: 1;
            height: 50px;
            line-height: 50px;
            list-style: none;
        }

        .nav .s {
            float: left;
            width: 3px;
            text-align: center;
            color: #D4D4D4;
            font-size: 12px;
            height: 50px;
            line-height: 50px;
            list-style: none;
        }

        .nav .sub, ul.sub {
            display: none;
            position: absolute;
            left: -3px;
            top: 42px;
            z-index: 999;
            width: 128px;
            border: 1px solid #E6E4E3;
            border-top: 0;
            background: #fff;
        }

        .nav .sub li {
            text-align: center;
            padding: 0 8px;
            margin-bottom: -1px;
            list-style: none;
        }

        .nav .sub li a {
            display: block;
            border-bottom: 1px solid #E6E4E3;
            padding: 8px 0;
            height: 28px;
            line-height: 28px;
            color: #666;
        }

        .nav .sub li a:hover {
            color: #1E95FB;
            cursor: pointer;
        }

        .nav .block {
            height: 3px;
            background: #1E95FB;
            position: absolute;
            left: 0;
            top: 47px;
            overflow: hidden;
        }

        .sub {
            padding: 0;
            background: #f5f5f5;
        }

        .sub li {
            padding: 0 8px;
            list-style: none;
        }

        .sub li:hover {
            background: #f3f3f3;
        }

        .sub li a {
            display: block;
            color: #000;
            height: 34px;
            line-height: 34px;
        }

        .sub li a:hover {
            text-decoration-line: none;
        }

        /* 重用类样式 */
        .f_l {
            float: left !important;
        }

        .f_r {
            float: right !important;
        }

        .no_margin {
            margin: 0px !important;
        }

        .no_border {
            border: 0px !important;
        }

        .no_bg {
            background: none !important;
        }

        .clear_both {
            clear: both !important;
        }

        .display_block {
            display: block !important;
        }

        .text_over {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            -o-text-overflow: ellipsis;
            -moz-binding: url('ellipsis.xml#ellipsis');
        }

        /* 重用自定义样式 */
        .w_100 {
            width: 100%;
        }

        .w_95 {
            width: 95%;
        }

        .indextx {
            width: 980px;
            margin: 0 auto;
            margin-top: 10px;
            background: #FFFFFF;
        }

        .w_min_width {
            min-width: 1200px;
        }

        .w_1200 {
            width: 1200px;
        }

        .w_1067 {
            width: 1067px;
        }

        .w_980 {
            width: 980px;
        }

        .header {
            overflow: hidden
        }
        .icon-remove{
            width: 18px;
            height: 20px;
            overflow: hidden;
            vertical-align: top;
            cursor: pointer;
            background: url(../../static/topjui/images/clear.png) center center no-repeat;
            border: 0px;
        }
        .tag-delete{
            width: 15px;
            height: 15px;
            border-radius: 60%;
            position: absolute;
            top: -4px;
            right: -4px;
            text-align: center;
            line-height: 10px;
            color: #fff;
            background-color: #909090;
        }
    </style>

    <script>
        /* 静态演示中获取_ctx，动态演示非必须 开始 */
        var _ctx = "${base}";
        /* 静态演示中获取_ctx，动态演示非必须 结束 */
    </script>
</head>