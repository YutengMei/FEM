package com.ym.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.mall.modules.pms.model.PmsProductAttributeCategory;
import com.ym.mall.modules.pms.model.dto.ProductAttributeCateDTO;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    Page list(Integer pageNum, Integer pageSize);

    boolean add(PmsProductAttributeCategory productAttributeCategory);

    List<ProductAttributeCateDTO> getListWithAttr();
}
