package com.ym.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.ym.mall.modules.pms.model.PmsProductAttributeCategory;
import com.ym.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.ym.mall.modules.pms.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {

    @Autowired
    private PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    @Override
    public Page list(Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        return this.page(page);
    }

    @Override
    public boolean add(PmsProductAttributeCategory productAttributeCategory) {
        productAttributeCategory.setAttributeCount(0);
        productAttributeCategory.setParamCount(0);
        return this.save(productAttributeCategory);
    }

    @Override
    public List<ProductAttributeCateDTO> getListWithAttr() {
        return pmsProductAttributeCategoryMapper.getListWithAttr();
    }
}
