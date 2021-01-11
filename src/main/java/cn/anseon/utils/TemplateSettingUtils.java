package cn.anseon.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板设置类
 *
 * @author GR
 * @date 2021-1-11 17:35
 */
public class TemplateSettingUtils {

    /**
     * 获取模板数据
     *
     * @return java.util.Map<java.lang.String, java.lang.String>
     */
    public static Map<String, String> getTemplatePathMap() {
        Map<String, String> templateMap = new HashMap<>();
        templateMap.put("Controller", "/templates/Controller.java.vm");
        templateMap.put("Service", "/templates/Service.java.vm");
        templateMap.put("ServiceImpl", "/templates/ServiceImpl.java.vm");
        templateMap.put("DTO", "/templates/DTO.java.vm");
        templateMap.put("DO", "/templates/DO.java.vm");
        templateMap.put("VO", "/templates/VO.java.vm");
        return templateMap;
    }
}
