package com.shiaj.hr.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.shiaj.hr.entity.User;
import org.apache.ibatis.annotations.Mapper;
import com.shiaj.hr.pojo.ao.UserAo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-12-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<UserAo> getAllUserInfoPage(Pagination page, Map<String, String> params);

    List<User> getUserNameJobCode();

    List<UserAo> search(Map<String, Object> params);

    //List<User> getAllUserInfoPage();

    //List<User> getProductByNameOrCode(Pagination page, Map<String, String> params);

    //List<User> getProductByFigureCode(Pagination page, Map<String, String> params);
}
