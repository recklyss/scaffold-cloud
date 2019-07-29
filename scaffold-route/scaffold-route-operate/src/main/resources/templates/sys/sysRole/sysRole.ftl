<!DOCTYPE html>
<html>
<head>
<#include "../../public/head_index.ftl"/>
</head>

<body>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div data-options="region:'west',title:'',split:true,border:false,width:'60%',iconCls:'fa fa-sitemap',headerCls:'border_right',bodyCls:'border_right'">
        <!-- datagrid表格 -->
        <table data-toggle="topjui-datagrid"
               data-options="id:'userDg',
                    idField:'id',
                    url: _ctx+'/sys/sysRole/sysRoleIndex',
                    childGrid:{
                        param:'roleId:id',
                        grid:[
                            {type:'treegrid',id:'orgnizationDatagrid',syncReload:false},
                        ]
			        },
                    filter: [{
                        field: 'userName',
                        type: 'textbox',
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    },{
                        field: 'sex',
                        type: 'combobox',
                        options: {
                            valueField: 'value',
                            textField: 'label',
                            data: [{
                                label: '<@spring.message "sys.man"/>',
                                value: '1'
                            }, {
                                label: '<@spring.message "sys.women"/>',
                                value: '2'
                            }]
                        },
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    },{
                        field: 'post',
                        type: 'combobox',
                        options: {
                            valueField: 'value',
                            textField: 'label',
                            multiple: true,
                            data: [{
                                label: 'CEO',
                                value: 'CEO'
                            }, {
                                label: 'COO',
                                value: 'COO'
                            }, {
                                label: 'CTO',
                                value: 'CTO'
                            }]
                        },
                        op: ['contains', 'equal', 'notequal', 'less', 'greater']
                    }]">
            <thead>
            <tr>
                <th data-options="field:'id',title:'id',checkbox:true"></th>
                <th data-options="field:'name',title:'<@spring.message "sys.name"/>',sortable:true"></th>
                <th data-options="field:'remark',title:'<@spring.message "sys.describe"/>',sortable:true"></th>
                <th data-options="field:'addTime',title:'<@spring.message "sys.addtime"/>',sortable:true,
                    formatter:function(value,row,index){
                    if(value != null){
                    return timestamp2Datetime(value,'Y-m-d');
                }
                }"
                ></th>
                <th data-options="field:'updateTime',title:'<@spring.message "sys.updatetime"/>',sortable:true,
                   formatter:function(value,row,index){
                    if(value != null){
                    return timestamp2Datetime(value,'Y-m-d');
                }
                }

                "></th>
                <th data-options="field:'status',title:'<@spring.message "sys.status"/>',sortable:true,
                    formatter:function(value,row,index){
                        if (value == '1') {
                            return '<@spring.message "sys.enable"/>';
                        } else if (value == '2') {
                            return '<@spring.message "sys.disable"/>';
                        }
                    }"></th>

            </tr>
            </thead>
        </table>
    </div>
    <div data-options="region:'center',iconCls:'icon-reload',title:'',split:true,border:false,bodyCls:'border_left'">

        <!-- treegrid表格 -->
        <table data-toggle="topjui-treegrid"
               data-options="id:'orgnizationDatagrid',
			   idField:'id',
			   treeField:'text',
			   singleSelect:false,
			   url: _ctx+'/sys/sysMenu/allMenus',
			   expandUrl: _ctx+'/sys/sysMenu/allMenus'
			">
            <thead>
            <tr>
                <th data-options="field:'uuid',title:'uuid',checkbox:true"></th>
                <th data-options="field:'text',title:'<@spring.message "sys.menu"/>'"></th>
                <th data-options="field:'autoStatus',title:'<@spring.message "sys.status"/>',sortable:true,
                    formatter:function(value,row,index){
                        if (value == '1') {
                            return '<@spring.message "sys.authorized"/>';
                        }else if(value=='0'){
                            return '<@spring.message "sys.unauthorized"/>';
                        }
                      }"
                ></th>

            </tr>
            </thead>
        </table>


    </div>
</div>

<!-- 表格工具栏开始 -->
<div id="userDg-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'userDg'
       }">
    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
       extend: '#userDg-toolbar',
       iconCls: 'fa fa-plus',
       btnCls: 'topjui-btn-green',
       dialog:{
           id:'userAddDialog',
           href:_ctx+'/sys/sysRole/addRole',
           width:500,
           height:400,
           buttonsGroup:[
               {text:'<@spring.message "sys.save"/>',url:'saveRole',iconCls:'fa fa-plus',handler:'ajaxForm',btnCls:'topjui-btn-brown'}
           ]
       }"><@spring.message "sys.add"/></a>

</div>
<div id="orgnizationDatagrid-toolbar" class="topjui-toolbar"
     data-options="grid:{
           type:'datagrid',
           id:'orgnizationDatagrid'
       }">
    <a href="javascript:void(0)" data-toggle="topjui-menubutton" data-options="method:'doAjax',
	   iconCls:'fa fa-plus',
	   btnCls:'topjui-btn-green',
	   url: _ctx+'/sys/sysRoleMenu/addRoleMenu?roleId={parent.id}',
   	   confirmMsg:'<@spring.message "sys.confirmmsg"/>',
   	   parentGrid:{
   	       type:'treegrid',
	   	   id:'userDg',
	   	   uncheckedMsg:'<@spring.message "sys.pleasecheckleftrow"/>'
   	   },
	   grid:{
	       type:'treegrid',
	   	   id:'orgnizationDatagrid',
           parentIdField:'id',
           uncheckedMsg:'<@spring.message "sys.pleasecheckrow"/>',
	   }" ><@spring.message "sys.setauthorized"/></a>


    <a href="javascript:void(0)" data-toggle="topjui-menubutton" data-options="method:'doAjax',
	   iconCls:'fa fa-plus',
	   btnCls:'topjui-btn-green',
	   url: _ctx+'/sys/sysRoleMenu/deleteMenu?roleId={parent.id}',
   	   confirmMsg:'<@spring.message "sys.confirmmsg"/>',
   	   parentGrid:{
   	       type:'treegrid',
	   	   id:'userDg',
	   	   uncheckedMsg:'<@spring.message "sys.pleasecheckleftrow"/>'
   	   },
	   grid:{
	       type:'treegrid',
	   	   id:'orgnizationDatagrid',
           parentIdField:'id',
           uncheckedMsg:'<@spring.message "sys.pleasecheckrow"/>',
	   }"><@spring.message "sys.setunauthorized"/></a>

</div>
</div>
</body>
</html>