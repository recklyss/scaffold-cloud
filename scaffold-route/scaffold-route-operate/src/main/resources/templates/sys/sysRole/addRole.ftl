<#import "../../spring.ftl" as spring/>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div class="topjui-fluid" style="padding: 0;margin-right: 30px;">
        <div class="topjui-row" style="padding-top: 30px">
            <div class="topjui-col-sm12">
                <label class="topjui-form-label"><@spring.message "sys.name"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="name" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'<@spring.message "sys.name"/>'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm12">
                <label class="topjui-form-label"><@spring.message "sys.describe"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="remark" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'<@spring.message "sys.describe"/>'">
                </div>
            </div>
        </div>
    </div>
</div>