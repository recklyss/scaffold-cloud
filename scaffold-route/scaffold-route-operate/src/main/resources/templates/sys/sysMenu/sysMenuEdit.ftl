<#import "../../spring.ftl" as spring/>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div class="topjui-fluid">
        <div class="topjui-row" style="padding-top: 20px">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.parent"/></label>
                <div class="topjui-input-block">
                    <input type="hidden" name="id" value="${sysMenu.id!''}">
                    <input type="text" name="pid" data-toggle="topjui-combotreegrid" value="${sysMenu.pid!''}" readonly="readonly"
                           data-options="required:true,expandAll:false,
                           prompt:'<@spring.message "sys.parent"/>',idField:'id',treeField:'name',
                          panelHeight:250,fitColumns:true,
                           columns:[[
                               {field:'name',title:'<@spring.message "sys.name"/>',width:100}
                           ]],
                           url: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId=0',
                           expandUrl: _ctx+'/sys/sysMenu/findSysMenuByPid?parentId={id}',
                           getFatherIdsUrl: _ctx+'/sys/sysMenu/findFatherIds?id={id}'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.resourcetype"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="resourceType" data-toggle="topjui-combobox" value="${sysMenu.resourceType!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.resourcetype"/>',panelHeight:150,
                            textField:'text',
                            valueField:'value',
                            data: [{text: '<@spring.message "sys.menu"/>',value: 'menu'},
                                {text: '<@spring.message "sys.window"/>',value: 'window'},
                                {text: '<@spring.message "sys.button"/>',alue: 'button'},
                                {text: '<@spring.message "sys.url"/>',value: 'url'}]"/>
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.name"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="name" data-toggle="topjui-textbox" value="${sysMenu.name!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.name"/>'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.openorclose"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="state"
                           data-toggle="topjui-combobox"
                           value="${sysMenu.state!''}"
                           data-options="required:true,textField:'text',valueField:'value',prompt:'<@spring.message "sys.openorclose"/>',panelHeight:100,
                            data: [{text: '<@spring.message "sys.open"/>',value: 'closed'},{text: '<@spring.message "sys.close"/>',value: 'open'}]">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.status"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="status"
                           data-toggle="topjui-combobox"
                           value="${sysMenu.status!''}"
                           data-options="required:true,textField:'text',valueField:'value',prompt:'<@spring.message "sys.status"/>',panelHeight:100,
                            data: [{text: '<@spring.message "sys.enable"/>',value: '1'},{text: '<@spring.message "sys.disable"/>',value: '0'}]">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.icon"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="iconCls" data-toggle="topjui-textbox" value="${sysMenu.iconCls!''}"
                           data-options="prompt:'<@spring.message "sys.icon"/>'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm8">
                <label class="topjui-form-label"><@spring.message "sys.url"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="url" data-toggle="topjui-textbox" value="${sysMenu.url!''}"
                           data-options="prompt:'<@spring.message "sys.url"/>'">
                </div>
            </div>
            <div class="topjui-col-sm4">
                <label class="topjui-form-label"><@spring.message "sys.sort"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="sort" data-toggle="topjui-numberspinner" value="${sysMenu.sort!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.sort"/>'">
                </div>
            </div>
        </div>


    </div>
</div>