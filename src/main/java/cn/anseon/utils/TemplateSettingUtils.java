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
     * 获取默认模板数据
     *
     * @return java.util.Map<java.lang.String, java.lang.String>
     */
    public static Map<String, String> getDefaultTemplatePathMap() {
        Map<String, String> templateMap = new HashMap<>();
        templateMap.put("ServiceImpl.java", "/templates/mysql/ServiceImpl.java.vm");
        templateMap.put("DO.java", "/templates/mysql/DO.java.vm");
        templateMap.put("Repository.java", "/templates/mysql/Repository.java.vm");
        TemplateSettingUtils.putCommonPath(templateMap);
        return templateMap;
    }

    /**
     * 获取默认模板数据
     *
     * @return java.util.Map<java.lang.String, java.lang.String>
     */
    public static Map<String, String> getMongoTemplatePathMap() {
        Map<String, String> templateMap = new HashMap<>();
        templateMap.put("ServiceImpl.java", "/templates/mongo/ServiceImpl.java.vm");
        templateMap.put("DO.java", "/templates/mongo/DO.java.vm");
        TemplateSettingUtils.putCommonPath(templateMap);
        return templateMap;
    }

    /**
     * 装载公共部分
     *
     * @param templateMap 模板信息
     */
    public static void putCommonPath(Map<String, String> templateMap) {
        templateMap.put("Controller.java", "/templates/common/Controller.java.vm");
        templateMap.put("Service.java", "/templates/common/Service.java.vm");
        templateMap.put("DTO.java", "/templates/common/DTO.java.vm");
        templateMap.put("VO.java", "/templates/common/VO.java.vm");
        templateMap.put("Converter.java", "/templates/common/Converter.java.vm");
    }
}
