package com.ym.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.ym.mall.modules.pms.model.PmsProductAttribute;
import com.ym.mall.modules.pms.model.PmsProductAttributeCategory;
import com.ym.mall.modules.pms.model.dto.RelationAttrInfoDTO;
import com.ym.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.ym.mall.modules.pms.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {

    @Autowired
    private PmsProductAttributeMapper  pmsProductAttributeMapper;

    @Autowired
    private PmsProductAttributeCategoryService  pmsProductAttributeCategoryService;

    @Override
    public Page list(Long cid, Integer type, Integer pageNum, Integer pageSize) {
        Page<PmsProductAttribute> pmsProductAttributePage = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsProductAttribute::getProductAttributeCategoryId, cid)
                             .eq(PmsProductAttribute::getType, type).orderByAsc(PmsProductAttribute::getSort);
        return this.page(pmsProductAttributePage, queryWrapper);
    }

    @Override
    public boolean create(PmsProductAttribute pmsProductAttribute) {
        boolean save = this.save(pmsProductAttribute);
        UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
        if (save) {
            if (pmsProductAttribute.getType() == 0) {
                updateWrapper.setSql("attribute_count = attribute_count + 1");
            } else if (pmsProductAttribute.getType() == 1) {
                updateWrapper.setSql("param_count = param_count + 1");
            }
            updateWrapper.lambda().eq(PmsProductAttributeCategory::getId, pmsProductAttribute.getProductAttributeCategoryId());
            pmsProductAttributeCategoryService.update(updateWrapper);
        }
        return save;
    }

    @Override
    @Transactional
    public boolean delete(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        PmsProductAttribute productAttribute = null;
        for (Long id : ids) {
            productAttribute = this.getById(id);
            if (productAttribute != null) {
                break;
            }
        }
        int count = pmsProductAttributeMapper.deleteBatchIds(ids);

        if (count > 0 && productAttribute != null) {
            UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
            if (productAttribute.getType() == 0) {
                updateWrapper.setSql("attribute_count = attribute_count - " + count);
            } else if (productAttribute.getType() == 1) {
                updateWrapper.setSql("param_count = param_count - " + count);
            }
            updateWrapper.lambda().eq(PmsProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());
            pmsProductAttributeCategoryService.update(updateWrapper);
        }
        return count > 0;
    }

    @Override
    public List<RelationAttrInfoDTO> getRelationAttrInfoByCid(Long cid) {
        return this.pmsProductAttributeMapper.getRelationAttrInfoByCid(cid);
    }
}
