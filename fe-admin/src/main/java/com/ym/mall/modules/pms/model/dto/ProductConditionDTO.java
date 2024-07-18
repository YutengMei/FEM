package com.ym.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *      *     keyword: null,
 *      *     pageNum: 1,
 *      *     pageSize: 5,
 *      *     publishStatus: null,
 *      *     verifyStatus: null,
 *      *     productSn: null,
 *      *     productCategoryId: null,
 *      *     brandId: null
 */
@Data
@EqualsAndHashCode
@ApiModel(value = "product api params")
public class ProductConditionDTO {
    private String keyword;
    private Integer pageNum;
    private Integer pageSize;
    private Integer publishStatus;
    private Integer verifyStatus;
    private String productSn;
    private Long productCategoryId;
    private Long brandId;
}
