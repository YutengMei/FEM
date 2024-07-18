package com.ym.mall.modules.ums.dto;

import com.ym.mall.modules.pms.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductUpdateInitDTO extends ProductSaveParamsDTO {
    private Long cateParentId;
}
