package com.cms.scaffold.common.constant_auto;

/**
* Created by ${author} on ${date}
*/
public class ${fileName} {

<#macro bpTree children>
    <#if children?? && children?size gt 0>
        <#list children as child>
    /**
    * ${child.name}
    */
    public static final String ${child.nid?upper_case} = "${child.nid}";

        </#list>
    </#if>
</#macro>

<@bpTree children=sysDictList />
}
