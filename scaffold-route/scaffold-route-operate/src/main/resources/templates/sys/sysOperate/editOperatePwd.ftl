<#import "../../spring.ftl" as spring/>
<div data-toggle="topjui-layout" data-options="fit:true">
    <div class="topjui-fluid">
        <div class="topjui-row" style="padding-top: 30px">
            <div class="topjui-col-sm12">
                <label class="topjui-form-label"><@spring.message "sys.role"/></label>
                <div class="topjui-input-block">
                    <div data-toggle="topjui-radio">
                        <#list list as item>
                            <#if item.id==sysRoleOperate.roleId>
                                <input type="radio" data-toggle="topjui-radiobutton" name="roleId" label="${item.name}" value="${item.id}" checked="checked" readonly>
                            </#if>
                        </#list>
                    </div>
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.username"/></label>
                <div class="topjui-input-block">
                    <input type="hidden" name="id"  value="${sysOperate.id}">
                    <input type="text" name="userName" data-toggle="topjui-textbox"
                           data-options="required:true" value="${sysOperate.userName!''}" readonly>
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.password"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="pwd" data-toggle="topjui-textbox"
                           data-options="required:true" >
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.status"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="status" data-toggle="topjui-combobox"
                           data-options="id:'status',prompt:'<@spring.message "sys.status"/>',panelHeight:100,
                     data:[
                           {text: '<@spring.message "sys.enable"/>',value: '0'
                            <#if sysOperate.status==0>, selected:true</#if>},
                           {text: '<@spring.message "sys.disable"/>',value: '1'
                            <#if sysOperate.status==1> ,selected:true</#if>}
                     ]" readonly>
                </div>
            </div>
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.mobilephone"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="mobilePhone" data-toggle="topjui-textbox"
                           data-options="required:true" value="${sysOperate.mobilePhone!''}" readonly>
                </div>
            </div>
        </div>

        <div class="topjui-row">
            <div class="topjui-col-sm6">
                <label class="topjui-form-label"><@spring.message "sys.realname"/></label>
                <div class="topjui-input-block">
                    <input type="text" name="realName" data-toggle="topjui-textbox"
                           data-options="required:true" value="${sysOperate.realName!''}" readonly>
                </div>
            </div>
        </div>
    </div>
</div>