package com.lj.app.core.common.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.base.model.UpmDictionary;
import com.lj.app.core.common.base.model.UpmDictionaryNote;
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

	public static String typeAndDateCodeToName(String typeCode, String dataCode) {
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

	public static List<UpmDictionary> findByTypeCode(String typeCode) {
		initData();
		return (List<UpmDictionary>) dictionaryMap.get(typeCode);
	}

	public static UpmDictionary findDicData(String typeCode, String dataCode) {
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

	public  void refreshDataNotStatic() {
		dictionaryMap.clear();
		logger.warn("====Refreshing data====");
		
		UpmDictionaryNote entityNote = new UpmDictionaryNote();
		
		upmDictionaryNoteService = SpringContextHolder.getBean("upmDictionaryNoteService");
		
		upmDictionaryService = SpringContextHolder.getBean("upmDictionaryService");
		List<UpmDictionaryNote> entityNoteList = upmDictionaryNoteService.queryForList("select",entityNote);
		
		for (UpmDictionaryNote uapNote : entityNoteList) {
			String typeCode = uapNote.getTypeCode();
			UpmDictionary entity = new UpmDictionary();
			entity.setTypeCode(typeCode);
			List<UpmDictionary> entityList = upmDictionaryService.queryForList("select",entity);
			dictionaryMap.put(typeCode, entityList);
		}
	}
	
	public static  void initData() {
		if(dictionaryMap==null|| dictionaryMap.size()==0){
			DictionaryUtil.getInstance().refreshDataNotStatic();
		}
	}
	
	public static  void refreshData() {
		initData();
	}

}