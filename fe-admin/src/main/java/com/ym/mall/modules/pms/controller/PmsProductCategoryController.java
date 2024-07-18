package com.ym.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.mall.common.api.CommonPage;
import com.ym.mall.common.api.CommonResult;
import com.ym.mall.modules.pms.model.PmsProductCategory;
import com.ym.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.ym.mall.modules.pms.model.dto.ProductCateChildrenDTO;
import com.ym.mall.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService pmsProductCategoryService;

    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page page = pmsProductCategoryService.list(parentId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    public CommonResult updateNavStatus(@RequestParam(value = "ids") List<Long> ids,
                                        @RequestParam(value = "navStatus") Integer navStatus) {
        boolean result = pmsProductCategoryService.updateNavStatus(ids, navStatus);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    public CommonResult showStatus(@RequestParam(value = "ids") List<Long> ids,
                                   @RequestParam(value = "showStatus") Integer showStatus) {
        boolean result = pmsProductCategoryService.showStatus(ids, showStatus);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    ///productCategory/delete/'+id,
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult deleteById(@PathVariable Long id) {
        boolean result = pmsProductCategoryService.removeById(id);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    /**
     *     url:'/productCategory/create',
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody PmsProductCategoryDTO productCategoryDTO) {
        boolean result = pmsProductCategoryService.customSave(productCategoryDTO);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    /**
     * '/productCategory/'+id,
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<PmsProductCategory> getById(@PathVariable Long id) {
        PmsProductCategory productCategory = pmsProductCategoryService.getById(id);
        return CommonResult.success(productCategory);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@RequestBody PmsProductCategoryDTO productCategory) {
        boolean result = pmsProductCategoryService.update(productCategory);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    /**
     * product levels relation api
     */
    @RequestMapping("list/withChildren")
    public CommonResult listWithChildren() {
        List<ProductCateChildrenDTO> list = pmsProductCategoryService.getWithChildren();
        return CommonResult.success(list);
    }

}


