package ${package_name}.service.impl;

import ${package_name}.dao.${table_name}Mapper;
import ${package_name}.domain.${table_name};
import ${package_name}.service.${table_name}Service;
import com.cms.scaffold.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
* @author zhangjiaheng
*/
@Service
@Slf4j
public class ${table_name}ServiceImpl extends BaseServiceImpl<${table_name}Mapper, ${table_name}> implements ${table_name}Service {
}
