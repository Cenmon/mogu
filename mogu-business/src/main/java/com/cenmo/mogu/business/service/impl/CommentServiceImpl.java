package com.cenmo.mogu.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.business.service.CommentService;
import com.cenmo.mogu.mapper.CommentMapper;
import com.cenmo.mogu.pojo.Comment;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
