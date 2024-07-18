package com.ym.mall;

import com.ym.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {StartApp.class})
public class ProductTests {

    @Autowired
    PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    @Test
    public void test(){
        System.out.println(pmsProductAttributeCategoryMapper.getListWithAttr());
    }

}
