<#import "../../spring.ftl" as spring/>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div class="topjui-fluid" style="padding: 0; margin-right: 30px">
        <div class="topjui-row" style="padding-top: 30px">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.name"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="jobName" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'<@spring.message "sys.name"/>'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.group"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="jobGroup" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'<@spring.message "sys.group"/>'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.corn"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="cronExpression" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'<@spring.message "sys.corn"/>'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.methodname"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="methodName" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'<@spring.message "sys.methodname"/>'">
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.param"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="paramJson" data-toggle="topjui-textbox" value="">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.isconcurrent"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="isConcurrent" data-toggle="topjui-combobox" value="0"
                           data-options="id:'isConcurrent',prompt:'<@spring.message "sys.isconcurrent"/>',required:true,panelHeight:100,
                     url: _ctx+'/sys/sysDict/findDictValues?selectName=basics_sys_job_is_concurrent'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm10">
                <label class="topjui-form-label"><@spring.message "sys.classname"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="beanClass" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'<@spring.message "sys.classname"/>'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm10">
                <label class="topjui-form-label"><@spring.message "sys.describe"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="jobDescription" data-toggle="topjui-textbox" value="" data-options="required:true,prompt:'<@spring.message "sys.describe"/>'">
                </div>
            </div>

        </div>

    </div>
</div>