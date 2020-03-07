package com.shiaj.hr.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.shiaj.hr.entity.Salary;
import com.shiaj.hr.pojo.ao.SalaryAo;

import java.util.List;
import java.util.Map;

public interface ISalaryService extends IService<Salary> {

    /**
     * 获取所有薪资条信息
     */
    List<SalaryAo> getAll();

    /**
     * 根据用户id获取基本薪资信息
     * @param uid 用户id
     * @return 基本薪资信息
     */
    Salary getSalaryByUid(Long uid);

    /**
     * 获取所有薪资条信息
     */
    Page<SalaryAo> getSalaryAll(Map<String, String> params);

    /**
     * 根据搜索条件查找该员工的所有年月薪资信息
     * @param params 搜索条件
     * @return 该员工的薪资信息
     */
    // List<SalaryAo> searchSalary(Map<String, String> params);

    /**
     * 删除该员工、该月的薪资信息
     * @param id 薪资id
     */
    void deleteSalary(Long id);

    /**
     * 更新薪资信息
     * @param params 薪资信息
     */
    void updateSalary(Map<String, Object> params);

    /**
     * 新增一条薪资信息
     * @param params 薪资信息
     */
    boolean addSalary(Map<String, Object> params);

    /**
     * 验证一条薪资信息的唯一性
     * @param params 员工工号,年月
     * @return true：存在重复 false：不存在
     */
    boolean checkSalary(Map<String, Object> params);

    /**
     * 根据姓名或工号获取员工薪资
     * @param params 员工姓名或工号或时间
     * @return 该员工所有薪资单
     */
    Page<SalaryAo> getSalaryByNameOrJobCode(Map<String, String> params);

    /**
     * 根据工号获取该员工薪资
     * @param jobCode 员工工号
     * @return 该员工所有薪资单
     */
    List<Salary> getSalaryByJobCode(String jobCode);

    Page<SalaryAo> getSalaryByTinme(Map<String, String> params);
}
