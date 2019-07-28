package com.cms.scaffold.common.constant_auto;

/**
* Created by ${author} on ${date}
*/
public class ${fileName} {

    <#macro bpTree children>
        <#if children?? && children?size gt 0>
            <#list children as child>
                <#if child.type = 1>
    /*********************** ${child.name}start **************************************/
                    <@bpTree children=child.list />
    /*********************** ${child.name}end **************************************/
                </#if>
                <#if child.type == 2 && child.code??>
    /**
    * ${child.name}
    */
                    <#if child.javaType == "String">
    public static final ${child.javaType} ${child.nid?upper_case}_${child.code?upper_case} = "${child.value}";
                    <#elseif child.javaType == "Char">
    public static final ${child.javaType} ${child.nid?upper_case}_${child.code?upper_case} = '${child.value}';
                    <#else>
    public static final ${child.javaType} ${child.nid?upper_case}_${child.code?upper_case} = ${child.value};
                    </#if>
                </#if>
            </#list>
        </#if>
    </#macro>

    <@bpTree children=sysDictList />


}
