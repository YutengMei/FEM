package com.ym.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.mall.common.api.CommonPage;
import com.ym.mall.common.api.CommonResult;
import com.ym.mall.modules.pms.model.PmsProductAttribute;
import com.ym.mall.modules.pms.model.dto.RelationAttrInfoDTO;
import com.ym.mall.modules.pms.service.PmsProductAttributeService;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**

 * @author Yuteng Mei
 * @since 2024-07-05
 */
@RestController
@RequestMapping("productAttribute")
public class PmsProductAttributeController {

    @Autowired
    private PmsProductAttributeService pmsProductAttributeService;

    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductAttribute>> getList(@PathVariable Long cid,
                                                                 @RequestParam(value = "type") Integer type,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page page = pmsProductAttributeService.list(cid, type, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody PmsProductAttribute pmsProductAttribute) {
        boolean result = pmsProductAttributeService.create(pmsProductAttribute);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@RequestBody PmsProductAttribute pmsProductAttribute) {
        boolean result = pmsProductAttributeService.updateById(pmsProductAttribute);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<PmsProductAttribute> getById(@PathVariable Long id) {
        PmsProductAttribute productCategory = pmsProductAttributeService.getById(id);
        return CommonResult.success(productCategory);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult deleteById(@RequestParam("ids") List<Long> ids) {
        boolean result = pmsProductAttributeService.delete(ids);
        if (result == true) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/attrInfo/{cid}", method = RequestMethod.GET)
    public CommonResult getRelationAttrInfoByCid(@PathVariable Long cid) {
        List<RelationAttrInfoDTO> relationAttrInfoDTOS =  pmsProductAttributeService.getRelationAttrInfoByCid(cid);
        return CommonResult.success(relationAttrInfoDTOS);
    }

}

