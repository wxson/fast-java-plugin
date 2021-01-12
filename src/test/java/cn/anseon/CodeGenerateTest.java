package cn.anseon;

import cn.anseon.domain.Property;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author GR
 * @date 2021-1-7 14:12
 */
public class CodeGenerateTest {

    @Test
    public void run() throws IOException {
        String str = "{\"name\":\"小明\",\"sex\":\"男\",\"age\":20,\"amount\":20.0,\"bean\":{\"account\":\"1234\"},\"list\":[{\"account\":\"1234\"}]}";
        ArrayList<Property> propertyList = new ArrayList<>();
        Map map = JSON.parseObject(str, Map.class);
        for (Object key : map.keySet()) {
            Property property = new Property();
            property.setName(key.toString());
            Object value = map.get(key);
            String typeName = value.getClass().getTypeName();
            // 获取属性类型
            typeName = typeName.substring(typeName.lastIndexOf(".") + 1);
            if ("BigDecimal".equals(typeName)) {
                typeName = "Double";
            }

            if ("JSONObject".equals(typeName)) {
                typeName = "Object";
            }

            if ("JSONArray".equals(typeName)) {
                typeName = "List";
            }

            property.setType(typeName);
            System.out.println(property);
            propertyList.add(property);
        }

    }
}
