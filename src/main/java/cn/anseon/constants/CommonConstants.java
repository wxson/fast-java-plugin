package cn.anseon.constants;

/**
 * 常用常量
 *
 * @author GR
 * @date 2021-1-7 13:20
 */
public class CommonConstants {
    /**
     * 实体根路径
     */
    public static final String DOMAIN_PACKAGE_BASE_PATH = "/domain/";
    /**
     * DTO 前缀
     */
    public static final String DOMAIN_DTO_SUFFIX = "DTO";
    public static final String DOMAIN_DTO_DIR = DOMAIN_PACKAGE_BASE_PATH + "/dto/";
    /**
     * DO 前缀
     */
    public static final String DOMAIN_DO_SUFFIX = "DO";
    public static final String DOMAIN_DO_DIR = DOMAIN_PACKAGE_BASE_PATH + "/db/";
    /**
     * VO 前缀
     */
    public static final String DOMAIN_VO_SUFFIX = "VO";
    public static final String DOMAIN_VO_DIR = DOMAIN_PACKAGE_BASE_PATH + "/vo/";

    /**
     * Controller dir
     */
    public static final String DOMAIN_CONTROLLER_SUFFIX = "Controller";
    public static final String DOMAIN_CONTROLLER_DIR = "/controller/";

    /**
     * Service dir
     */
    public static final String DOMAIN_I_SERVICE_SUFFIX = "Service";
    public static final String DOMAIN_I_SERVICE_DIR = "/service/";

    /**
     * ServiceImpl dir
     */
    public static final String DOMAIN_SERVICE_IMPL_SUFFIX = "ServiceImpl";
    public static final String DOMAIN_SERVICE_IMPL_DIR = "/service/impl/";
}
