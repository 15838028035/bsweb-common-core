package com.lj.app.core.common.flows;

import java.util.Random;

import com.lj.app.core.common.flows.model.ProcessModel;
import com.lj.app.core.common.util.DateUtil;

/**
 * 默认的流程实例编号生成器
 * 编号生成规则为:yyyyMMdd-HH:mm:ss-SSS-random
 */
public class DefaultNoGenerator implements INoGenerator {
	public String generate(ProcessModel model) {
		String time =DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss");
		Random random = new Random();
		return time + "-" + random.nextInt(1000);
	}
}