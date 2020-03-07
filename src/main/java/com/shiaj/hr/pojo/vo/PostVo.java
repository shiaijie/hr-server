package com.shiaj.hr.pojo.vo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostVo extends Model<PostVo>{
    // 岗位id
    private Long id;
    // 岗位名称
    private String name;
    // 部门ID
    private Long departId;
    // 部门名称
    private String departName;
    // 备注
    private String remark;
    // 删除标志
    private Integer deleteFlag;

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

    public Long getDepartId() {
        return departId;
    }
    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }
    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
