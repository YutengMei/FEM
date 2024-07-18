package com.ym.mall.modules.pms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.mall.common.api.CommonPage;
import com.ym.mall.common.api.CommonResult;
import com.ym.mall.modules.pms.model.PmsProductAttributeCategory;
import com.ym.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.ym.mall.modules.pms.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService pmsProductAttributeCategoryService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page page = pmsProductAttributeCategoryService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(PmsProductAttributeCategory productAttributeCategory) {
        boolean result = pmsProductAttributeCategoryService.add(productAttributeCategory);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(PmsProductAttributeCategory productAttributeCategory) {
        boolean result = pmsProductAttributeCategoryService.updateById(productAttributeCategory);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteById(@PathVariable Long id) {
        boolean result = pmsProductAttributeCategoryService.removeById(id);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    public CommonResult getListWithAttr() {
        List<ProductAttributeCateDTO>  list = pmsProductAttributeCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }
}

