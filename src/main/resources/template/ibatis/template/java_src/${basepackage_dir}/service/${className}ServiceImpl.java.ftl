<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service;

import com.lj.app.core.common.base.service.BaseServiceImpl;

import org.springframework.stereotype.Service;

@Service("${classNameLower}Service")
public class ${className}ServiceImpl<${className}> extends BaseServiceImpl<${className}> implements ${className}Service<${className}>{

}
