package cn.anseon.proxy;

import cn.anseon.constants.CommonConstants;
import cn.anseon.domain.FastDomain;
import cn.anseon.utils.LocalFileUtils;
import cn.anseon.utils.TemplateSettingUtils;
import cn.anseon.utils.VelocityUtils;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author GR
 * @date 2021-1-7 17:06
 */
public class CodeGenerateProxy {

    private static CodeGenerateProxy codeGenerateProxy;

    /**
     * 单列对象
     *
     * @return cn.anseon.proxy.CodeGenerateProxy
     */
    public static CodeGenerateProxy getInstance() {
        synchronized (CodeGenerateProxy.class) {
            if (null == codeGenerateProxy) {
                synchronized (CodeGenerateProxy.class) {
                    codeGenerateProxy = new CodeGenerateProxy();
                }
            }
        }
        return codeGenerateProxy;
    }

    /**
     * 获取所有模板数据
     *
     * @param fastDomain 新建信息
     * @return java.util.List<org.apache.velocity.Template>
     */
    private Map<String, String> findTemplates(FastDomain fastDomain) {
        Map<String, String> templateMap = new HashMap<>();
        // 获取模板与模板对象
        Map<String, String> templatePathMap = null;
        // 依赖fast-java
        if (fastDomain.getDependFastJava()) {
            templatePathMap = TemplateSettingUtils.getFastJavaTemplatePathMap();
        } else {
            templatePathMap = TemplateSettingUtils.getDefaultTemplatePathMap();
        }

        for (String templateName : templatePathMap.keySet()) {
            String targetFileName = fastDomain.getActionAbsoluteDir() + "\\" + this.buildTargetFile(fastDomain, templateName);
            try {
                String templateContent = FileUtil
                        .loadTextAndClose(CodeGenerateProxy.class.getResourceAsStream(templatePathMap.get(templateName)));
                templateMap.put(targetFileName, templateContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return templateMap;
    }

    /**
     * 构建目标文件
     *
     * @param fastDomain   新类信息
     * @param templateName 模板名称
     * @return java.lang.String
     */
    private String buildTargetFile(FastDomain fastDomain, String templateName) {
        String baseDir = "";
        String interfaceSuffix = "I";
        // dto
        if (templateName.startsWith(CommonConstants.DOMAIN_DTO_SUFFIX)) {
            baseDir = CommonConstants.DOMAIN_DTO_DIR;
        }
        // do
        if (templateName.startsWith(CommonConstants.DOMAIN_DO_SUFFIX)) {
            baseDir = CommonConstants.DOMAIN_DO_DIR;
        }
        // vo
        if (templateName.startsWith(CommonConstants.DOMAIN_VO_SUFFIX)) {
            baseDir = CommonConstants.DOMAIN_VO_DIR;
        }
        // controller
        if (templateName.startsWith(CommonConstants.DOMAIN_CONTROLLER_SUFFIX)) {
            baseDir = CommonConstants.DOMAIN_CONTROLLER_DIR;
        }
        // service
        if (templateName.startsWith(CommonConstants.DOMAIN_I_SERVICE_SUFFIX)) {
            baseDir = CommonConstants.DOMAIN_I_SERVICE_DIR;
        }
        // serviceImpl
        if (templateName.startsWith(CommonConstants.DOMAIN_SERVICE_IMPL_SUFFIX)) {
            baseDir = CommonConstants.DOMAIN_SERVICE_IMPL_DIR;
        }
        //首字母大写
        String upperCase = fastDomain.getFastJavaClassName().substring(0, 1).toUpperCase();
        String fastJavaClassName = upperCase + fastDomain.getFastJavaClassName().substring(1);
        // 如果是接口
        if (templateName.startsWith(CommonConstants.DOMAIN_I_SERVICE_SUFFIX) && !templateName.startsWith(CommonConstants.DOMAIN_SERVICE_IMPL_SUFFIX)) {
            return baseDir + interfaceSuffix + fastJavaClassName + templateName;
        }

        return baseDir + fastJavaClassName + templateName;
    }

    /**
     * 开始执行代码生成
     *
     * @param fastDomain fast
     */
    private void startGeneraCode(FastDomain fastDomain) {
        // 构建模板内相关属性
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("author", "Fast Java");
        contextMap.put("date", this.getCurrentDate());
        contextMap.put("basePackage", fastDomain.getActionDir().replace("/", "."));
        contextMap.put("domain", fastDomain.getFastJavaClassName());
        contextMap.put("domainMapping", fastDomain.getDomainMapping());

        // 获取所有模板信息
        Map<String, String> templateMap = findTemplates(fastDomain);
        for (String targetFileName : templateMap.keySet()) {
            File targetFile = new File(targetFileName);
            LocalFileUtils.saveFile(targetFile, VelocityUtils.evaluate(templateMap.get(targetFileName), contextMap));
            // 刷新目录
            LocalFileSystem.getInstance().refreshAndFindFileByIoFile(targetFile);
        }
    }

    /**
     * 获取当前时间
     *
     * @return java.lang.String
     */
    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    public void run(FastDomain fastDomain) {
        if (Objects.isNull(fastDomain)) {
            return;
        }
        // 首字母小写
        String requestMapping = fastDomain.getFastJavaClassName().substring(0, 1).toLowerCase() + fastDomain.getFastJavaClassName().substring(1);
        fastDomain.setDomainMapping(requestMapping);
        // 开始生成代码
        this.startGeneraCode(fastDomain);
    }
}
