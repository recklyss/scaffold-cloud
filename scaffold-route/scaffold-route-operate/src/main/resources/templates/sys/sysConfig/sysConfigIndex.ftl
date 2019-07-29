<!DOCTYPE html>
<html>
<#include "../../public/head_tab.ftl"/>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div  data-options="region:'center',iconCls:'icon-reload',title:'',fit:false,split:true,border:false,bodyCls:'border_left_right'">
        <table data-toggle="topjui-datagrid"
               data-options="id:'configDatagridId',
                             singleSelect:true,
                             url: _ctx+'/sys/sysConfig/sysConfigIndexList'"">
        <thead>
        <tr>
            <th data-options="field:'id',title:'id',checkbox:true"></th>
            <th data-options="field:'name',title:'<@spring.message "sys.name"/>'"></th>
            <th data-options="field:'nid',title:'NID'"></th>
            <th data-options="field:'value',title:'<@spring.message "sys.value"/>'"></th>
            <@th field='type' title='类型' i18n="sys.type" width='120' nid='basics_sys_config_type'></@th>
            <@th field='status' title='状态' i18n="sys.status" width='80' nid='basics_use_status'></@th>
            <th data-options="field:'remark',width:150,title:'<@spring.message "sys.describe"/>'"></th>
        </tr>
        </thead>
        </table>
    </div>
</div>

<!--表格工具栏 &ndash;&gt;-->
<div id="configDatagridId-toolbar" style="height: 30px; padding: 4px; text-align: left;"
     data-options="grid:{
        type:'datagrid',
        id:'configDatagridId'
     }" class="topjui-toolbar">

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#configDatagridId-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-red',
            grid: {
                type: 'datagrid',
                id: 'configDatagridId'
            },
            dialog: {
                title:'<@spring.message "sys.edit"/>',
                href: _ctx+'/sys/sysConfig/sysConfigEditPage?sysConfigId={id}',
                width:700,
                height:500,
                buttonsGroup: [
                    {
                        text: '<@spring.message "sys.update"/>',
                        url: _ctx+'/sys/sysConfig/sysConfigUpdate',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-brown'
                    }
                ]
            }"><@spring.message "sys.edit"/></a>

    <!--查询条件表单&ndash;&gt;-->
    <form id="queryForm" class="search-box" >
        <input type="text" name="name" data-toggle="topjui-textbox"
               data-options="id:'name',prompt:'<@spring.message "sys.name"/>',width:150">
        <input type="text" name="nid" data-toggle="topjui-textbox"
               data-options="id:'nid',prompt:'NID',width:150">
        <input type="text" name="type" data-toggle="topjui-combobox"
               data-options="id:'type',valueField:'value',textField:'text',prompt:'<@spring.message "sys.type"/>',width:150,
               url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_sys_config_type',
               icons:[{
                    iconCls:'icon-remove',
                    handler: function(e){
                        $(e.data.target).iCombobox('clear');
                    }
                }]">
        <input type="text" name="status" data-toggle="topjui-combobox"
               data-options="id:'status',valueField:'value',textField:'text',prompt:'<@spring.message "sys.status"/>',width:150,panelHeight:100,
               url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_use_status',
               icons:[{
                    iconCls:'icon-remove',
                    handler: function(e){
                        $(e.data.target).iCombobox('clear');
                    }
                }]">
        <a href="javascript:void(0)"
           data-toggle="topjui-menubutton"
           data-options="method:'query',
           iconCls:'fa fa-search',
           btnCls:'topjui-btn-blue',
           form:{id:'queryForm'},
           grid:{type:'datagrid','id':'configDatagridId'}"><@spring.message "sys.search"/></a>
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

<script>
</script>
</html>