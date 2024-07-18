package com.ym.mall.modules.ums.dto;

import com.ym.mall.modules.pms.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSaveParamsDTO extends PmsProduct {
    private List<PmsMemberPrice> memberPriceList;
    private List<PmsProductFullReduction> productFullReductionList;
    private List<PmsProductLadder> productLadderList;
    private List<PmsProductAttributeValue> productAttributeValueList;

    @Valid
    @Size(min = 1, message = "SKU list cannot be empty")
    private List<PmsSkuStock> skuStockList;
}
