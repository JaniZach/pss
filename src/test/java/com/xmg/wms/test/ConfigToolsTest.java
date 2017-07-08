package com.xmg.wms.test;


import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

public class ConfigToolsTest {
    @Test
    public void testPassword() throws Exception{
        String password = ConfigTools.encrypt("admin");
        System.out.println(password);
    }
}
