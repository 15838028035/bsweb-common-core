package com.lj.app.core.common.base.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.entity.UpmDictionary;
import com.lj.app.core.common.base.entity.UpmDictionaryNote;
import com.lj.app.core.common.util.SpringContextHolder;

@Service("upmDictionaryNoteApiService")
public class UpmDictionaryNoteApiServiceImpl implements UpmDictionaryNoteApiService {

	@Autowired
	private UpmDictionaryNoteService<UpmDictionaryNote> upmDictionaryNoteService;
	
	@Autowired
	private UpmDictionaryService<UpmDictionary> upmDictionaryService;
	
	public List<UpmDictionary> findUpmDictionaryListBy(String typeCode){
		 List<UpmDictionary> list = null;
		 UpmDictionaryService<UpmDictionary> upmDictionaryService =(UpmDictionaryService<UpmDictionary>)SpringContextHolder.getBean("upmDictionaryService");
		 
		 if(typeCode == null){
			 return null;
		 }
		 
		 Map<String,String> paramMap = new HashMap<String,String>();
		 paramMap.put("typeCode",typeCode);
		 
		 List<UpmDictionaryNote> upmDictionaryNoteList = upmDictionaryNoteService.queryForList("select",paramMap);
		 
		 if(upmDictionaryNoteList!=null&&upmDictionaryNoteList.size()>0){
			 
			 UpmDictionaryNote upmDictionaryNote = (UpmDictionaryNote)upmDictionaryNoteList.get(0);
			 
			 Map<String,Integer> paramMap2 = new HashMap<String,Integer>();
			 paramMap2.put("nodeId",upmDictionaryNote.getId());
			 
			 list = upmDictionaryService.queryForList("select",paramMap2);
		 }
		 
		 return list;
	}
	
	public Map<String,String> findUpmDictionaryListMapBy(String typeCode){
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		 List<UpmDictionary> list = null;
		 UpmDictionaryService upmDictionaryService =(UpmDictionaryService)SpringContextHolder.getBean("upmDictionaryService");
		 
		 if(typeCode == null){
			 return null;
		 }
		 
		 Map<String,String> paramMap = new HashMap<String,String>();
		 paramMap.put("typeCode",typeCode);
		 
		 List<UpmDictionaryNote> upmDictionaryNoteList = upmDictionaryNoteService.queryForList("select",paramMap);
		 
		 if(upmDictionaryNoteList!=null&&upmDictionaryNoteList.size()>0){
			 
			UpmDictionaryNote upmDictionaryNote = (UpmDictionaryNote)upmDictionaryNoteList.get(0);
			 
			 Map<String,Integer> paramMap2 = new HashMap<String,Integer>();
			 paramMap2.put("nodeId",upmDictionaryNote.getId());
			 
			 list = upmDictionaryService.queryForList("select",paramMap2);
		 }
		 
		 if(list!=null&&list.size()>0){
			 for(UpmDictionary UpmDictionary :list){
				 map.put(UpmDictionary.getDataCode(), UpmDictionary.getDataDesc());
			 }
		 }
		 return map;
	}

	public UpmDictionaryNoteService getUpmDictionaryNoteService() {
		return upmDictionaryNoteService;
	}

	public void setUpmDictionaryNoteService(
			UpmDictionaryNoteService upmDictionaryNoteService) {
		this.upmDictionaryNoteService = upmDictionaryNoteService;
	}

	public UpmDictionaryService getUpmDictionaryService() {
		return upmDictionaryService;
	}

	public void setUpmDictionaryService(UpmDictionaryService upmDictionaryService) {
		this.upmDictionaryService = upmDictionaryService;
	}
	
}
