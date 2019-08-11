<#import "../../spring.ftl" as spring/>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div class="topjui-fluid">
        <div class="topjui-row" style="padding-top: 30px">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.username"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="userName" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'<@spring.message "sys.username"/>'">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.password"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="pwd" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'<@spring.message "sys.password"/>'" class="passwordbox-f textbox-f">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.realname"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="realName" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'<@spring.message "sys.realname"/>'">
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm12">
                <label class="topjui-form-label"><@spring.message "sys.role"/></label>
                <div class="topjui-input-block">
                    <div>
                        <#list list as item>
                            <input type="radio" data-toggle="topjui-radiobutton" name="roleId" label="${item.name}" value="${item.id}" checked="checked">
                        </#list>
                    </div>
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.status"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="status" data-toggle="topjui-combobox"
                           data-options="id:'status',required:true,prompt:'<@spring.message "sys.status"/>',panelHeight:100,
                     data:[
                           {
                               text: '<@spring.message "sys.enable"/>',
                               value: '0'
                           },
                           {
                               text: '<@spring.message "sys.disable"/>',
                               value: '1'
                           }
                     ]">
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.mobilephone"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="mobilePhone" data-toggle="topjui-textbox"
                           data-options="required:true,prompt:'<@spring.message "sys.mobilephone"/>'">
                </div>
            </div>
        </div>

    </div>