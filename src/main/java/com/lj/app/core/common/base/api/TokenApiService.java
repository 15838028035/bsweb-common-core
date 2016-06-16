package com.lj.app.core.common.base.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.model.UpmToken;
import com.lj.app.core.common.base.service.UpmTokenService;


/**
 * 获取spring服务Bean使用名称：tokenApiService
 */

@Service("tokenApiService")
@SuppressWarnings("unchecked")
public class TokenApiService {
	
	@Autowired
	private UpmTokenService<UpmToken> upmTokenService;

	public void saveToken(UpmToken upmToken) {
		upmTokenService.insertObject("insert",upmToken);
	}
	
	public List<UpmToken> queryForList(Map<String,Object> map) {
		return upmTokenService.queryForList("select",map);
	}
	
	public void delete(int id) {
		upmTokenService.delete(id);
	}
}
