package com.ym.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.mall.modules.ums.model.UmsMemberLevel;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-16
 */
public interface UmsMemberLevelService extends IService<UmsMemberLevel> {

    List<UmsMemberLevel> list(Integer defaultStatus);
}
