<#import "../../spring.ftl" as spring/>
<div data-toggle="topjui-layout" data-options="fit:true" style="overflow-y: scroll">
    <div class="topjui-fluid" style="padding-top: 20px; margin-right: 30px;">
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.parent"/></label>
                <div class="topjui-input-block">
                    <input type="hidden" name="id" value="${sysDict.id!''}">
                    <input type="text" name="pid" data-toggle="topjui-combotreegrid" value="${sysDict.pid!''}" readonly="readonly"
                           data-options="required:true,expandAll:false,idField:'id',prompt:'<@spring.message "sys.parent"/>',
                           treeField:'name',
                           panelHeight:250,
                           fitColumns:true,
                           columns:[[
                               {field:'name',title:'<@spring.message "sys.name"/>',width:100}
                           ]],
                           url: _ctx+'/sys/sysDict/findSysDictByPid?parentId=0',
                           expandUrl: _ctx+'/sys/sysDict/findSysDictByPid?parentId={id}',
                           getFatherIdsUrl: _ctx+'/sys/sysDict/findFatherIds?id={id}'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.dicttype"/></label>
                <div class="topjui-input-block">
                    <input id="dictType" type="text" name="type" data-toggle="topjui-combobox" value="${sysDict.type!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.dicttype"/>',
                            panelHeight:100,
                            textField:'text',
                            valueField:'value',
                            url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_dict_type',
                            onSelect: function(rec){
                                if(rec.value==1){
                                    $('#dictNid').iTextbox({
                                        prompt:'<@spring.message "sys.mustinsert"/>',
                                        required:true,
                                        readonly:false
                                    });
                                    $('#dictValue').iTextbox({
                                        value:'',
                                        prompt:'<@spring.message "sys.disable"/>',
                                        required:false,
                                        readonly:true
                                    });
                                    $('#dictCode').iTextbox({
                                        value:'',
                                        prompt:'<@spring.message "sys.disable"/>',
                                        required:false,
                                        readonly:true
                                    });
                                    $('#dictJavaType').iTextbox({
                                        value:'',
                                        prompt:'<@spring.message "sys.disable"/>',
                                        required:false,
                                        readonly:true
                                    });
                                }else{
                                     $('#dictNid').iTextbox({
                                        value:'',
                                        prompt:'<@spring.message "sys.disable"/>',
                                        required:false,
                                        readonly:true
                                    });
                                    $('#dictValue').iTextbox({
                                        prompt:'<@spring.message "sys.mustinsert"/>',
                                        required:true,
                                        readonly:false
                                    });
                                    $('#dictCode').iTextbox({
                                        prompt:'<@spring.message "sys.mustinsert"/>',
                                        required:true,
                                        readonly:false
                                    });
                                    $('#dictJavaType').iTextbox({
                                        prompt:'<@spring.message "sys.mustinsert"/>',
                                        required:true,
                                        readonly:false
                                    });
                                }
                            }">
                </div>
            </div>

        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.name"/></label>
                <div class="topjui-input-block">
                    <input  type="text" name="name" data-toggle="topjui-textbox" value="${sysDict.name!''}"
                            data-options="required:true,prompt:'<@spring.message "sys.name"/>'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label">nid</label>
                <div class="topjui-input-block">
                    <input id="dictNid" type="text" name="nid" data-toggle="topjui-textbox" value="${sysDict.nid!''}"
                           data-options="prompt:'nid'">
                </div>
            </div>

        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.value"/></label>
                <div class="topjui-input-block">
                    <input id="dictValue" type="text" name="value" data-toggle="topjui-textbox" value="${sysDict.value!''}"
                           data-options="prompt:'<@spring.message "sys.value"/>'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.code"/></label>
                <div class="topjui-input-block">
                    <input id="dictCode" type="text" name="code" data-toggle="topjui-textbox" value="${sysDict.code!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.code"/>'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.javatype"/></label>
                <div class="topjui-input-block">
                    <input id='dictJavaType' type="text" name="javaType" data-toggle="topjui-combobox"
                           value="${(sysDict.javaType)!''}"
                           data-options="
            required:true,prompt:'<@spring.message "sys.javatype"/>',
            valueField:'value',
            url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_java_type'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.status"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="status"
                           data-toggle="topjui-combobox"
                           value="${sysDict.status!'1'}"
                           data-options="required:true,prompt:'<@spring.message "sys.status"/>',
                           panelHeight:100,
                           textField:'text',
                           valueField:'value',
                           url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_use_status'">
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.sort"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="sort" data-toggle="topjui-numberspinner" value="${sysDict.sort!''}"
                           data-options="prompt:'<@spring.message "sys.sort"/>'">
                </div>
            </div>

        </div>
    </div>
</div>
