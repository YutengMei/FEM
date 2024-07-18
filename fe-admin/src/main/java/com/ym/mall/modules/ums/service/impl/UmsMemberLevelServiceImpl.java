package com.ym.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.mall.modules.ums.mapper.UmsMemberLevelMapper;
import com.ym.mall.modules.ums.model.UmsMemberLevel;
import com.ym.mall.modules.ums.service.UmsMemberLevelService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-16
 */
@Service
public class UmsMemberLevelServiceImpl extends ServiceImpl<UmsMemberLevelMapper, UmsMemberLevel> implements UmsMemberLevelService {

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        QueryWrapper<UmsMemberLevel> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMemberLevel::getDefaultStatus, defaultStatus);
        return this.list(wrapper);
    }
}
