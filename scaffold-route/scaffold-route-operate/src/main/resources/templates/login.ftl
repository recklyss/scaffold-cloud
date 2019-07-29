<#assign base=request.contextPath />
<html>
    <#include "public/head_login.ftl">
<body>
<div class="box">


    <div class="login-box">
        <div class="login-title text-center">
            <span class="flag"><i class="fa fa-user"></i> <@spring.message "sys.userlogin"/></span>
            <h1>
                <small></small>
            </h1>
        </div>
        <div class="login-content ">
            <div class="form">
                <form id="modifyPassword" class="form-horizontal" action="#" method="post">
                    <input type="hidden" id="referer" name="referer" value="<#--${param.referer}-->">
                    <div class="form-group">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" id="username" name="username" class="form-control" placeholder="<@spring.message "sys.username"/>">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" id="password" name="password" class="form-control" placeholder="<@spring.message "sys.password"/>">
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-actions">
                        <div class="col-xs-12 text-center">
                            <button type="button" id="login" class="btn btn-sm btn-success">
                                <span class="fa fa-check-circle"></span> <@spring.message "sys.login"/>
                            </button>
                            <button type="button" id="reset" class="btn btn-sm btn-danger">
                                <span class="fa fa-close"></span> <@spring.message "sys.reset"/>
                            </button>

                        </div>
                    </div>
                    <div class="form-group" style="text-align: center">
                        <a href="javascript:void(0)"  data-toggle="topjui-menubutton" class="btn btn-sm"
                           data-options="iconCls:'fa fa-language'" onclick="changeLanguage('zh_CN')" >中文</a>
                        <a href="javascript:void(0)"  data-toggle="topjui-menubutton" class="btn btn-sm"
                           data-options="iconCls:'fa fa-language'" onclick="changeLanguage('en_US')" >English</a>
                        <#--<a href="javascript:void(0)"  data-toggle="topjui-menubutton" class="btn btn-sm"
                           data-options="iconCls:'fa fa-language'" onclick="changeLanguage('in_ID')" >印尼</a>-->
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <span class="text-danger"><i class="fa fa-warning"></i> <@spring.message "sys.passworderror"/></span>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        window.onload = function () {
            if (window.parent.window != window) {
                window.top.location = "${base}/login";
            }
        }

        $('#password').keyup(function (event) {
            if (event.keyCode == "13") {
                $("#login").trigger("click");
                return false;
            }
        });

        $("#login").on("click", function () {
            submitForm();
        });

        function submitForm() {
            if (navigator.appName == "Microsoft Internet Explorer" &&
                    (navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE6.0" ||
                            navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE7.0" ||
                            navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE8.0")
            ) {

            } else {
                $.ajax({
                    type: 'POST',
                    url:  _ctx + '/login/check?username='+$("#username").val()+"&password="+$("#password").val(),
                    contentType: "application/json; charset=utf-8",
                    success: function (data) {
                        if (data.statusCode == 200) {
                            location.href = _ctx +  '/index';
                        } else {
                            $.iMessager.alert(data.title,data.message,'info');
                        }
                    },
                    error: function () {

                    }
                });
            }
        }




        $("#reset").on("click", function () {
            $("#username").val("");
            $("#password").val("");
        });
    });
    function changeLanguage(lang){
        $.ajax({
            type: 'POST',
            url:  _ctx + '/lang/changeLanguage?lang='+lang,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                if (data.statusCode == 200) {
                    window.location.reload();
                } else {
                    $.iMessager.alert(data.title,data.message,'info');
                }
            },
            error: function () {

            }
        });
    }
</script>
</body>
</html>