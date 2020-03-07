package com.shiaj.hr.pojo.ao;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
public class SalaryAo extends Model<SalaryAo> {

    Long id;

    /** 员工姓名 */
    private String name;

    /** 员工工号 */
    private String jobCode;

    /** 基本工资 */
    private Float basicSalary;

    /** 津补贴 */
    private Float subsidy;

    /** 社保 */
    private Float socialInsurance;

    /** 公积金 */
    private Float accumulationFund;

    /** 其他附加 */
    private Float otherAdd;

    /** 其他扣除 */
    private Float otherMinus;

    /** 总工资 */
    private Float totalWage;

    /** 工资单年月 */
    private String yearMonth;

    /** 是否归档：0否，1是 */
    private Integer isFile;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getJobCode() {
        return jobCode;
    }
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public Float getBasicSalary() {
        return basicSalary;
    }
    public void setBasicSalary(Float basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Float getSubsidy() {
        return subsidy;
    }
    public void setSubsidy(Float subsidy) {
        this.subsidy = subsidy;
    }

    public Float getSocialInsurance() {
        return socialInsurance;
    }
    public void setSocialInsurance(Float socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public Float getAccumulationFund() {
        return accumulationFund;
    }
    public void setAccumulationFund(Float accumulationFund) {
        this.accumulationFund = accumulationFund;
    }

    public Float getOtherAdd() {
        return otherAdd;
    }
    public void setOtherAdd(Float otherAdd) {
        this.otherAdd = otherAdd;
    }

    public Float getOtherMinus() {
        return otherMinus;
    }
    public void setOtherMinus(Float otherMinus) {
        this.otherMinus = otherMinus;
    }

    public Float getTotalWage() {
        return totalWage;
    }
    public void setTotalWage(Float totalWage) {
        this.totalWage = totalWage;
    }

    public String getYearMonth() {
        return yearMonth;
    }
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Integer getIsFile() {
        return isFile;
    }
    public void setIsFile(Integer isFile) {
        this.isFile = isFile;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
