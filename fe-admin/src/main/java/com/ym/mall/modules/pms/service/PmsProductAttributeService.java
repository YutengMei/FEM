package com.ym.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.mall.modules.pms.model.PmsProductAttribute;
import com.ym.mall.modules.pms.model.dto.RelationAttrInfoDTO;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {

    Page list(Long cid, Integer type, Integer pageNum, Integer pageSize);

    boolean create(PmsProductAttribute pmsProductAttribute);

    boolean delete(List<Long> ids);

    List<RelationAttrInfoDTO> getRelationAttrInfoByCid(Long cid);
}
