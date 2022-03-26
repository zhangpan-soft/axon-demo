package com.dreamvalley.demo.axon.core.command.commodity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品查询命令
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityFindByIdCommand {
    private Long id;
}
