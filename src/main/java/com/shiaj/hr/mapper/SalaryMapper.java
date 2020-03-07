package com.shiaj.hr.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.shiaj.hr.entity.Salary;
import com.shiaj.hr.pojo.ao.SalaryAo;

import java.util.List;
import java.util.Map;

public interface SalaryMapper extends BaseMapper<Salary> {
    /**
     * 获取所有薪资信息
     * @return 薪资信息列表
     */
    List<SalaryAo> getAll();

    /**
     * 根据搜索条件搜索薪资信息
     * @param params 搜索条件
     * @return 薪资信息列表
     */
    // List<SalaryAo> params searchSalary(Map<String, String> params);

    /**
     * 根据姓名或工号搜索薪资信息
     * @param page
     * @param params 姓名或工号
     * @return 薪资信息列表
     */
    List<SalaryAo> getSalaryByNameOrJobCode(Pagination page, Map<String, String> params);

    List<SalaryAo> getSalaryAll(Pagination page, Map<String, String> params);

    List<SalaryAo> getSalaryByTinme(Pagination page, Map<String, String> params);
}
