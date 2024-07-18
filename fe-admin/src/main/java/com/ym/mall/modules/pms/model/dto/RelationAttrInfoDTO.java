package com.ym.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "product category and attribute relation filter", description = "use for filtering")
public class RelationAttrInfoDTO {
    private Long attributeId;
    private Long attributeCategoryId;
}
