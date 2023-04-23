package cn.anseon.domain;

/**
 * 属性值
 *
 * @author GR
 * @date 2021-1-12 14:04
 */
public class Property {
    private String type;
    private String name;
    private String camelCaseName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCamelCaseName() {
        return camelCaseName;
    }

    public void setCamelCaseName(String camelCaseName) {
        this.camelCaseName = camelCaseName;
    }

    @Override
    public String toString() {
        return "Property{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", camelCaseName='" + camelCaseName + '\'' +
                '}';
    }
}
