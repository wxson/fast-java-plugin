package cn.anseon.utils;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author GR
 * @date 2021-1-11 17:59
 */
public class VelocityUtils {

    private final static Properties PROPERTIES = new Properties();

    static {
        PROPERTIES.setProperty("resource.loader", "jar");
        PROPERTIES.setProperty("jar.resource.loader.class", "org.apache.velocity.runtime.resource.loader.JarResourceLoader");
        PROPERTIES.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        PROPERTIES.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
    }

    /**
     * 生成模板
     *
     * @param template 模板内容
     * @param map      模板内相关属性
     * @return java.lang.String
     */
    public static String evaluate(String template, Map<String, Object> map) {
        VelocityContext context = new VelocityContext();
        VelocityEngine velocityEngine = new VelocityEngine(PROPERTIES);
        if (Objects.nonNull(map)) {
            map.forEach(context::put);
        }

        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(context, writer, "", template);
        return writer.toString();
    }
}
