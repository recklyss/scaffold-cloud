<#import "../../spring.ftl" as spring/>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div class="topjui-fluid">
        <input type="hidden" name="id" value="${sysI18n.id!''}">
        <div class="topjui-row" style="padding-top: 30px">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.module"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="model" data-toggle="topjui-textbox" value="${sysI18n.model!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.module"/>'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.name"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="name" data-toggle="topjui-textbox" value="${sysI18n.name!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.name"/>'">
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.default"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="text" data-toggle="topjui-textbox" value="${sysI18n.text!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.default"/>'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.chinese"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="zhCn" data-toggle="topjui-textbox" value="${sysI18n.zhCn!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.chinese"/>'">
                </div>
            </div>
        </div>


        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.english"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="enUs" data-toggle="topjui-textbox" value="${sysI18n.enUs!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.english"/>'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.indonesian"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="inId" data-toggle="topjui-textbox" value="${sysI18n.inId!''}"
                           data-options="required:true,prompt:'<@spring.message "sys.indonesian"/>'">
                </div>

            </div>
        </div>


    </div>
</div>