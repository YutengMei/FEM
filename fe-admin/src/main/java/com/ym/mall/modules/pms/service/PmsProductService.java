package com.ym.mall.modules.pms.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.mall.modules.pms.model.PmsProduct;
import com.ym.mall.modules.pms.model.dto.ProductConditionDTO;
import com.ym.mall.modules.ums.dto.ProductSaveParamsDTO;
import com.ym.mall.modules.ums.dto.ProductUpdateInitDTO;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
public interface PmsProductService extends IService<PmsProduct> {

    Page list(ProductConditionDTO condition);

    boolean updateStatus(Integer newStatus, List<Long> ids, SFunction<PmsProduct, ?> getNewStatus);

    boolean create(ProductSaveParamsDTO productSaveParamsDTO);

    ProductUpdateInitDTO getUpdateInfo(Long id);

    boolean update(ProductSaveParamsDTO productSaveParamsDTO);
}
