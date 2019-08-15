package ${package_name}.controller;

import com.cms.scaffold.common.base.BaseController;
import com.cms.scaffold.common.builder.Builder;
import com.cms.scaffold.common.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ${package_name}.api.${table_name}Api;
import ${package_name}.domain.${table_name};
import ${package_name}.service.${table_name}Service;
import lombok.extern.slf4j.Slf4j;

/**
* @author zhangjiaheng
*/
@RestController
@Slf4j
public class ${table_name}Controller extends BaseController implements ${table_name}Api {

    @Autowired
    ${table_name}Service ${table_name?uncap_first}Service;

    /**
    * @param id 主键
    * @return 根据主键查询
    */
    @Override
    public ResponseModel selectById(Long id) {
        log.info("根据ID主键查询>>> id:[{}]", id);
        final ${table_name} ${table_name?uncap_first} = ${table_name?uncap_first}Service.selectById(id);
        return successData(Builder.build(${table_name?uncap_first}, ${table_name}.class));
    }
}
