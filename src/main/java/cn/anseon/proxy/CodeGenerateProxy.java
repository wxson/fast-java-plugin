package cn.anseon.proxy;

import cn.anseon.constants.CommonConstants;
import cn.anseon.domain.FastDomain;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.net.URL;
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
     * @param fastDomain     新建信息
     * @param velocityEngine velocity装载器
     * @return java.util.List<org.apache.velocity.Template>
     */
    private Map<String, Template> findTemplates(FastDomain fastDomain, VelocityEngine velocityEngine) {
        Map<String, Template> templateMap = new HashMap<>();
        URL templatesUrl = this.getClass().getResource("/templates");
        File templatesFile = new File(templatesUrl.getPath());
        if (!templatesFile.isDirectory()) {
            return templateMap;
        }

        File[] files = templatesFile.listFiles();
        for (File file : files) {
            String templateName = file.getName();
            if (!templateName.endsWith(".vm")) {
                continue;
            }
            String targetFileName = fastDomain.getActionAbsoluteDir() + "\\" + this.buildTargetFile(fastDomain, templateName);
            Template template = velocityEngine.getTemplate("templates/" + templateName, "UTF-8");
            templateMap.put(targetFileName, template);
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
        // 获得模板名称：如：VO.java.vm ——> VO.java
        templateName = templateName.substring(0, templateName.length() - 2);
        // 如果是接口
        if (templateName.startsWith(CommonConstants.DOMAIN_I_SERVICE_SUFFIX) && !templateName.startsWith(CommonConstants.DOMAIN_SERVICE_IMPL_SUFFIX)) {
            return baseDir + interfaceSuffix + fastJavaClassName + templateName;
        }

        return baseDir + fastJavaClassName + templateName;
    }

    /**
     * 开始执行代码生成
     *
     * @param fastDomain fast����
     */
    private void startGeneraCode(FastDomain fastDomain) {
        // init Properties
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        Velocity.init(properties);
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("author", "Fast Java");
        velocityContext.put("date", this.getCurrentDate());
        velocityContext.put("basePackage", fastDomain.getActionDir().replace("/", "."));
        velocityContext.put("domain", fastDomain.getFastJavaClassName());
        velocityContext.put("domainMapping", fastDomain.getDomainMapping());

        // 获取所有模板信息
        Map<String, Template> templateMap = findTemplates(fastDomain, velocityEngine);
        for (String targetFileName : templateMap.keySet()) {
            File targetFile = new File(targetFileName);
            try {
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }

                if (!targetFile.exists()) {
                    targetFile.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (FileOutputStream outStream = new FileOutputStream(targetFile);
                 OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
                 BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                Template template = templateMap.get(targetFileName);
                if (Objects.nonNull(template)) {
                    template.merge(velocityContext, bufferedWriter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
