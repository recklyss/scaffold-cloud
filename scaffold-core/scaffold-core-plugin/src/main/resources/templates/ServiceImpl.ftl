package ${package_name}.service.impl;

import ${package_name}.dao.${table_name}Mapper;
import ${package_name}.domain.${table_name};
import ${package_name}.ao.${table_name}AO;
import ${package_name}.bo.${table_name}BO;
import ${package_name}.service.${table_name}Service;
import BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ${table_name}ServiceImpl extends BaseServiceImpl<${table_name}Mapper, ${table_name}AO, ${table_name}BO, ${table_name}> implements ${table_name}Service {
}
