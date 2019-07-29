<#assign base=request.contextPath />
<html>
<#include "../../public/head_tab.ftl" >

<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div  data-options="region:'center',iconCls:'icon-reload',title:'',fit:false,split:true,border:false,bodyCls:'border_left_right'">
        <table data-toggle="topjui-datagrid"
               data-options="id:'sysI18nDatagridId',
                             url: _ctx+'/sys/sysI18n/sysI18nIndexList'"">
        <thead>
        <tr>
            <th data-options="field:'id',title:'id'"></th>
            <th data-options="field:'model',title:'<@spring.message "sys.module"/>'"></th>
            <th data-options="field:'name',title:'<@spring.message "sys.name"/>'"></th>
            <th data-options="field:'text',title:'<@spring.message "sys.default"/>'"></th>
            <th data-options="field:'zhCn',title:'<@spring.message "sys.chinese"/>'"></th>
            <th data-options="field:'enUs',title:'<@spring.message "sys.english"/>'"></th>
            <th data-options="field:'inId',title:'<@spring.message "sys.indonesian"/>'"></th>
        </tr>
        </thead>
        </table>
    </div>
</div>
<!--表格工具栏 &ndash;&gt;-->
<div id="sysI18nDatagridId-toolbar" style="height: 30px; padding: 4px; text-align: left;"
     data-options="grid:{
        type:'datagrid',
        id:'sysI18nDatagridId'
     }" class="topjui-toolbar">

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#sysI18nDatagridId-toolbar',
       iconCls: 'fa fa-plus',
       btnCls: 'topjui-btn-green',
       dialog:{
           id:'sysI18nAddDialog',
           href:_ctx+'/sys/sysI18n/sysI18nEdit',
           buttonsGroup:[
               {text:'<@spring.message "sys.save"/>',url:_ctx+'/sys/sysI18n/sysI18nSave',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }"><@spring.message "sys.add"/></a>

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#sysI18nDatagridId-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-red',
            grid: {
                type: 'datagrid',
                id: 'sysI18nDatagridId'
            },
            dialog: {
                title:'<@spring.message "sys.edit"/>',
                href: _ctx+'/sys/sysI18n/sysI18nEdit?id={id}',
                buttonsGroup: [
                    {
                        text: '<@spring.message "sys.update"/>',
                        url: _ctx+'/sys/sysI18n/sysI18nSave',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }"><@spring.message "sys.edit"/></a>


    <!--查询条件表单&ndash;&gt;-->
    <form id="queryForm" class="search-box" >
        <input type="text" name="name" data-toggle="topjui-textbox"
               data-options="id:'name',prompt:'<@spring.message "sys.name"/>',width:150">

        <a href="javascript:void(0)"
           data-toggle="topjui-menubutton"
           data-options="method:'query',
           iconCls:'fa fa-search',
           btnCls:'topjui-btn-blue',
           form:{id:'queryForm'},
           grid:{type:'datagrid','id':'sysI18nDatagridId'}"><@spring.message "sys.search"/></a>
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

<script>

</script>

</body>
</html>