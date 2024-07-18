package com.ym.mall.modules.ums.controller;


import com.ym.mall.common.api.CommonResult;
import com.ym.mall.modules.ums.model.UmsMemberLevel;
import com.ym.mall.modules.ums.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-16
 */
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    private UmsMemberLevelService umsMemberLevelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list(
            @RequestParam(value = "defaultStatus", defaultValue = "0") Integer defaultStatus
    ) {
        List<UmsMemberLevel> list = umsMemberLevelService.list(defaultStatus);
        return CommonResult.success(list);
    }

}

