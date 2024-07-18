package com.ym.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.mall.modules.pms.mapper.PmsBrandMapper;
import com.ym.mall.modules.pms.model.PmsBrand;
import com.ym.mall.modules.pms.service.PmsBrandService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Override
    public Page list(String keyword, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(keyword)) {
            wrapper.lambda().like(PmsBrand::getName, keyword);
        }
        wrapper.lambda().orderByAsc(PmsBrand::getSort);
        return this.page(page, wrapper);
    }
}
