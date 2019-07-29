function onSubmit() {
    var message = {};
    var button = $(this).iLinkbutton("options");
    var form = button.form;
    var formData = new FormData($("#" + form.id)[0]);
    var validate = $("#" + form.id).form("validate");
    if (!validate) {
        $.messager.progress("close");
        return false;
    }

    var e = $("#" + button.mbId).menubutton("options"), grid = e.grid;

    $.ajax({
        async: false,
        cache: false,
        type: "post",
        data: formData,
        url: form.url,
        dataType: 'json',
        contentType: false, //必须
        processData: false, //必须
        beforeSend: function () {
            $.messager.progress({text: "正在操作..."})
        },
        success: function (data) {
            $.messager.progress("close"), refreshGrids(button.reload);
            1 == data.statusCode || 100 == data.statusCode || data.statusCode == topJUI.config.statusCode.success ?
                (message.statusCode = topJUI.config.statusCode.success, message.title = data.title || topJUI.language.message.title.operationTips, message.message = data.message || topJUI.language.message.msg.success) :
                (message.statusCode = topJUI.config.statusCode.failure, message.title = data.title, message.message = data.message)


            if (message.statusCode == topJUI.config.statusCode.success) {
                if ($("#" + button.dialogId).dialog("close"), "object" == typeof grid) if ("datagrid" == grid.type) $("#" + grid.id).datagrid("reload"); else if ("treegrid" == grid.type) {
                    var i = getSelectedRowData(grid.type, grid.id);
                    null == i ? $("#" + grid.id).treegrid("reload") : $("#" + grid.id).treegrid("reload", i[grid.parentIdField])
                }
                refreshGrids(button.reload)
            }
            showMessage(message)
        }
    });
}

$.extend($.fn.iMenubutton.methods.ajaxExport=
    function (a) {
        return a.each(function () {
            ajaxExportHandler(this)
        })

});


$.extend($.fn.iMenubutton.methods.searchGroup=
    function (a) {
        return a.each(function () {
            searchGroupHandler(this)
        })

    });

function ajaxExportHandler(a){
    var b = $.data(a, "menubutton").options, c = getUrl("controller"), d = {
        gridId: "datagrid",
        csvTitle: parent.$("#index_tabs").tabs("getSelected").panel("options").title + "_导出数据_" + getCurrentDatetime("YmdHis"),
        url: b.url ? b.url : c + "exportCSV"
    };
    b = $.extend(!0, d, b);
    var e, f, g, h, i, j = [], k = [];
    if ("object" == typeof b.grid) if (e = b.grid.id, "datagrid" == b.grid.type) {
        f = $("#" + e).datagrid("getColumnFields", !0), g = $("#" + e).datagrid("getColumnFields"), h = f.concat(g);
        for (var l = 0; l < h.length; l++) i = $("#" + e).datagrid("getColumnOption", h[l]), j.push(i.title), 1 == i.hidden || 1 == i.checkbox ? k.push(!0) : k.push(!1)
    } else if ("treegrid" == b.grid.type) {
        f = $("#" + e).treegrid("getColumnFields", !0), g = $("#" + e).treegrid("getColumnFields"), h = f.concat(g);
        for (var m = 0; m < h.length; m++) i = $("#" + e).treegrid("getColumnOption", h[m]), j.push(i.title), 1 == i.hidden || 1 == i.checkbox ? k.push(!0) : k.push(!1)
    }
    for (var n = 0; n < k.length; n++) k[n] && (j.splice(n, 1), h.splice(n, 1), k.splice(n, 1), n--);
    var o = j.join(","),
        p = h.join(",").replace(/,handle/g, "").replace(/handle,/g, "");
    //解决字段带有操作字符串，导出时该列被过滤问题
    if ((o.indexOf(",操作")+3) == o.length){
        o = o.replace(/,操作/g, "").replace(/操作,/g, "");
    }else {
        o = o.replace(/操作,/g, "");
    }
    b.ajaxData = {
        csvTitle: b.csvTitle,
        colName: o,
        fieldName: p
    }
    // 在这里获取到表单查询条件  按照&分割
    var queryForm = $(a).parent().find("form");
    if(queryForm){
        var queryString = $(a).parent().find("form").serializeArray();
        for (var i = 0; i < queryString.length; i++){
            eval("b.ajaxData." + queryString[i].name + "='" + queryString[i].value + "'");
        }
    }
    $.ajax({
        type: "POST",
        url: b.url,
        data: b.ajaxData,
        dataType: "json",
        success: function(data){
            console.log(data);
            if(data.statusCode == 200){
                $.messager.alert("文件导出成功","请到下载管理页面下载文件【" + b.csvTitle+"】");
            }else{
                $.messager.alert("导出出现失败" ,data.message);
            }
        }
    });
}


function searchGroupHandler(a) {
    var b = $.data(a, "menubutton").options;
    "object" == typeof b.grid && getColumnsNameAndField(b.grid.type, b.grid.id);
    var c = '<table id="advanceSearchTable" class="editTable">';
    c += "<tr>", c += '<td style="font-weight: bold;">方式</td>', c += '<td style="font-weight: bold;">左括号</td>', c += '<td style="font-weight: bold;">字段</td>', c += '<td style="font-weight: bold;">条件</td>', c += '<td style="font-weight: bold;">数值</td>', c += '<td style="font-weight: bold;">右括号</td>', c += '<td style="font-weight: bold;">操作</td>', c += "</tr>", c += "<tr>", c += '<td><input type="text" class="join" name="join"></td>', c += '<td><input type="text" class="lb" name="lb"></td>', c += '<td><input type="text" class="field" name="field"></td>', c += '<td><input type="text" class="op" name="op"></td>', c += '<td><input type="text" class="value" name="value"></td>', c += '<td><input type="text" class="rb" name="rb"></td>', c += '<td><a id="addCondition" href="javascript:void(0)"></a>', c += "</td>", c += "</tr>", c += "</table>";
    var d = {
        dialog: {
            id: "advanceSearchDialog",
            title: "组合查询",
            width: 700,
            height: 300,
            modal: !1,
            collapsible: !0,
            minimizable: !1,
            maximized: !1,
            resizable: !0,
            closed: !1,
            closable: !0,
            zIndex: 10,
            iconCls: "fa fa-search",
            content: c,
            buttons: "#advanceSearchDialog-buttons",
            onOpen: function () {
                $(this).trigger(topJUI.eventType.initUI.advanceSearchForm);
                $(".field:last").iCombobox({
                    loadFilter:function(data){
                        var toupjuiGruopSearchModelType = $("#"+b.grid.id+"-toolbar").find(".toupjui-gruop-search-model-type").val();
                        if(toupjuiGruopSearchModelType != undefined && toupjuiGruopSearchModelType!=''){
                            var toupjuiGruopSearchTestJson = eval("(" + toupjuiGruopSearchModelType  + ")");
                            var data1 = [];
                            for(var i in data){
                                if(toupjuiGruopSearchTestJson[data[i].value] !=undefined && data[i].text !=''){
                                    data1.push(data[i]);
                                }
                            }
                            return data1;
                        }
                        return data;
                    },
                    onChange:function(newValue,oldValue){
                        var isTypeDate = false;
                        var toupjuiGruopSearchModelType = $("#"+b.grid.id+"-toolbar").find(".toupjui-gruop-search-model-type").val();
                        if(toupjuiGruopSearchModelType != undefined && toupjuiGruopSearchModelType!=''){
                            var toupjuiGruopSearchTestJson = eval("(" + toupjuiGruopSearchModelType  + ")");
                            if(toupjuiGruopSearchTestJson[newValue] == "java.util.Date"){
                                isTypeDate = true;
                            }
                        }
                        if(isTypeDate){
                            $(this).parent().next().next().children().remove();
                            $(this).parent().next().next().append('<input type="text" class="value" name="value">');
                            $(this).parent().next().next().children("input:first-child").iDatebox({});
                        }else{
                            var optionsData = $("#"+b.grid.id).children("thead:last-child").children("tr:first-child").children();
                            for(var optionId=0;optionId<optionsData.length;optionId++){
                                var valueData = optionsData[optionId].attributes[0].nodeValue;
                                var optionFieldName = valueData.split("',title")[0].split("field:'")[1];
                                if(newValue == optionFieldName){
                                    if(valueData.indexOf("dict")>=0){
                                        var aaa = valueData.substring(valueData.indexOf("formatter: function(value,row,index){ "),valueData.length-1);
                                        var bbb = aaa.replace("formatter: function(value,row,index){ ","");
                                        var ccc = bbb.split(";}");
                                        var dictData =[];
                                        for(var i=0;i<ccc.length;i++){
                                            if(ccc[i]==''){
                                                continue;
                                            }
                                            var ddd = ccc[i].replace("if(value == '","").replace("'){ return '","@@").replace("'","").split("@@");
                                            var eee = {'text':ddd[1],'value':ddd[0]}
                                            dictData.push(eee);
                                        }
                                        $(this).parent().next().next().children().remove();
                                        $(this).parent().next().next().append('<input type="text" class="value" name="value">');
                                        $(this).parent().next().next().children("input:first-child").iCombobox({
                                            textField:'text',
                                            valueField:'value',
                                            data:dictData
                                        });
                                    }else{
                                        $(this).parent().next().next().children().remove();
                                        $(this).parent().next().next().append('<input type="text" class="value" name="value">');
                                        $(this).parent().next().next().children("input:first-child").iTextbox({
                                            width:141
                                        });

                                    }
                                }

                            }
                        }


                    }
                });
            }
        }
    };
    b = $.extend(d, b);
    var e = '<form id="advanceSearchDialog"></form>';
    e += '<div id="advanceSearchDialog-buttons" style="display:none">', e += '<a href="#" id="resetAdvanceSearchForm" data-toggle="topjui-linkbutton" data-options="iconCls:\'icon-reload\',btnCls:\'topjui-btn-green\'">清空</a>', e += '<a href="#" id="submitAdvanceSearchForm" data-toggle="topjui-linkbutton" data-options="iconCls:\'icon-search\'">查询</a>', e += '<a href="#" id="closeAdvanceSearchDialog" data-toggle="topjui-linkbutton" data-options="btnCls:\'topjui-btn-red\'">关闭</a>', e += "</div>", $("body").append(e);
    var f = $("#" + b.dialog.id);
    f.dialog(b.dialog), $("#resetAdvanceSearchForm").linkbutton({
        iconCls: "fa fa-refresh", onClick: function () {
            $("#advanceSearchDialog").iForm('clear');
        }
    }), $("#submitAdvanceSearchForm").linkbutton({
        iconCls: "fa fa-search",
        btnCls: "topjui-btn-orange",
        onClick: function () {
            for (var a = [], c = $("#" + b.dialog.id).serializeArray(), d = c.length / 6, e = 0; e < d; e++) {
                var f = (c[6 * e + 0].name, c[6 * e + 0].value), g = (c[6 * e + 1].name, c[6 * e + 1].value),
                    h = (c[6 * e + 2].name, c[6 * e + 2].value), i = (c[6 * e + 3].name, c[6 * e + 3].value),
                    j = (c[6 * e + 4].name, c[6 * e + 4].value), k = (c[6 * e + 5].name, c[6 * e + 5].value);
                a.push({join: f, lb: g, field: h, op: i, value: j, rb: k})
            }
            loadGrid(a)
        }
    }), $("#closeAdvanceSearchDialog").linkbutton({
        iconCls: "fa fa-close", onClick: function () {
            $("#" + b.dialog.id).dialog("close")
        }
    });
    var g = "<tr>";
    g += '<td><input type="text" class="join" name="join"></td>', g += '<td><input type="text" class="lb" name="lb"></td>', g += '<td><input type="text" class="field" name="field"></td>', g += '<td><input type="text" class="op" name="op"></td>', g += '<td><input type="text" class="value" name="value"></td>', g += '<td><input type="text" class="rb" name="rb"></td>', g += '<td><a class="deleteCondition" href="javascript:void(0)"></a></td></tr>',
     $("#addCondition").on("click", function () {
        $("#advanceSearchTable").append(g), $(this).trigger(topJUI.eventType.initUI.advanceSearchForm);
        $(".field:last").iCombobox({
            loadFilter:function(data){
                var toupjuiGruopSearchModelType = $("#"+b.grid.id+"-toolbar").find(".toupjui-gruop-search-model-type").val();
                if(toupjuiGruopSearchModelType != undefined && toupjuiGruopSearchModelType!=''){
                    var toupjuiGruopSearchTestJson = eval("(" + toupjuiGruopSearchModelType  + ")");
                    var data1 = [];
                    for(var i in data){
                        if(toupjuiGruopSearchTestJson[data[i].value] !=undefined && data[i].text !=''){
                            data1.push(data[i]);
                        }
                    }
                    return data1;
                }
                return data;
            },
            onChange:function(newValue,oldValue){
                var optionsData = $("#"+b.grid.id).children("thead:last-child").children("tr:first-child").children();
                var toupjuiGruopSearchModelType = $("#"+b.grid.id+"-toolbar").find(".toupjui-gruop-search-model-type").val();
                for(var optionId=0;optionId<optionsData.length;optionId++){
                    var isTypeDate = false;
                    if(toupjuiGruopSearchModelType != undefined && toupjuiGruopSearchModelType!=''){
                        var toupjuiGruopSearchTestJson = eval("(" + toupjuiGruopSearchModelType  + ")");
                        if(toupjuiGruopSearchTestJson[newValue] == "java.util.Date"){
                            isTypeDate = true;
                        }
                    }
                    if(isTypeDate){
                        $(this).parent().next().next().children().remove();
                        $(this).parent().next().next().append('<input type="text" class="value" name="value">');
                        $(this).parent().next().next().children("input:first-child").iDatebox({});
                    }else {
                        var valueData = optionsData[optionId].attributes[0].nodeValue;
                        var optionFieldName = valueData.split("',title")[0].split("field:'")[1];
                        if (newValue == optionFieldName) {
                            if (valueData.indexOf("dict") >= 0) {
                                var aaa = valueData.substring(valueData.indexOf("formatter: function(value,row,index){ "),valueData.length-1);
                                var bbb = aaa.replace("formatter: function(value,row,index){ ","");
                                var ccc = bbb.split(";}");
                                var dictData =[];
                                for(var i=0;i<ccc.length;i++){
                                    if(ccc[i]==''){
                                        continue;
                                    }
                                    var ddd = ccc[i].replace("if(value == '","").replace("'){ return '","@@").replace("'","").split("@@");
                                    var eee = {'text':ddd[1],'value':ddd[0]}
                                    dictData.push(eee);
                                }
                                $(this).parent().next().next().children().remove();
                                $(this).parent().next().next().append('<input type="text" class="value" name="value">');
                                $(this).parent().next().next().children("input:first-child").iCombobox({
                                    textField:'text',
                                    valueField:'value',
                                    data:dictData
                                });
                            } else {
                                $(this).parent().next().next().children().remove();
                                $(this).parent().next().next().append('<input type="text" class="value" name="value">');
                                $(this).parent().next().next().children("input:first-child").iTextbox({
                                    width: 141
                                });
                            }
                        }
                    }
                }
            }
        });
    })
}

$.extend($.fn.validatebox.defaults.rules, {
    CHS: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入汉字'
    },
    english : {// 验证英语
        validator : function(value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message : '请输入英文'
    },
    ip : {// 验证IP地址
        validator : function(value) {
            return /\d+\.\d+\.\d+\.\d+/.test(value);
        },
        message : 'IP地址格式不正确'
    },
    ZIP: {
        validator: function (value, param) {
            return /^[0-9]\d{5}$/.test(value);
        },
        message: '邮政编码不存在'
    },
    QQ: {
        validator: function (value, param) {
            return /^[1-9]\d{4,10}$/.test(value);
        },
        message: 'QQ号码不正确'
    },
    mobile: {
        validator: function (value, param) {
            return /^(?:13\d|15\d|18\d|17\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
        },
        message: '手机号码不正确'
    },
    tel:{
        validator:function(value,param){
            return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
        },
        message:'电话号码不正确'
    },
    mobileAndTel: {
        validator: function (value, param) {
            return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
        },
        message: '请正确输入电话号码'
    },
    number: {
        validator: function (value, param) {
            return /^[0-9]+.?[0-9]*$/.test(value);
        },
        message: '请输入数字'
    },
    money:{
        validator: function (value, param) {
            return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
        },
        message:'请输入正确的金额'

    },
    mone:{
        validator: function (value, param) {
            return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
        },
        message:'请输入整数或小数'

    },
    integer:{
        validator:function(value,param){
            return /^[+]?[1-9]\d*$/.test(value);
        },
        message: '请输入最小为1的整数'
    },
    integ:{
        validator:function(value,param){
            return /^[+]?[0-9]\d*$/.test(value);
        },
        message: '请输入整数'
    },
    range:{
        validator:function(value,param){
            if(/^[1-9]\d*$/.test(value)){
                return value >= param[0] && value <= param[1]
            }else{
                return false;
            }
        },
        message:'输入的数字在{0}到{1}之间'
    },
    minLength:{
        validator:function(value,param){
            return value.length >=param[0]
        },
        message:'至少输入{0}个字'
    },
    maxLength:{
        validator:function(value,param){
            return value.length<=param[0]
        },
        message:'最多{0}个字'
    },
    minNumber:{
        validator:function(value,param){
            return value >param[0]
        },
        message:'输入的数字应大于{0}'
    },
    maxNumber:{
        validator:function(value,param){
            return value<=param[0]
        },
        message:'输入的数字应小于等于{0}'
    },
    //select即选择框的验证
    selectValid:{
        validator:function(value,param){
            if(value == param[0]){
                return false;
            }else{
                return true ;
            }
        },
        message:'请选择'
    },
    idCode:{
        validator:function(value,param){
            return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
        },
        message: '请输入正确的身份证号'
    },
    loginName: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value);
        },
        message: '登录名称只允许汉字、英文字母、数字及下划线。'
    },
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次输入的字符不一至'
    },
    englishOrNum : {// 只能输入英文和数字
        validator : function(value) {
            return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
        },
        message : '请输入英文、数字、下划线或者空格'
    },
    xiaoshu:{
        validator : function(value){
            return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
        },
        message : '最多保留两位小数！'
    },
    ddPrice:{
        validator:function(value,param){
            if(/^[1-9]\d*$/.test(value)){
                return value >= param[0] && value <= param[1];
            }else{
                return false;
            }
        },
        message:'请输入1到100之间正整数'
    },
    jretailUpperLimit:{
        validator:function(value,param){
            if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
                return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
            }else{
                return false;
            }
        },
        message:'请输入0到100之间的最多俩位小数的数字'
    },
    rateCheck:{
        validator:function(value,param){
            if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
                return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
            }else{
                return false;
            }
        },
        message:'请输入0到1000之间的最多俩位小数的数字'
    },
    urlCheck : {// 验证英语
        validator : function(value) {
            return /^(http:\/\/)|(https:\/\/){1}[\S\s]*$/i.test(value);
        },
        message : '请输入完整的带http://或者https://前缀的url'
    }
});