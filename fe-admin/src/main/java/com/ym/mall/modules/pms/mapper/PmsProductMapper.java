package com.ym.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ym.mall.modules.pms.model.PmsProduct;
import com.ym.mall.modules.ums.dto.ProductUpdateInitDTO;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    ProductUpdateInitDTO getUpdateInfo(Long id);
}
