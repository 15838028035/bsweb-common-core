package com.lj.app.core.common.pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lj.app.core.common.util.StringUtil;

import net.sf.json.JSONObject;

public class PageTool {
	private static final String JSONPAGE = "page";
	private static final String JSONROWS = "rows";

	public static String convert(String str) {
		if (StringUtil.isBlank(str)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if ((c >= 'A') && (c <= 'Z')) {
				if (i == 0)
					sb.append(Character.toLowerCase(c));
				else
					sb.append("_" + Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}
	
	public static String pageToJsonJQGrid(Page page) {
		Map<String,Object> mapResult = new HashMap<String,Object>();
		mapResult.put("page", page.getPageNumber());
		mapResult.put("total", page.getTotalPages());
		mapResult.put("records", page.getTotalCount());
		mapResult.put("rows", page.getResult());
		return page.toJsonString(mapResult);
	}
	
	public static String mapPageToJsonJQGrid(Page page) {
		Map<String,Object> mapResult = new HashMap<String,Object>();
		mapResult.put("page", page.getPageNumber());
		mapResult.put("total", page.getTotalPages());
		mapResult.put("records", page.getTotalCount());
		mapResult.put("rows", page.getResult());
		return toJsonString(page,mapResult);
	}
	
	public static String pageToJsonBootStrap(Page page) {
		Map<String,Object> mapResult = new HashMap<String,Object>();
		mapResult.put("pageNumber", page.getPageNumber());
		mapResult.put("pageSize", page.getPageSize());
		mapResult.put("page", page.getPageNumber());
		mapResult.put("totalPage", page.getTotalPages());
		mapResult.put("total", page.getTotalCount());
		mapResult.put("rows", page.getResult());
		return page.toJsonString(mapResult);
	}
	
	public static String mapPageToJsonBootStrap(Page page) {
		Map<String,Object> mapResult = new HashMap<String,Object>();
		mapResult.put("pageNumber", page.getPageNumber());
		mapResult.put("pageSize", page.getPageSize());
		mapResult.put("page", page.getPageNumber());
		mapResult.put("totalPage", page.getTotalPages());
		mapResult.put("total", page.getTotalCount());
		mapResult.put("rows", page.getResult());
		return toJsonString(page,mapResult);
	}
	
	public static String jsonObjectToJsonJQGrid(JSONObject jsonObject) {
		String jsonResult = null;
		
		try {
			if(jsonObject==null){
				return null;
			}
			jsonResult=jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	public static String pageToJsonJQGridWithNoDateFormat(Page page) {
		Map<String,Object> mapResult = new HashMap<String,Object>();
		mapResult.put("page", page.getPageNumber());
		mapResult.put("total", page.getTotalPages());
		mapResult.put("records", page.getTotalCount());
		mapResult.put("rows", page.getResult());
		
		JSONObject jsonObj = new JSONObject();
		return jsonObjectToJsonJQGrid(jsonObj.fromObject(mapResult));
	}

	public static String toJsonString(Page page,Object object){
		return page.toJsonString(object);
	}
	
	public String toJsonString(Page page,List list){
		return page.toJsonString(list);
	}

	public static String getJsonpage() {
		return JSONPAGE;
	}

	public static String getJsonrows() {
		return JSONROWS;
	}
	
}
