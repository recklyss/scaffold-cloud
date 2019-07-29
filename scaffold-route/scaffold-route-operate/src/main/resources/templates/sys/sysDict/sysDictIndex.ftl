<#assign base=request.contextPath />
<html>
<#include "../../public/head_tab.ftl" >

<body>
<!-- treegrid表格 -->
<table data-toggle="topjui-treegrid"
       data-options="id:'dictTreegrid',
        idField:'id',
        treeField:'name',
        url: _ctx+'/sys/sysDict/findSysDictByPid?parentId=0',
        expandUrl: _ctx+'/sys/sysDict/findSysDictByPid?parentId={id}',
        singleSelect:true,
        onSelect:function(row){
            if(row.type==1){
                $('#dictAddButton').iMenubutton('enable');
            }else{
              $('#dictAddButton').iMenubutton('disable');
            }
        },
        onUnselect:function(row){
           $('#dictAddButton').iMenubutton('enable');
        }

">
    <thead>
    <tr>
        <th data-options="field:'id',title:'id',checkbox:true"></th>
        <th data-options="field:'name',title:'<@spring.message "sys.name"/>',width:400"></th>
        <th data-options="field:'nid',title:'nid',width:200""></th>
        <th data-options="field:'value',title:'<@spring.message "sys.value"/>',width:80"></th>
        <@th field='type' title='类型' i18n="sys.type" width='80' nid='basics_dict_type'></@th>
        <th data-options="field:'code',title:'<@spring.message "sys.code"/>',width:80"></th>
        <th data-options="field:'pid',title:'<@spring.message "sys.parent"/>',width:60"></th>
        <@th field='status' title='状态' i18n="sys.status" width='100' nid='basics_use_status'></@th>
        <th data-options="field:'sort',title:'<@spring.message "sys.sort"/>',width:100"></th>
    </tr>
    </thead>

</table>

<!-- 菜单 表格工具栏 -->
<div id="dictTreegrid-toolbar" class="topjui-toolbar"
     data-options="grid:{
	       type:'treegrid',
           id:'dictTreegrid',
           parentIdField:'pid'
       }" style="display:none">

    <a href="javascript:void(0)" id="dictAddButton"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
           extend:'#dictTreegrid-toolbar',
           iconCls: 'fa fa-plus',
           btnCls:'topjui-btn-green',
           parentGrid:{
               type:'treegrid',
               id:'dictTreegrid',
               unselectedMsg:'<@spring.message "sys.pleasecheckrow"/>'
           },
           dialog:{
               id:'dictAddDialog',
               href:_ctx+'/sys/sysDict/sysDictEdit?pid={id}',
               height: 600,
               buttonsGroup:[
                   {text:'<@spring.message "sys.save"/>',url: _ctx+'/sys/sysDict/sysDictSave',iconCls:'fa fa-plus',handler:'ajaxForm'}
               ]
           }
"><@spring.message "sys.add"/></a>

    <a href="javascript:void(0)"
       data-toggle="topjui-menubutton"
       data-options="method:'openDialog',
           extend:'#dictTreegrid-toolbar',
           iconCls:'fa fa-pencil',
           btnCls: 'topjui-btn-red',
           parentGrid:{
               type:'treegrid',
               id:'dictTreegrid',
               unselectedMsg:'<@spring.message "sys.pleasecheckrow"/>'
           },
           dialog:{
               id:'dictEditDialog',
               href:_ctx+'/sys/sysDict/sysDictEdit?id={id}',
               height: 600,
               buttonsGroup:[
                   {text:'<@spring.message "sys.update"/>',url: _ctx+'/sys/sysDict/sysDictSave',iconCls:'fa fa-save',handler:'ajaxForm'}
               ]
           }"><@spring.message "sys.edit"/></a>


</div>


<script>
    // 自定义方法
    function formatter(value,row,index) {
        console.log(value);
        var html = "&lt;@dictSelectTag nid=\"basics_dict_type\"&gt;&lt;/@dictSelectTag&gt;";
        return html;
    }
</script>

</body>
</html>