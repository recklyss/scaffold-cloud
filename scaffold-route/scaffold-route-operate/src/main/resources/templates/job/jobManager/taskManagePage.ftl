<!DOCTYPE html>
<html>
<#include "../../public/head_tab.ftl"/>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'center',iconCls:'icon-reload',title:'',fit:false,split:true,border:false,bodyCls:'border_left_right'">
        <table data-toggle="topjui-datagrid"
               data-options="id:'datagridId',
                             singleSelect:true,
                             url: _ctx+'/job/jobManager/getJobPageList'">
            <thead>
            <tr>
                <th data-options="field:'id',title:'ID'"></th>
                <th data-options="field:'jobName',title:'<@spring.message "sys.name"/>'"></th>
                <th data-options="field:'jobGroup',title:'<@spring.message "sys.group"/>'"></th>
                <th data-options="field:'jobDescription',title:'<@spring.message "sys.describe"/>'"></th>
                <th data-options="field:'cronExpression',title:'<@spring.message "sys.corn"/>'"></th>
                <th data-options="field:'beanClass',title:'<@spring.message "sys.classname"/>'"></th>
                <th data-options="field:'methodName',title:'<@spring.message "sys.methodname"/>'"></th>
                <th data-options="field:'paramJson',title:'<@spring.message "sys.param"/>'"></th>
                <th data-options="field:'isConcurrentName',title:'<@spring.message "sys.isconcurrent"/>'"></th>
                <th data-options="field:'jobStatusName',title:'<@spring.message "sys.status"/>'"></th>
                <th data-options="field:'startWithrun',title:'<@spring.message "sys.startwithrun"/>',formatter:function(value,row,index){
                        if (value == '0') {
                            return '<@spring.message "sys.no"/>';
                        } else if (value == '1') {
                            return '<@spring.message "sys.yes"/>';
                        }
                    }"></th>
                <th data-options="field:'createTime',title:'<@spring.message "sys.addtime"/>'"></th>
                <th data-options="field:'operate',title:'<@spring.message "sys.operate"/>',sortable:true,formatter:operateFormatter"></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<!-- 表格工具栏 -->
<div id="datagridId-toolbar" style="height: 30px; padding: 4px; text-align: left;"
     data-options="grid:{
        type:'datagrid',
        id:'datagridId'
     }" class="topjui-toolbar">

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#datagridId-toolbar',
       iconCls: 'fa fa-plus',
       btnCls: 'topjui-btn-green',
       dialog:{
           id:'activityAwardAddDialog',
           title:'<@spring.message "sys.add"/>',
           href:_ctx+'/job/jobManager/addTaskPage',
                width:700,
                height:450,
           buttonsGroup:[{
                    text:'<@spring.message "sys.test"/>',
                    iconCls:'fa fa-star',
                    handler:function(){
                        var obj = $('#activityAwardAddDialog').serializeObject();
                        $.ajax({
                            type: 'POST',
                            url: _ctx+'/job/jobManager/testExecuteTheMethod',
                            data: obj,
                            dataType: 'json',
                            success: function (data) {
                                if (data.statusCode == 200) {
                                    $.iMessager.show({
                                        title: '<@spring.message "sys.promptMessage"/>',
                                        msg: '<@spring.message "sys.startedandlooklogs"/>'
                                    });
                                } else {
                                    $.iMessager.alert(data.title,data.message,'error');
                                }
                            },
                            error: function () {
                                $.iMessager.alert('<@spring.message "sys.promptMessage"/>','<@spring.message "sys.networkError"/>','error');
                            }
                        });
                    }
               },
               {
                    text:'<@spring.message "sys.add"/>',
                    url: _ctx+'/job/jobManager/addJob',
                    iconCls:'fa fa-plus',
                    handler:'ajaxForm',
                    btnCls:'topjui-btn-brown'
               }
           ]
       }"><@spring.message "sys.add"/></a>

    <!--查询条件表单-->
    <form id="queryForm" class="search-box">

        <input type="text" name="jobName" data-toggle="topjui-textbox"
               data-options="id:'jobName',prompt:'<@spring.message "sys.name"/>',width:150">


        <a href="javascript:void(0)"
           data-toggle="topjui-menubutton"
           data-options="method:'query',
           iconCls:'fa fa-search',
           btnCls:'topjui-btn-blue',
           form:{id:'queryForm'},
           grid:{type:'datagrid','id':'datagridId'}"><@spring.message "sys.search"/></a>
        <a href="#"
           data-toggle="topjui-linkbutton"
           data-options="id:'submitBtn',
                   iconCls:'fa fa-trash',
                   btnCls:'topjui-btn-red',
                   form:{
                       id:'queryForm',
                       method:'clear'
                   }"><@spring.message "sys.empty"/></a>

    </form>
</div>
</body>
</html>
<script type="application/javascript">
    function operateFormatter(value, row, index) {
        var htmlstr = ' <button class="layui-btn layui-btn-xs" onclick="restart(\'' + row.id + '\')"> <@spring.message "sys.start"/> </button> ';
        htmlstr += ' <button class="layui-btn layui-btn-xs layui-btn-danger" onclick="stop(\'' + row.id + '\')"> <@spring.message "sys.stop"/> </button> ';
        htmlstr += ' <button class="layui-btn layui-btn-xs layui-btn-danger" onclick="deleteById(\'' + row.id + '\')"> <@spring.message "sys.delete"/> </button> ';
        htmlstr += ' <button class="layui-btn layui-btn-xs layui-btn-normal" onclick="update(\'' + row.id + '\')"> <@spring.message "sys.update"/> </button> ';
        htmlstr += ' <button class="layui-btn layui-btn-xs layui-btn-danger" onclick="runOneTime(\'' + row.id + '\')"> <@spring.message "sys.runonce"/> </button> ';
        return htmlstr;
    }

    function runOneTime(id){
        $.ajax({
            type: "POST",
            url:_ctx + "/job/jobManager/runAJobNowOnce",
            data: {id:id},
            dataType: "json",
            success: function (data) {
                if (data.statusCode == 200) {
                    // 提示信息
                    $.iMessager.show({
                        title: '<@spring.message "sys.promptMessage"/>',
                        msg: '<@spring.message "sys.startedandlooklogs"/>'
                    });
                } else {
                    $.iMessager.alert(data.title,data.message,'error');
                }
            },
            error: function () {
                $.iMessager.alert('<@spring.message "sys.promptMessage"/>','<@spring.message "sys.networkError"/>','error');
            }
        });
    }

    function deleteById(id) {
        $.ajax({
            type: "POST",
            url:_ctx + "/job/jobManager/delete",
            data: {id:id},
            dataType: "json",
            success: function (data) {
                if (data.statusCode == 200) {
                    // 提示信息
                    $('#datagridId').iDatagrid('reload');
                } else {
                    $.iMessager.alert(data.title,data.message,'info');
                }
            },
            error: function () {
                $.iMessager.alert('<@spring.message "sys.promptMessage"/>','<@spring.message "sys.networkError"/>','error');
            }
        });
    }

    function stop(id) {
        $.ajax({
            type: "POST",
            url:_ctx + "/job/jobManager/stopOneJob",
            data: {id:id},
            dataType: "json",
            success: function (data) {
                if (data.statusCode == 200) {
                    // 提示信息
                    $('#datagridId').iDatagrid('reload');
                } else {
                    $.iMessager.alert(data.title,data.message,'info');
                }
            },
            error: function () {
                $.iMessager.alert('<@spring.message "sys.promptMessage"/>','<@spring.message "sys.networkError"/>','error');
            }
        });
    }

    function restart(id) {
        $.ajax({
            type: "POST",
            url:_ctx + "/job/jobManager/restartOneJob",
            data: {id:id,status:1},
            dataType: "json",
            success: function (data) {
                if (data.statusCode == 200) {
                    // 提示信息
                    $('#datagridId').iDatagrid('reload');
                } else {
                    $.iMessager.alert(data.title,data.message,'info');
                }
            },
            error: function () {
                $.iMessager.alert('<@spring.message "sys.promptMessage"/>','<@spring.message "sys.networkError"/>','error');
            }
        });
    }

    // 修改cron表达式
    function update(id) {
        var $editDialog = $('<form></form>');
        $editDialog.iDialog({
            title: '<@spring.message "sys.update"/>',
            width: 450,
            height: 300,
            closed: false,
            href: _ctx+'/job/jobManager/updateTaskPage?id=' + id,
            buttons: [{
                text: '<@spring.message "sys.save"/>',
                iconCls: 'fa fa-save',
                btnCls: 'topjui-btn-blue',
                handler: function () {
                    var obj = $editDialog.serializeObject();
                    $.ajax({
                        type: 'POST',
                        url: _ctx+'/job/jobManager/updateJob',
                        data: obj,
                        success: function (data) {
                            if (data.statusCode == 200) {
                                // 提示信息
                                $editDialog.iDialog('close');
                                $('#datagridId').iDatagrid('reload');
                            } else {
                                $.iMessager.alert(data.title, data.message, 'info');
                            }
                        },
                        error: function () {
                            $.iMessager.alert('<@spring.message "sys.promptMessage"/>','<@spring.message "sys.networkError"/>','error');
                        }
                    });
                }
            }, {
                text: '<@spring.message "sys.close"/>',
                iconCls: 'fa fa-close',
                btnCls: 'topjui-btn-red',
                handler: function () {
                    $editDialog.iDialog('close');
                }
            }]
        });
    }
</script>
