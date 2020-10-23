package com.cenmo.mogu.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Adv;
import com.cenmo.mogu.mapper.AdvMapper;
import com.cenmo.mogu.manager.service.AdvService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-20
 */
@Service
public class AdvServiceImpl extends ServiceImpl<AdvMapper, Adv> implements AdvService {

    @Override
    public EUDataGridResult getAdvList(Long categoryId, int page, int rows) {

        // 分页查询，设置page和rows，最后为page-1，rows？？
        Page<Adv> advPage = new Page<>(page,rows);
        QueryWrapper<Adv> queryWrapper = new QueryWrapper<Adv>().eq("category_id", categoryId);

        IPage<Adv> iPage = baseMapper.selectPage(advPage, queryWrapper);

//        List<Adv> records = iPage.getRecords(); // 所有被分页后的记录
        System.out.println("ipage.getRecords():"+ iPage.getRecords());
//        long size = iPage.getSize();// 总页数
        System.out.println("ipage.getSize():"+ iPage.getSize());
//        long current = iPage.getCurrent(); // 当前页
        System.out.println("ipage.getCurrent():"+ iPage.getCurrent());
//        long pages = iPage.getPages();// 当前分页总页数，即每个分页的记录数
        System.out.println("ipage.getPages():"+ iPage.getPages());


        EUDataGridResult result = new EUDataGridResult();
        result.setRows(iPage.getRecords());
        result.setTotal(iPage.getTotal());

        return result;
    }

    @Override
    public MoguResult insertAdv(Adv adv) {

        baseMapper.insert(adv);
        return MoguResult.ok();
    }

    @Override
    public MoguResult deleteAdv(long id) {
        baseMapper.deleteById(id);
        return MoguResult.ok();
    }

    @Override
    public MoguResult editAdv(Adv adv) {
        baseMapper.updateById(adv);
        return MoguResult.ok();
    }
}
