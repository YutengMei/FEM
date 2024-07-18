package com.ym.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ym.mall.modules.pms.model.PmsProductCategory;
import com.ym.mall.modules.pms.model.dto.ProductCateChildrenDTO;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

    List<ProductCateChildrenDTO> getWithChildren();
}
