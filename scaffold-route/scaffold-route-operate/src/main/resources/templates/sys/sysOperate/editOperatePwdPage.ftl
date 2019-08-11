<#import "../../spring.ftl" as spring/>
<div data-toggle="topjui-layout" data-options="fit:true">

    <div class="topjui-fluid">
        <fieldset>
            <legend>修改密码</legend>
        </fieldset>
        <div class="topjui-row">
            <div class="topjui-col-sm8">
                <label class="topjui-form-label">原密码</label>
                <div class="topjui-input-block">
                    <input type="password" width="150px" name="oldPassword1" data-toggle="topjui-textbox" data-options="required:true" >
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm8">
                <label class="topjui-form-label">确认原密码</label>
                <div class="topjui-input-block">
                    <input type="password" width="150px" name="oldPassword2" data-toggle="topjui-textbox" data-options="required:true" >
                </div>
            </div>
        </div>
        <div class="topjui-row">
            <div class="topjui-col-sm8">
                <label class="topjui-form-label">新密码</label>
                <div class="topjui-input-block">
                    <input type="hidden" value="${sysOperateId}" name="id"/>
                    <input type="password" width="150px" name="password" data-toggle="topjui-textbox" data-options="required:true" >
                </div>
            </div>
        </div>

    </div>
</div>