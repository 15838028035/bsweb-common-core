package com.lj.app.core.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class Page {

	private static Logger log = Logger.getLogger(Page.class);

	public Page() {

	}

	public Page(int totalRecords, int recordsOfPage) {
		this.totalRecords = totalRecords;
		this.recordsOfPage = recordsOfPage;
	}

	/**
	 * page :the number of the requested page
	 */
	private int currentPage;

	/**
	 * the total records from the query;
	 */
	private int totalRecords;

	/**
	 * rowNum Sets how many records we want to view in the grid
	 */
	private int recordsOfPage;

	/**
	 * Sets the initial sorting column
	 */
	private String sortname;

	/**
	 * 升序或者降序
	 */
	private String sortorder;

	private static final String JSONROWS = "rows";

	private static final String JSONPAGE = "page";

	public int getRecordsOfPage() {
		return recordsOfPage;
	}

	public void setRecordsOfPage(int recordsOfPage) {
		this.recordsOfPage = recordsOfPage;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		int totalPages = 1;
		if (totalRecords % recordsOfPage == 0) {
			totalPages = totalRecords / recordsOfPage;
		} else {
			totalPages = totalRecords / recordsOfPage + 1;
		}
		return totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPageSkipResults() {
		return this.getCurrentPageRecordesIndexStart();
	}

	/**
	 * 返回本页的开始记录序号，下标从0开始计算
	 * 
	 * @return
	 */
	public int getCurrentPageRecordesIndexStart() {
		int nextPageRecordesIndexStart = 0;
		int temp = (this.currentPage - 1) * this.recordsOfPage + 1;
		if (temp > totalRecords) {

		} else {
			nextPageRecordesIndexStart = temp;
		}
		return nextPageRecordesIndexStart - 1;
	}

	/**
	 * 返回本页的结束记录序号，下标从0开始计算
	 * 
	 * @return
	 */
	public int getCurrentPageRecordsIndexEnd() {
		int nextPageRecordsIndexEnd = 0;
		nextPageRecordsIndexEnd = (this.currentPage) * this.recordsOfPage;
		if (nextPageRecordsIndexEnd > totalRecords) {
			nextPageRecordsIndexEnd = totalRecords;
		}
		return nextPageRecordsIndexEnd - 1;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String toJsonString(List<Object> list) {
		JsonConfig cfg = new JsonConfig();
		DateJsonFormat df = new DateJsonFormat();
		cfg.registerJsonValueProcessor(java.util.Date.class, df);

		JSONObject all = new JSONObject();
		if (list == null) {
			all.put(JSONROWS, "");
		} else {
			all.put(JSONROWS, JSONArray.fromObject(list, cfg));
		}

		all.put(JSONPAGE, this);
		String jsonString = all.toString();
		log.debug(jsonString);
		return jsonString;
	}

	public static String toJsonString(Object object) {

		JsonConfig cfg = new JsonConfig();
		DateJsonFormat df = new DateJsonFormat();
		cfg.registerJsonValueProcessor(java.util.Date.class, df);

		JSONObject all = JSONObject.fromObject(object, cfg);
		String jsonString = all.toString();
		log.debug(jsonString);
		return jsonString;
	}

	public static Map<String, Object> toMap(Object o)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return BeanUtils.describe(o);
	}
}
