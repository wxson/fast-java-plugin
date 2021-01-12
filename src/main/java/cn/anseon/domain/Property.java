package cn.anseon.domain;

import lombok.Data;

/**
 * 属性值
 *
 * @author GR
 * @date 2021-1-12 14:04
 */
public class Property {
    private String type;
    private String name;

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

    @Override
    public String toString() {
        return "Property{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
