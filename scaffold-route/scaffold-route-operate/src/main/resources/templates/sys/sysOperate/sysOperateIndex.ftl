<!DOCTYPE html>
<html>
<#include "../../public/head_index.ftl"/>
<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div  data-options="region:'center',iconCls:'icon-reload',title:'',fit:false,split:true,border:false,bodyCls:'border_left_right'">
        <table data-toggle="topjui-datagrid"
               data-options="id:'userDatagridId',
                             singleSelect:true,
                             url: _ctx+'/sys/sysOperate/operateList'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'userName',title:'<@spring.message "sys.username"/>'"></th>
                <th data-options="field:'realName',title:'<@spring.message "sys.realname"/>'"></th>
                <th data-options="field:'mobilePhone',title:'<@spring.message "sys.mobilephone"/>'"></th>
                <th data-options="field:'roleName',title:'<@spring.message "sys.role"/>'"></th>
                <th data-options="field:'status',title:'<@spring.message "sys.status"/>',
                formatter:function(value,row,index){
                    if (value == '0') {
                        return '<@spring.message "sys.enable"/>';
                    } else if (value == '1') {
                        return '<@spring.message "sys.disable"/>';
                    }
                }"></th>

            </tr>
            </thead>
        </table>
    </div>
</div>

<!--表格工具栏 &ndash;&gt;-->
<div id="userDatagridId-toolbar"
     data-options="grid:{
        type:'datagrid',
        id:'userDatagridId'
     }" class="topjui-toolbar">
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
           extend: '#userDatagridId-toolbar',
           iconCls: 'fa fa-plus',
           btnCls: 'topjui-btn-green',
           dialog:{
               title:'<@spring.message "sys.add"/>',
               id:'articleAddDialog',
               href:_ctx+'/sys/sysOperate/addOperatePage',
               width:700,
               height:500,
               maximizable:false,
               buttonsGroup:[
                   {text:'<@spring.message "sys.save"/>',url: _ctx+'/sys/sysOperate/saveOperate',iconCls:'fa fa-plus',handler:'ajaxForm'}
               ]
       }"><@spring.message "sys.add"/></a>

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDatagridId-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-red',
            grid: {
                type: 'datagrid',
                id: 'userDatagridId'
            },
            dialog: {
                title:'<@spring.message "sys.edit"/>',
                href: _ctx+'/sys/sysOperate/editOperatePage?operateId={id}',
                width:700,
                height:500,
                buttonsGroup: [
                    {
                        text: '<@spring.message "sys.update"/>',
                        url: _ctx+'/sys/sysOperate/updateOperate',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }"><@spring.message "sys.edit"/></a>

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method: 'openDialog',
            extend: '#userDatagridId-toolbar',
            iconCls: 'fa fa-pencil',
            btnCls: 'topjui-btn-purple',
            grid: {
                type: 'datagrid',
                id: 'userDatagridId'
            },
            dialog: {
                title:'<@spring.message "sys.editpassword"/>',
                href: _ctx+'/sys/sysOperate/editOperatePwdPage?operateId={id}',
                width:700,
                height:500,
                buttonsGroup: [
                    {
                        text: '<@spring.message "sys.update"/>',
                        url: _ctx+'/sys/sysOperate/updateOperatePwd',
                        iconCls: 'fa fa-save',
                        handler: 'ajaxForm',
                        btnCls: 'topjui-btn-green'
                    }
                ]
            }"><@spring.message "sys.editpassword"/></a>

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'doAjax',
       extend: '#userDatagridId-toolbar',
       btnCls:'topjui-btn-brown',
       iconCls:'fa fa-trash',
       url: _ctx+'/sys/sysOperate/resetPwd',
       grid: {uncheckedMsg:'<@spring.message "sys.pleasecheckrow"/>',param:'operateId:id'}"><@spring.message "sys.reset"/></a>

    <!--查询条件表单&ndash;&gt;-->
    <form id="queryForm" class="search-box">
        <input type="text" name="status" data-toggle="topjui-combobox"
               data-options="id:'status',prompt:'<@spring.message "sys.status"/>',width:100,panelHeight:100,
                     data:[{text: '<@spring.message "sys.enable"/>',value: '0' },{text: '<@spring.message "sys.disable"/>',value: '1'}],
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
           grid:{type:'datagrid','id':'userDatagridId'}"><@spring.message "sys.search"/></a>
    </form>
</div>
</body>
</html>
<script type="text/javascript">


</script>