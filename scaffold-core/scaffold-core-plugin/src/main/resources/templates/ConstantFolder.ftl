package com.cms.scaffold.common.constant_auto;

/**
* Created by ${author} on ${date}
*/
public class ${fileName} {

    <#macro bpTree children>
        <#if children?? && children?size gt 0>
            <#list children as child>
                    <#if child.pid != 0>
    /**
    * ${child.name}
    */
    public static final String ${child.nid?upper_case} = "${child.nid}";

                    </#if>
                <@bpTree children=child.list />
            </#list>
        </#if>
    </#macro>

    <@bpTree children=sysDictList />


}
