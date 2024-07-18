package com.ym.mall.modules.pms.model.dto;

import com.ym.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@ApiModel(value = "product cate relation dto")
public class ProductCateChildrenDTO {

    private Long id;

    private String name;

    private List<PmsProductCategory> children;
}
