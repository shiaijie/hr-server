package com.shiaj.hr.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shiaj.hr.entity.Post;
import com.shiaj.hr.pojo.vo.PostVo;

import java.util.List;

public interface PostMapper extends BaseMapper<Post> {

    /**
     * 根据岗位Id获取岗位信息
     * @param postId 只存一个岗位id
     * @return 岗位详情 岗位id，岗位名称，备注，岗位所属部门
     */
    Post getPostById(Long postId);

    /**
     * 获取所有岗位
     * @return 岗位列表
     */
    List<PostVo> getAll();

    /**
     * 获取所有岗位
     * @return 岗位列表
     */
    List<PostVo> searchPost(String postName);
}
