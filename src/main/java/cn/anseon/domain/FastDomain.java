package cn.anseon.domain;

import lombok.Data;

import java.util.List;

/**
 * @author GR
 * @date 2021-1-7 15:00
 */
@Data
public class FastDomain {
    /**
     * Request 请求映射
     */
    private String domainMapping;
    /**
     * 是否依赖fast-java
     */
    private Boolean dependFastJava;
    /**
     * action 目录
     */
    private String actionDir;
    /**
     * action绝对路径
     */
    private String actionAbsoluteDir;
    /**
     * 类名称
     */
    private String fastJavaClassName;
    /**
     * 新建类属性 json数据
     */
    private List<Property> propertyList;

    public Boolean getDependFastJava() {
        return dependFastJava;
    }

    public FastDomain setDependFastJava(Boolean dependFastJava) {
        this.dependFastJava = dependFastJava;
        return this;
    }

    public String getFastJavaClassName() {
        return fastJavaClassName;
    }

    /**
     * 重写设置类名方法，目的创建domainMapping
     *
     * @param fastJavaClassName 类名
     * @return cn.anseon.domain.FastDomain
     */
    public FastDomain setFastJavaClassName(String fastJavaClassName) {
        this.fastJavaClassName = fastJavaClassName;
        this.domainMapping = fastJavaClassName.toLowerCase();
        return this;
    }

    public String getDomainMapping() {
        return domainMapping;
    }

    public FastDomain setDomainMapping(String domainMapping) {
        this.domainMapping = domainMapping;
        return this;
    }

    public String getActionDir() {
        return actionDir;
    }

    public FastDomain setActionDir(String actionDir) {
        this.actionDir = actionDir;
        return this;
    }

    public String getActionAbsoluteDir() {
        return actionAbsoluteDir;
    }

    public FastDomain setActionAbsoluteDir(String actionAbsoluteDir) {
        this.actionAbsoluteDir = actionAbsoluteDir;
        return this;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public FastDomain setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
        return this;
    }
}
