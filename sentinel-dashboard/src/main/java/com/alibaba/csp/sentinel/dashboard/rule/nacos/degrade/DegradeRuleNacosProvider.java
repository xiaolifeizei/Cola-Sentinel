package com.alibaba.csp.sentinel.dashboard.rule.nacos.degrade;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : cui_feng
 * @since : 2022-08-23 13:59
 */
@Component("degradeRuleNacosProvider")
public class DegradeRuleNacosProvider implements DynamicRuleProvider<List<DegradeRuleEntity>> {

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder;

    @Override
    public List<DegradeRuleEntity> getRules(String appName) throws Exception {
        String rule = configService.getConfig(appName + NacosConfigUtil.DEGRADE_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID, 3000);
        return degradeRuleEntityDecoder.convert(rule);
    }
}
