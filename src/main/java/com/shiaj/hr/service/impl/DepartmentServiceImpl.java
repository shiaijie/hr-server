package com.shiaj.hr.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shiaj.hr.entity.Department;
import com.shiaj.hr.pojo.vo.NzTreeNode;
import com.shiaj.hr.mapper.DepartmentMapper;
import com.shiaj.hr.service.IDepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Resource
    private DepartmentMapper mapper;

    @Resource
    private PostServiceImpl postServiceImpl;

    @Override
    public Map<String, Object> getNameById(String departId) {
        Map<String, Object> departmentInfo = this.mapper.getNameById(Long.parseLong(departId));
        return departmentInfo;
    }

    @Override
    public void deleteDepartment(String departId) {
        List<Long> departIds = new ArrayList<>();
        departIds.add(Long.parseLong(departId));
        while(departIds.size() != 0) {
            Department temp = new Department();
            List<Department> departments = new Department().selectList(new EntityWrapper<Department>().in("parentDepart", departIds));
            if (CollectionUtils.isEmpty(departments)) {
                for (int i = 0; i < departIds.size(); i++) {
                    // 删除这个部门id下的所有岗位
                    this.postServiceImpl.deleteByParent(departIds.get(i).toString());
                    // 删除这个部门
                    this.mapper.deleteById(departIds.get(i));
                }
                // 删除完毕
                System.out.println("删除完毕");
                break;
            }
            else {
                for (int i = 0; i < departIds.size(); i++) {
                    // 删除这个部门id下的所有岗位
                    this.postServiceImpl.deleteByParent(departIds.get(i).toString());
                    // 删除这个部门
                    this.mapper.deleteById(departIds.get(i));
                }
                departIds.clear();
                for (int i = 0; i < departments.size(); i++) {
                    departIds.add(departments.get(i).getId());
                }
            }
        }
    }

    @Override
    public void updateDepartment(Map<String, Object> departmentInfo) {
        Department department = this.mapper.selectById(departmentInfo.get("id").toString());
        department.setName(departmentInfo.get("name").toString());
        department.setParentDepart(Long.parseLong(departmentInfo.get("parentDepart").toString()));
        if(departmentInfo.get("remark")!=null) {
            department.setRemark(departmentInfo.get("remark").toString());
        }
        department.updateAllColumnById();
    }

    @Override
    public void addDepartment(Map<String, Object> departmentInfo) {
        Department department = new Department();
        department.setName(departmentInfo.get("name").toString());
        if(departmentInfo.get("remark")!=null){
            department.setRemark(departmentInfo.get("remark").toString());
        }
        department.setParentDepart(Long.parseLong(departmentInfo.get("parentDepart").toString()));
        department.setDeleteFlag(0);
        department.setRoot("0");
        this.mapper.insert(department);
    }

    @Override
    public boolean checkDepartment(String departmentName) {
        //true：存在重复 false：不存在
        List<Department> departList = new Department().selectList(new EntityWrapper<Department>().eq("name", departmentName));
        if (CollectionUtils.isEmpty(departList)) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public List<Department> getAll() {
        return this.mapper.getAll();
    }

    // 获取部门树
    @Override
    public List<NzTreeNode> getDeptTree() {
        return NzTreeNode.buildTree(this.mapper.getDeptTree());
    }
}
