<#assign base=request.contextPath />
<#import "../spring.ftl" as spring/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">

    <!-- TopJUI框架样式 -->
    <link type="text/css" href="${base}/static/topjui/themes/default/topjui.core.min.css" rel="stylesheet">
    <link type="text/css" href="${base}/static/topjui/themes/default/topjui.blue.css" rel="stylesheet" id="dynamicTheme"/>
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
    <script type="text/javascript" src="${base}/static/static/plugins/layui/layui.js" charset="utf-8"></script>
    <!-- 自定义js -->
    <script type="text/javascript" src="${base}/static/common/js/self.js" charset="utf-8"></script>

    <script>
        /* 静态演示中获取_ctx，动态演示非必须 开始 */
        var _ctx = "${base}";
        /* 静态演示中获取_ctx，动态演示非必须 结束 */
    </script>
    <script type="text/javascript" charset="utf-8" src="${base}/static/static/plugins/ueditor/ueditor.config_default.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${base}/static/static/plugins/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${base}/static/static/plugins/ueditor/ueditor.all.js"> </script>

    <#--覆盖ueditor中的上传连接地址-->
    <script type="text/javascript">
        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function(action) {
            // 这里很重要，很重要，很重要，要和config.json配置中的imageActionName值一样
            if (action == 'uploadimage'
                    || action == 'uploadvideo'
                    || action == 'uploadfile') {
                // 这里调用后端我们写的图片上传接口
                return _ctx + '/sys/upload/ueditorUpload';
            } else {
                return this._bkGetActionUrl.call(this, action);
            }
        }
    </script>

    <style type="text/css">
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
</head>