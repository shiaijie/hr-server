package com.shiaj.hr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.shiaj.hr.entity.User;
import com.shiaj.hr.pojo.ao.UserAo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @since 2019-12-01
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param jobCode 工号（账号）
     * @param password 密码
     * @return 用户信息
     * @throws Exception 异常
     */
    User login(String jobCode, String password) throws Exception;

    /**
     * 注册
     * @param params 注册所需信息
     */
    boolean register(Map<String,Object> params);

    /**
     * 验证工号唯一性
     * @param params 工号，用户id号
     */
    boolean checkJobCode(Map<String, String> params);

    /**
     * 验证手机号唯一性
     * @param params 手机号，用户id号
     * @return
     */
    boolean checkPhone(Map<String, String> params);

    /**
     * 验证身份证号唯一性
     * @param params 身份证号，用户id号
     * @return
     */
    boolean checkIDnumber(Map<String, String> params);

    /**
     * 添加人员
     * @param params 人员信息
     */
    void addUser(Map<String, Object> params);

    /**
     * 更新人员信息
     */
    void updateUserInfo(Map<String, Object> params);

    /**
     * 获取所有员工信息(不分页)
     */
    List<User> getAllUserInfo();

    /**
     * 获取员工信息（分页）
     */
    Page<UserAo> getAllUserInfoPage(Map<String, String> params);

    /**
     * 获取所有员工姓名和工号
     */
    List<User> getUserNameJobCode();

    /**
     * 删除用户
     */
    void deleteUser(String staffId);

    /**
     * 根据工号搜索员工信息
     * @param jobCode 工号
     * @return 该员工信息
     */
    Map<String, Object> getUserByJobCode(String jobCode);

    /**
     * 根据输入栏搜索员工
     * @param params 姓名或工号
     * @return 用户列表
     */
    List<UserAo> searchUserByJobCodeOrName(Map<String, Object> params);

    /**
     * 修改密码
     * @param params 员工id和新密码
     */
    void updatePassword(Map<String, String> params);
}
