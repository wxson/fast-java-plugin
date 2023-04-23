package cn.anseon.enums;

/**
 * @author GR
 * @date 2023-4-20 11:31
 */
public enum PersistenceTypeEnum {
    /**
     * MySQL
     */
    MYSQL("MySQL"),
    /**
     * MongoDB
     */
    MONGODB("MongoDB"),
    ;

    private String desc;

    PersistenceTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
