package com.ym.mall.modules.pms.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ym.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductCategory dto", description="product category")
public class PmsProductCategoryDTO extends PmsProductCategory {
    private List<Long> productAttributeIdList;
}
