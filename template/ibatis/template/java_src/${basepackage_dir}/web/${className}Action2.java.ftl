<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.web;

import ${basepackage}.model.${className};
import ${basepackage}.service.${className}Service;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.base.service.BaseService;
import com.lj.app.core.common.util.DateUtil;
import com.lj.app.core.common.web.AbstractBaseAction;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @title :${className}Action.java
 * @description :${className}Action
 * @author: userName
 * @date: date
 */
@SuppressWarnings("serial")
@Controller
@Namespace("/jsp/${classNameLower}")
@Results({
	    @Result(name = AbstractBaseAction.RELOAD, location = "${classNameLower}Action", type = AbstractBaseAction.REDIRECT),
		@Result(name = AbstractBaseAction.INPUT, location = "/jsp/${classNameLower}/${classNameLower}-input.jsp"),
		@Result(name = AbstractBaseAction.SAVE, location = "${classNameLower}Action!edit.action",type=AbstractBaseAction.REDIRECT),
		@Result(name = AbstractBaseAction.LIST, location = "/jsp/${classNameLower}/${classNameLower}List.jsp", type=AbstractBaseAction.REDIRECT)
})

@Action("${classNameLower}Action")
public class ${className}Action extends AbstractBaseAction<${className}> {
	
	 protected Logger logger = LoggerFactory.getLogger(${className}Action.class);

	@Autowired
	private ${className}Service ${classNameLower}Service;
	
	private ${className} ${classNameLower};
	
	public   BaseService getBaseService(){
		return ${classNameLower}Service;
	}
	
	public ${className} getModel() {
		return ${classNameLower};
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			${classNameLower} = (${className})${classNameLower}Service.getInfoByKey(id);
		} else {
			${classNameLower} = new ${className}();
		}
	}
	
	@Override
	public String list() throws Exception {
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			page.setFilters(${classNameLower});
			
			if (ValidateUtil.isNotEmpty(this.getSidx())) {
				String orderBy = PageTool.convert(this.getSidx()) + " "+ this.getSord();
				page.setSortColumns(orderBy);
			}
			
			<#list table.columns as column>
			<#if column.isDateTimeColumn>
			condition.put("${column.columnNameLower}Begin",  Struts2Utils.getParameter("${column.columnNameLower}Begin"));
			condition.put("${column.columnNameLower}End",  Struts2Utils.getParameter("${column.columnNameLower}End"));
			</#if>
			</#list>
			condition.put(CREATE_BY, getLoginUserId());
			
			${classNameLower}Service.findPageList(page, condition);
			Struts2Utils.renderText(PageTool.pageToJsonJQGrid(this.page),new String[0]);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	@Override
	public String save() throws Exception {
		
	try{
			if (operate != null && operate.equals("edit")) {
				${classNameLower}.setUpdateBy(getLoginUserId());
				${classNameLower}.setUpdateDate(DateUtil.getNowDateYYYYMMddHHMMSS());
				${classNameLower}Service.updateObject(${classNameLower});
				
				returnMessage = UPDATE_SUCCESS;
			}else{
				${classNameLower}.setCreateBy(getLoginUserId());
				${classNameLower}.setCreateDate(DateUtil.getNowDateYYYYMMddHHMMSS());
				${classNameLower}Service.insertObject(${classNameLower});
				returnMessage = CREATE_SUCCESS;
			}
			
			return LIST;
		}catch(Exception e){
			returnMessage = CREATE_FAILURE;
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public String delete() throws Exception {
		return null;
	}
	
}

