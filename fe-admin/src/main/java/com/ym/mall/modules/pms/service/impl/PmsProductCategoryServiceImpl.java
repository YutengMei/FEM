package com.ym.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.mall.modules.pms.mapper.PmsProductCategoryAttributeRelationMapper;
import com.ym.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.ym.mall.modules.pms.model.PmsProductCategory;
import com.ym.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.ym.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.ym.mall.modules.pms.model.dto.ProductCateChildrenDTO;
import com.ym.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.ym.mall.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

    @Autowired
    private PmsProductCategoryAttributeRelationService pmsProductCategoryAttributeRelationService;

    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;

    @Override
    public Page list(Long parentId, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductCategory::getParentId, parentId).orderByAsc(PmsProductCategory::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateNavStatus(List<Long> ids, Integer navStatus) {
        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        pmsProductCategoryUpdateWrapper.lambda().set(PmsProductCategory::getNavStatus, navStatus).in(PmsProductCategory::getId, ids);
        return this.update(pmsProductCategoryUpdateWrapper);
    }

    @Override
    public boolean showStatus(List<Long> ids, Integer showStatus) {
        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        pmsProductCategoryUpdateWrapper.lambda().set(PmsProductCategory::getShowStatus, showStatus).in(PmsProductCategory::getId, ids);
        return this.update(pmsProductCategoryUpdateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean customSave(PmsProductCategoryDTO productCategoryDTO) {
        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtils.copyProperties(productCategoryDTO, productCategory);
        productCategory.setProductCount(0);
        //level = 0
        if (productCategoryDTO.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            productCategory.setLevel(1);
        }
        this.save(productCategory);
        saveAttrRelation(productCategoryDTO, productCategory);
        return true;
    }

    private boolean saveAttrRelation(PmsProductCategoryDTO productCategoryDTO, PmsProductCategory productCategory) {
        List<Long> productAttributeIdList = productCategoryDTO.getProductAttributeIdList();
        List<PmsProductCategoryAttributeRelation> pmsProductCategoryAttributeRelationList = new ArrayList<>();
        for (Long attrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation pmsProductCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();
            pmsProductCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
            pmsProductCategoryAttributeRelation.setProductAttributeId(attrId);
            pmsProductCategoryAttributeRelationList.add(pmsProductCategoryAttributeRelation);
        }
        return pmsProductCategoryAttributeRelationService.saveBatch(pmsProductCategoryAttributeRelationList);
    }

    @Override
    public boolean update(PmsProductCategoryDTO productCategoryDTO) {
        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtils.copyProperties(productCategoryDTO, productCategory);
        //level = 0
        if (productCategoryDTO.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            productCategory.setLevel(1);
        }
        this.updateById(productCategory);

        //delete saved relation attribute
        QueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductCategoryAttributeRelation::getProductCategoryId, productCategory.getId());
        this.pmsProductCategoryAttributeRelationService.remove(queryWrapper);

        saveAttrRelation(productCategoryDTO, productCategory);
        return true;
    }

    @Override
    public List<ProductCateChildrenDTO> getWithChildren() {
        return pmsProductCategoryMapper.getWithChildren();
    }
}
