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
        templateMap.put("Controller.java", "/templates/Controller.java.vm");
        templateMap.put("Service.java", "/templates/Service.java.vm");
        templateMap.put("ServiceImpl.java", "/templates/ServiceImpl.java.vm");
        templateMap.put("DTO.java", "/templates/DTO.java.vm");
        templateMap.put("DO.java", "/templates/DO.java.vm");
        templateMap.put("VO.java", "/templates/VO.java.vm");
        return templateMap;
    }
}
