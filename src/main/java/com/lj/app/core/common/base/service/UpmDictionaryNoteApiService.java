package com.lj.app.core.common.base.service;

import java.util.List;
import java.util.Map;

import com.lj.app.core.common.base.entity.UpmDictionary;

/**
 * 
 * 数据字典服务Api
 *
 */
public interface UpmDictionaryNoteApiService {

  /**
   * 根据类别查找
   */
  public List<UpmDictionary> findUpmDictionaryListBy(String typeCode);

  /**
   * 根据类别查找
   */
  public Map<String, String> findUpmDictionaryListMapBy(String typeCode);
}
