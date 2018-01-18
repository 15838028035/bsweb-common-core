package com.lj.app.core.common.base.service;

import org.springframework.stereotype.Service;

/**
 * reemarker模版配置
 * @param <FreemarkerTemplateConfig> 模板对象
 */
@Service("freemarkerTemplateConfigService")
public class FreemarkerTemplateConfigServiceImpl<FreemarkerTemplateConfig> extends
    BaseServiceImpl<FreemarkerTemplateConfig> implements FreemarkerTemplateConfigService<FreemarkerTemplateConfig> {

}
