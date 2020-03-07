package com.shiaj.hr.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shiaj.hr.entity.Salary;
import com.shiaj.hr.constants.SystemConstants;
import com.shiaj.hr.mapper.SalaryMapper;
import com.shiaj.hr.pojo.ao.SalaryAo;
import com.shiaj.hr.service.ISalaryService;
import com.shiaj.hr.util.SortUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 薪资信息管理表 服务实现类
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {
    @Resource
    private SalaryMapper mapper;

    private UserServiceImpl userService;

    @Override
    public List<SalaryAo> getAll() {
        return this.mapper.getAll();
    }

    @Override
    public Salary getSalaryByUid(Long uid){
        List<Salary> salaryList = new Salary().selectList(new EntityWrapper<Salary>().eq("uid", uid).orderBy("yearMonth",false));
        if (salaryList.size() == 0){
            return null;
        }
        else{
            return salaryList.get(0);
        }

    }

//    @Override
//    public Map<SalaryAo> searchSalary(Map<String, Object> params) {
//        return this.mapper.searchSalary(params);
//    }

    @Override
    public void deleteSalary(Long id) {
        this.mapper.deleteById(id);
    }

    @Override
    public void updateSalary(Map<String, Object> params) {
        Salary salary = this.mapper.selectById(params.get("id").toString());
        salary.setBasicSalary(Float.parseFloat(params.get("basicSalary").toString()));
        salary.setSubsidy(Float.parseFloat(params.get("subsidy").toString()));
        salary.setSocialInsurance(Float.parseFloat(params.get("socialInsurance").toString()));
        salary.setAccumulationFund(Float.parseFloat(params.get("accumulationFund").toString()));
        salary.setOtherAdd(Float.parseFloat(params.get("otherAdd").toString()));
        salary.setOtherMinus(Float.parseFloat(params.get("otherMinus").toString()));
        salary.setTotalWage(Float.parseFloat(params.get("totalWage").toString()));
        salary.setYearMonth(params.get("yearMonth").toString());
        salary.setJobCode(params.get("jobCode").toString());
        salary.updateAllColumnById();
    }

    @Override
    public boolean addSalary(Map<String, Object> params) {
        // 查询该salary的工号是否在user表内存在，不存在的话不能加
        String jobCode = params.get("jobCode").toString();
        String yearMonth = params.get("yearMonth").toString();
        // 检索工资表内是否已有该员工的该日期的工资单
        Salary record = new Salary().selectOne(new EntityWrapper<Salary>().eq("jobCode", jobCode).eq("yearMonth", yearMonth));
        if (record == null){
            Salary salary = new Salary();
            salary.setBasicSalary(Float.parseFloat(params.get("basicSalary").toString()));
            salary.setSubsidy(Float.parseFloat(params.get("subsidy").toString()));
            salary.setSocialInsurance(Float.parseFloat(params.get("socialInsurance").toString()));
            salary.setAccumulationFund(Float.parseFloat(params.get("accumulationFund").toString()));
            salary.setOtherAdd(Float.parseFloat(params.get("otherAdd").toString()));
            salary.setOtherMinus(Float.parseFloat(params.get("otherMinus").toString()));
            salary.setTotalWage(Float.parseFloat(params.get("totalWage").toString()));
            salary.setYearMonth(params.get("yearMonth").toString());
            salary.setUid(Long.parseLong(params.get("uid").toString()));
            salary.setJobCode(params.get("jobCode").toString());
            this.mapper.insert(salary);
            return true;
        }
        else{
            System.out.println("不能添加");
            return false;
        }
    }

    @Override
    public boolean checkSalary(Map<String, Object> params) {
        String jobCode = (String) params.get("jobCode");
        Date yearMonth = (Date) params.get("yearMonth");
        // 是否已存在相同年月的该员工工资单
        //true：存在重复 false：不存在
        List<Salary> salaryList = new Salary().selectList(new EntityWrapper<Salary>().eq("jobCode", jobCode).eq("date", yearMonth));
        if (CollectionUtils.isEmpty(salaryList)) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public Page<SalaryAo> getSalaryByNameOrJobCode(Map<String, String> params){
        // 排序
        SortUtil.genOrderBy(params);

        //分页
        Page<SalaryAo> page = new Page<>();
        page.setCurrent(Integer.parseInt(params.get(SystemConstants.CURRENT)));
        page.setSize(Integer.parseInt(params.get(SystemConstants.PAGE_SIZE)));

        page.setRecords(this.mapper.getSalaryByNameOrJobCode(page, params));
        return page;
    }

    @Override
    public Page<SalaryAo> getSalaryAll(Map<String, String> params) {
        // 排序
        SortUtil.genOrderBy(params);

        //分页
        Page<SalaryAo> page = new Page<>();
        page.setCurrent(Integer.parseInt(params.get(SystemConstants.CURRENT)));
        page.setSize(Integer.parseInt(params.get(SystemConstants.PAGE_SIZE)));
        page.setRecords(this.mapper.getSalaryAll(page, params));
        return page;
    }

    @Override
    public List<Salary> getSalaryByJobCode(String jobCode){
        return new Salary().selectList(new EntityWrapper<Salary>().eq("jobCode", jobCode).orderBy("yearMonth",false));
    }

    @Override
    public Page<SalaryAo> getSalaryByTinme(Map<String, String> params){
        // 排序
        SortUtil.genOrderBy(params);

        //分页
        Page<SalaryAo> page = new Page<>();
        page.setCurrent(Integer.parseInt(params.get(SystemConstants.CURRENT)));
        page.setSize(Integer.parseInt(params.get(SystemConstants.PAGE_SIZE)));

        page.setRecords(this.mapper.getSalaryByTinme(page, params));
        return page;
    }

}
