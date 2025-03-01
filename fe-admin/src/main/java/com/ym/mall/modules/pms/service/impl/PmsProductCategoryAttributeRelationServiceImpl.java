package com.ym.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.mall.modules.pms.mapper.PmsProductCategoryAttributeRelationMapper;
import com.ym.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.ym.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements PmsProductCategoryAttributeRelationService {

}
