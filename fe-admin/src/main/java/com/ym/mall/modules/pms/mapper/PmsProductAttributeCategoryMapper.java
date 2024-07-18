package com.ym.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ym.mall.modules.pms.model.PmsProductAttributeCategory;
import com.ym.mall.modules.pms.model.dto.ProductAttributeCateDTO;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {

    List<ProductAttributeCateDTO> getListWithAttr();
}
