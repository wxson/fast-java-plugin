package cn.anseon.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具
 *
 * @author GR
 * @date 2021-1-12 9:15
 */
public class LocalFileUtils {

    /**
     * 保存文件
     *
     * @param file     文件信息
     * @param resource 写入的文件类容
     */
    public static void saveFile(File file, String resource) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileUtils.writeStringToFile(file, resource, "utf8");
        } catch (IOException e) {
            throw new RuntimeException("save file fail");
        }
    }
}
