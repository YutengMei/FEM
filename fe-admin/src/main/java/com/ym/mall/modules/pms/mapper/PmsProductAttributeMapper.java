package com.ym.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ym.mall.modules.pms.model.PmsProductAttribute;
import com.ym.mall.modules.pms.model.dto.RelationAttrInfoDTO;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {

    List<RelationAttrInfoDTO> getRelationAttrInfoByCid(Long cid);
}
