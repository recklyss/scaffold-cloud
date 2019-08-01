package ${package_name}.api;

import com.cms.scaffold.common.response.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author zhangjiaheng
*/
public interface ${table_name}Api{


    /**
    * @param id 主键
    * @return 根据主键查询
    */
    @RequestMapping(value = "/${table_name?uncap_first}/selectById", method = RequestMethod.GET)
    ResponseModel selectById(@RequestParam("id") Long id);
}
