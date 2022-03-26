package com.dreamvalley.demo.axon.core.dto.commodity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangpan
 */
@Data
public class CommodityDto {
    private Long id;
    private String reversion;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String name;
    private Long price;
    private Integer stock;
    private String status;
}
