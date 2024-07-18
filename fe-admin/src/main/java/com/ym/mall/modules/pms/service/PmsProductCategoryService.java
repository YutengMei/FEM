package com.ym.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.mall.modules.pms.model.PmsProductCategory;
import com.ym.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.ym.mall.modules.pms.model.dto.ProductCateChildrenDTO;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    /**
     * get product category list
     * @param parentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page list(Long parentId, Integer pageNum, Integer pageSize);

    boolean updateNavStatus(List<Long> ids, Integer navStatus);

    boolean showStatus(List<Long> ids, Integer showStatus);

    boolean customSave(PmsProductCategoryDTO productCategoryDTO);

    boolean update(PmsProductCategoryDTO productCategory);

    List<ProductCateChildrenDTO> getWithChildren();
}
