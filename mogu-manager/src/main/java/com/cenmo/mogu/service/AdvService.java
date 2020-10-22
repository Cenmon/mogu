package com.cenmo.mogu.service;

import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Adv;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 广告表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-20
 */
public interface AdvService extends IService<Adv> {

    EUDataGridResult getAdvList(Long categoryId, int page, int rows);

    MoguResult insertAdv(Adv adv);

    MoguResult deleteAdv(long id);

    MoguResult editAdv(Adv adv);
}
