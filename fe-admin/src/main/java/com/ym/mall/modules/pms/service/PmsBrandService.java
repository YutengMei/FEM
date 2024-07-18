package com.ym.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.mall.modules.pms.model.PmsBrand;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
public interface PmsBrandService extends IService<PmsBrand> {

    Page list(String keyword, Integer pageNum, Integer pageSize);
}
