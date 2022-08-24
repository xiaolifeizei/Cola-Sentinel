package com.alibaba.csp.sentinel.dashboard.rule.nacos.flow;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
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
@Component("flowRuleNacosProvider")
public class FlowRuleNacosProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder;

    @Override
    public List<FlowRuleEntity> getRules(String appName) throws Exception {
        String rule = configService.getConfig(appName + NacosConfigUtil.FLOW_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID, 3000);
        return flowRuleEntityDecoder.convert(rule);
    }
}
