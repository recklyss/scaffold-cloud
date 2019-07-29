<#assign base=request.contextPath />
<html>
<#include "../../public/head_tab.ftl" >

<body>

<!-- treegrid表格 -->
<table data-toggle="topjui-treegrid"
       data-options="id:'menuTreegrid',
        idField:'id',
        treeField:'name',
        singleSelect:true,
        url: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId=0',
        expandUrl: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId={id}'">
    <thead>
    <tr>
        <th data-options="field:'id',title:'id',checkbox:true"></th>
        <th data-options="field:'name',title:'<@spring.message "sys.name"/>',width:200"></th>
        <th data-options="field:'resourceType',title:'<@spring.message "sys.resourcetype"/>',formatter: function(value,row,index){
                    if (value == 'menu'){
                        return '<@spring.message "sys.menu"/>';
                    } else if (value == 'window'){
                        return '<@spring.message "sys.window"/>';
                    } else if (value == 'button'){
                        return '<@spring.message "sys.button"/>';
                    } else if (value == 'url'){
                        return '<@spring.message "sys.url"/>';
                    } else {
                        return value;
                    }
			    }"></th>
        <th data-options="field:'url',title:'<@spring.message "sys.url"/>'"></th>
        <th data-options="field:'pid',title:'<@spring.message "sys.parent"/>'"></th>
        <th data-options="field:'levelId',title:'<@spring.message "sys.level"/>'"></th>
        <th data-options="field:'sort',title:'<@spring.message "sys.sort"/>'"></th>
        <th data-options="field:'code',title:'<@spring.message "sys.code"/>'"></th>
        <th data-options="field:'status',title:'<@spring.message "sys.status"/>',
                    formatter: function(value,row,index){
						if (value == 1){
							return '<span style=\'color:green\'><@spring.message "sys.enable"/></span>';
						} else if (value == 0) {
							return '<span style=\'color:red\'><@spring.message "sys.disable"/></span>';
						} else {
						    return '';
						}
                    }"></th>

    </tr>
    </thead>

</table>

<!-- 菜单 表格工具栏 -->
<div id="menuTreegrid-toolbar" class="topjui-toolbar"
     data-options="grid:{
	       type:'treegrid',
           id:'menuTreegrid',
           parentIdField:'pid'
       }" style="display:none">

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
           extend:'#menuTreegrid-toolbar',
           btnCls:'topjui-btn-green',
           parentGrid:{
               type:'treegrid',
               id:'menuTreegrid',
               unselectedMsg:'<@spring.message "sys.pleasecheckrow"/>'
           },
           dialog:{
               id:'menuAddDialog',
               href:_ctx+'/sys/sysMenu/sysMenuEdit?pid={id}',
               buttonsGroup:[
                   {text:'<@spring.message "sys.save"/>',url: _ctx+'/sys/sysMenu/sysMenuSave',iconCls:'fa fa-plus',handler:'ajaxForm'}
               ]
           }"><@spring.message "sys.add"/></a>

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
           extend:'#menuTreegrid-toolbar',
           iconCls:'fa fa-pencil',
           btnCls:'topjui-btn-blue',
           parentGrid:{
               type:'treegrid',
               id:'menuTreegrid',
               unselectedMsg:'<@spring.message "sys.pleasecheckrow"/>'
           },
           dialog:{
               id:'menuEditDialog',
               href:_ctx+'/sys/sysMenu/sysMenuEdit?id={id}',
               buttonsGroup:[
                   {text:'<@spring.message "sys.update"/>',url: _ctx+'/sys/sysMenu/sysMenuSave',iconCls:'fa fa-save',handler:'ajaxForm'}
               ]
           }"><@spring.message "sys.edit"/></a>


</div>
</body>
</html>