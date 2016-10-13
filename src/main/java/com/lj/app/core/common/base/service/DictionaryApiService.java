package com.lj.app.core.common.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.entity.UpmDictionary;

@Service("dictionaryApiService")
public class DictionaryApiService {

	@Autowired
	private UpmDictionaryService<UpmDictionary> upmDictionaryService;

	public List<UpmDictionary> findByExample(UpmDictionary upmDictionary) {
		return this.upmDictionaryService.queryForList("select",upmDictionary);
	}

	public void saveUpmDictionary(UpmDictionary upmDictionary) {
		this.upmDictionaryService.insertObject(upmDictionary);
	}

	public List<UpmDictionary> getAllUpmDictionary() {
		return this.upmDictionaryService.queryForList("select");
	}
	
	public String typeAndDateCodeToName(String typeCode, String dataCode) {
		return DictionaryUtil.typeAndDateCodeToName(typeCode, dataCode);
	}

	public List<UpmDictionary> findByTypeCode(String typeCode) {
		return DictionaryUtil.findByTypeCode(typeCode);
	}

	public List<UpmDictionary> getDicData(String typeCode) {
		return DictionaryUtil.findByTypeCode(typeCode);
	}

	public UpmDictionary findDicData(String typeCode, String dataCode) {
		return DictionaryUtil.findDicData(typeCode, dataCode);
	}

	public UpmDictionary findDicDataNoMap(String typeCode, String dataCode) {
		UpmDictionary dic = new UpmDictionary();
		dic.setTypeCode(typeCode);
		dic.setDataCode(dataCode);
		return (UpmDictionary) this.upmDictionaryService.queryForList("select",dic).get(0);
	}

	public List<UpmDictionary> listAllUpmDictionary() {
		return this.upmDictionaryService.queryForList("select");
	}

	public List<UpmDictionary> listUpmDictionaryByExample(UpmDictionary entity) {
		return this.upmDictionaryService.queryForList("select",entity);
	}

	public void deleteByNoteCode(String typeCode) {
		this.upmDictionaryService.delete(typeCode);
	}
}