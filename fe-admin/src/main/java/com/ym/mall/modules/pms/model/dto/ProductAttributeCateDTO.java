package com.ym.mall.modules.pms.model.dto;

import com.ym.mall.modules.pms.model.PmsProductAttribute;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@ApiModel(value = "product attribute cate dto")
public class ProductAttributeCateDTO {

    private Long id;

    private String name;

    private List<PmsProductAttribute> productAttributeList;
}
