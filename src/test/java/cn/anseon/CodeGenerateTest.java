package cn.anseon;

import cn.anseon.domain.FastDomain;
import cn.anseon.proxy.CodeGenerateProxy;
import org.junit.Test;

/**
 * @author GR
 * @date 2021-1-7 14:12
 */
public class CodeGenerateTest {

    @Test
    public void run() {
        new CodeGenerateProxy().run(new FastDomain());
    }
}
