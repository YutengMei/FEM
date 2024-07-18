package com.ym.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.mall.common.api.CommonPage;
import com.ym.mall.common.api.CommonResult;
import com.ym.mall.modules.pms.model.PmsProduct;
import com.ym.mall.modules.pms.model.dto.ProductConditionDTO;
import com.ym.mall.modules.pms.service.PmsProductService;
import com.ym.mall.modules.ums.dto.ProductSaveParamsDTO;
import com.ym.mall.modules.ums.dto.ProductUpdateInitDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@RestController
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService pmsProductService;

    /**
     *     keyword: null,
     *     pageNum: 1,
     *     pageSize: 5,
     *     publishStatus: null,
     *     verifyStatus: null,
     *     productSn: null,
     *     productCategoryId: null,
     *     brandId: null
     * @return
     */
    @ApiOperation("Product list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list(ProductConditionDTO condition) {
        Page page = pmsProductService.list(condition);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        boolean result = pmsProductService.removeByIds(ids);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/update/newStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@RequestParam("ids") List<Long> ids, @RequestParam("newStatus") Integer newStatus) {
        boolean res = pmsProductService.updateStatus(newStatus, ids, PmsProduct::getNewStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam("recommendStatus") Integer recommendStatus) {
        boolean res = pmsProductService.updateStatus(recommendStatus, ids, PmsProduct::getRecommandStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    public CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids, @RequestParam("publishStatus") Integer publishStatus) {
        boolean res = pmsProductService.updateStatus(publishStatus, ids, PmsProduct::getPublishStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody ProductSaveParamsDTO productSaveParamsDTO) {
       boolean res = pmsProductService.create(productSaveParamsDTO);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/updateInfo/{id}")
    public CommonResult getUpdateInfo(@PathVariable Long id) {
        ProductUpdateInitDTO updateInitDTO = this.pmsProductService.getUpdateInfo(id);
        return CommonResult.success(updateInitDTO);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@RequestBody @Valid ProductSaveParamsDTO productSaveParamsDTO) {
        boolean res = pmsProductService.update(productSaveParamsDTO);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }



}

