
package com.shiaj.hr.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 岗位信息管理表
 */

@Data
// @EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("post")
public class Post extends Model<Post> {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 岗位名称
     */
    private String name;
    /**
     * 部门ID
     */
    private Long departId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标志
     */
    @TableLogic
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

    // @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name=" + name +
                ", departId=" + departId +
                ", remark=" + remark +
                "}";
    }
}
