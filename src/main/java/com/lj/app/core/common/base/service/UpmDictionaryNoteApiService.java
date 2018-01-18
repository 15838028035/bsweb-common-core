package com.lj.app.core.common.base.service;

import java.util.List;
import java.util.Map;

import com.lj.app.core.common.base.entity.UpmDictionary;

public interface UpmDictionaryNoteApiService {

  public List<UpmDictionary> findUpmDictionaryListBy(String typeCode);

  public Map<String, String> findUpmDictionaryListMapBy(String typeCode);
}
