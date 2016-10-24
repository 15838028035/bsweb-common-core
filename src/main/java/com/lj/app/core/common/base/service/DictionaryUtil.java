package com.lj.app.core.common.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.base.entity.UpmDictionary;
import com.lj.app.core.common.base.entity.UpmDictionaryNote;
import com.lj.app.core.common.util.SpringContextHolder;

public class DictionaryUtil {
	private static Log logger = LogFactory.getLog(DictionaryUtil.class);
	private static Map<String, List<UpmDictionary>> dictionaryMap = new HashMap<String, List<UpmDictionary>>();
	
	@Autowired
	private UpmDictionaryNoteService<UpmDictionaryNote> upmDictionaryNoteService;
	
	@Autowired
	private UpmDictionaryService<UpmDictionary> upmDictionaryService;
	
	private static  DictionaryUtil dictionaryUtil = null;
	
	public static  DictionaryUtil getInstance() {
		if(dictionaryUtil ==null) {
			dictionaryUtil = new DictionaryUtil();
		}
		return dictionaryUtil;
	}

	public static String typeAndDateCodeToName(String typeCode, String dataCode) throws Exception{
		initData();
		List<UpmDictionary> uList = (List<UpmDictionary>) dictionaryMap.get(typeCode);
		String dataDesc = null;
		if (uList == null)
			return null;
		for (UpmDictionary upmDictionary : uList) {
			if (dataCode.equalsIgnoreCase(upmDictionary.getDataCode())) {
				dataDesc = upmDictionary.getDataDesc();
				break;
			}
		}
		return dataDesc;
	}

	public static List<UpmDictionary> findByTypeCode(String typeCode) throws Exception{
		initData();
		return (List<UpmDictionary>) dictionaryMap.get(typeCode);
	}

	public static UpmDictionary findDicData(String typeCode, String dataCode)  throws Exception{
		initData();
		List<UpmDictionary> uList = (List<UpmDictionary>) dictionaryMap.get(typeCode);
		if (uList == null)
			return null;
		UpmDictionary uu = new UpmDictionary();
		for (UpmDictionary upmDictionary : uList) {
			if (dataCode.equalsIgnoreCase(upmDictionary.getDataCode())) {
				uu = upmDictionary;
				break;
			}
		}
		return uu;
	}

	public  void refreshDataNotStatic() throws Exception {
		dictionaryMap.clear();
		logger.warn("====Refreshing data====");
		
		UpmDictionaryNote entityNote = new UpmDictionaryNote();
		
		upmDictionaryNoteService = SpringContextHolder.getBean("upmDictionaryNoteService");
		
		upmDictionaryService = SpringContextHolder.getBean("upmDictionaryService");
		List<UpmDictionaryNote> entityNoteList = upmDictionaryNoteService.queryForList("select",entityNote);
		
		for (UpmDictionaryNote uapNote : entityNoteList) {
			String typeCode = uapNote.getTypeCode();
			Map<String,String> upmDictionaryMap = new HashMap<String,String>();
			upmDictionaryMap.put("typeCode", typeCode);
			
			List<UpmDictionary> entityList = upmDictionaryService.queryForList("select",upmDictionaryMap);
			dictionaryMap.put(typeCode, entityList);
		}
	}
	
	public static  void initData() throws Exception {
		if(dictionaryMap==null|| dictionaryMap.size()==0){
			DictionaryUtil.getInstance().refreshDataNotStatic();
		}
	}
	
	public static  void refreshData()  {
		try {
			initData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}