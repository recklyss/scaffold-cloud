package ${feign_package_name};

import org.springframework.cloud.openfeign.FeignClient;
import ${package_name}.api.${table_name}Api;

/**
* @author zhangjiaheng
*/
@FeignClient(value = "${feign_service_name}")
public interface ${table_name}Feign extends ${table_name}Api {}
