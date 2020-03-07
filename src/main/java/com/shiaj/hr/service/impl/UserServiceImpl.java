package com.shiaj.hr.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shiaj.hr.entity.Post;
import com.shiaj.hr.entity.Salary;
import com.shiaj.hr.constants.SystemConstants;
import com.shiaj.hr.entity.User;
import com.shiaj.hr.mapper.UserMapper;
import com.shiaj.hr.util.SortUtil;
import com.shiaj.hr.service.IUserService;
import org.springframework.stereotype.Service;
import com.shiaj.hr.pojo.ao.UserAo;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2019-12-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper mapper;

    @Resource
    private DepartmentServiceImpl departmentServiceImpl;

    @Resource
    private PostServiceImpl postServiceImpl;

    @Resource
    private SalaryServiceImpl salaryServiceImpl;

    @Override
    public User login(String jobCode, String password) throws Exception {
        /*String passwordDes = PasswordDecrypt.desEncrypt(password);
        if (passwordDes == null) {
            throw new SysRuntimeException(message.getMessage("W004", this.message.getMessage("L002")));
        }*/
        // 加密
        // String md5Pw = DigestUtils.md5DigestAsHex(passwordDes.getBytes("utf-8"));

        User userEntity;
        User user = new User();
        // 检索工号
        userEntity = user.selectOne(new EntityWrapper<User>().eq("jobCode", jobCode).eq("password", password));
        // 没有符合条件的账号信息（账号密码错误）
        if (userEntity == null) {
            return null;
        }
        return userEntity;


        /*// 工号不存在时按邮箱检索
        if (userEntity == null) {
            // 考虑有邮箱相同账号被禁用情况
            List<SysUser> userList = user.selectList(
                    new EntityWrapper<SysUser>()
                            .eq("mail", jobNum.toLowerCase())
                            .eq("password", md5Pw)
            );
*/
            // 没有符合条件的账号信息（账号密码错误）
            /*if (userEntity == null) {
                throw new SysRuntimeException(message.getMessage("W017"));
                return null;
            }

            // 有账号的情况下过滤已禁用账号
            List<SysUser> validUserList = userList.stream().filter(su -> su.getDeleteFlag() == 0).collect(Collectors.toList());*/

            /*// 账号已被禁用时
            if (CollectionUtils.isEmpty(validUserList)) {
                throw new SysRuntimeException(message.getMessage("W003"));
            }*/

            /*userEntity = validUserList.get(0);*/


        // return setLoginInfo(userEntity);

    }

/*    private User setLoginInfo(User userEntity) {
        // 校验账号是否有效
        validateExpiration(userEntity);

        LoginInfo info = new LoginInfo();
        info.setUid(userEntity.getId());
        info.setToken(tokenManager.genToken(userEntity));
        info.setName(userEntity.getName());
        info.setMail(userEntity.getMail());
        info.setJobCode(userEntity.getJobCode());

        // 如果有对应职员信息的话设置StaffId
        StaffBasic staffBasic = new StaffBasic();
        staffBasic = staffBasic.selectOne(new EntityWrapper<StaffBasic>().eq("jobCode", userEntity.getJobCode()));
        if (staffBasic != null) {
            info.setStaffId(staffBasic.getId());
        }

        // 请求中不带L-Authorization的话，重新生成
        HttpServletRequest request = HttpUtil.getRequest();
        String longToken = request.getHeader(LONG_TOKEN_NAME);

        longToken = tokenManager.saveLongToken(longToken, userEntity);
        info.setRefreshToken(longToken);

        // 获取角色列表
        Set<AuthorityVo> authorityInfosList = new LinkedHashSet<>();
        Set<String> roleIdList = new HashSet<>();
        List<Integer> permissonValueList = new ArrayList<>();
        if (userEntity.getId() != null) {
            Map<String, Long> paramsRole = new HashMap<>(2);
            paramsRole.put("userId", userEntity.getId());
            List<RoleInfo> roleResult = roleMapper.selectRole(paramsRole);
            for (RoleInfo role : roleResult) {
                roleIdList.add(String.valueOf(role.getRoleId()));
                permissonValueList.add(role.getPermissionsValue());
                List<AuthorityVo> authorityInfos = this.roleFunctionMapper.queryAuthorityByRoleId(role.getRoleId());
                authorityInfosList.addAll(authorityInfos);
            }
        }
        info.setRoleList(roleIdList);
        // 设置权限最大值
        if (!CollectionUtils.isEmpty(permissonValueList)) {
            info.setPermissionsValue(Collections.max(permissonValueList));
        }

        String firstUrl = null;
        if (authorityInfosList.size() > 0) {
            for (AuthorityVo au : authorityInfosList) {
                if (au.getMenuFlag() != null && au.getMenuFlag().equals(ResourceTypeEnum.MENU.getValue())) {
                    if (StringUtils.isNotBlank(au.getLink())) {
                        firstUrl = au.getLink();
                        break;
                    }
                }
            }
        }
        if (firstUrl == null) {
            throw new SysRuntimeException(message.getMessage("W005"));
        }

        // 设置最后登录时间、IP
        userEntity.setLastLogin(new Date());
        userEntity.setLoginIp(AccessDataUtil.getIpAddress(request));
        this.updateAllColumnById(userEntity);

        // 写入登录日志
        BusinessLog log = new BusinessLog();
        log.setModule(this.message.getMessage("L200"));
        log.setOp(AutoLog.Op.LOGIN.getValue());
        log.setType(AutoLog.Type.LOGIN_OUT.getValue());
        log.setIp(AccessDataUtil.getIpAddress(request));
        log.setUrl(request.getRequestURI());
        log.setUid(userEntity.getId());
        log.setGmtCreate(new Date());
        logService.addLog(log);

        info.setFirstUrl(firstUrl);
        return info;
    }*/


    @Override
    public boolean register(Map<String, Object> params) {
        String jobCode = params.get("jobCode").toString();
        String phone = params.get("phone").toString();
        String password = params.get("password").toString();
        String name = params.get("name").toString();
        // 若工号和手机号不重复，注册成功
         User user = new User();
         user.setName(name);
         user.setJobCode(jobCode);
         user.setPassword(password);
         user.insert();
         return true;
    }

    @Override
    public boolean checkJobCode(Map<String, String> params) {
        String jobCode = params.get("jobCode");
        User user = new User().selectOne(new EntityWrapper<User>().eq("jobCode", jobCode));
        if (user == null) {
            return true;
        }
        if (jobCode == null) {
            return true;
        }
        if (params.get("id") != null){
            String id = params.get("id");
            return id.equals(user.getId().toString());
        }
        return false;
    }

    @Override
    public boolean checkPhone(Map<String, String> params) {
        String phone = params.get("phone");

        User user = new User().selectOne(new EntityWrapper<User>().eq("phone", phone));
        if (user == null) {
            return true;
        }
        if (phone == null) {
            return true;
        }
        if (params.get("id") != null){
            String id = params.get("id");
            return id.equals(user.getId().toString());
        }
        return false;
    }

    @Override
    public boolean checkIDnumber(Map<String, String> params) {
        String IDnumber = params.get("IDnumber");

        User user = new User().selectOne(new EntityWrapper().eq("IDnumber", IDnumber));
        if (user == null) {
            return true;
        }
        if (IDnumber == null) {
            return true;
        }
        if (params.get("id") != null){
            String id = params.get("id");
            return id.equals(user.getId().toString());
        }
        return false;
    }

    @Override
    public void addUser(Map<String, Object> params) {

        User user = new User();
        user.setName(params.get("name").toString());
        user.setJobCode(params.get("jobCode").toString());
        user.setPhone(params.get("phone").toString());
        // 初始密码设为111111
        user.setPassword("111111");
        user.setSex(Integer.parseInt(params.get("sex").toString()));
        user.setAge(Integer.parseInt(params.get("age").toString()));
        user.setIDnumber(params.get("IDnumber").toString());
        //
        user.setDepartId(Integer.parseInt(params.get("departId").toString()));
        user.setPostId(Integer.parseInt(params.get("postId").toString()));
        if (params.get("email") != null) {
            user.setEmail(params.get("email").toString());
        }
        user.setPolitical(params.get("political").toString());
        user.setNation(params.get("nation").toString());
        user.setMarriageStatus(params.get("marriageStatus").toString());
        user.setAddress(params.get("address").toString());
        user.setInductionDay(new Date());
        user.setIsLeave(0);
        user.setLeavingDay(null);user.setBirthday(params.get("birthday").toString());
        user.setIsAdmin(Integer.parseInt(params.get("isAdmin").toString()));
        user.setDeleteFlag(0);
        // user.updateAllColumnById();
        /*if (params.get("IDnumber") != null && StringUtils.isNotBlank(params.get("deptId").toString())){
            user.setIDnumber(params.get("IDnumber").toString());
        }*/
        user.insert();

        // 薪资的添加
        Salary newSalary = new Salary();
        newSalary.setUid(Long.parseLong(params.get("id").toString()));
        newSalary.setJobCode(params.get("jobCode").toString());
        newSalary.setBasicSalary(Float.parseFloat(params.get("basicWage").toString()));
        newSalary.setSubsidy(Float.parseFloat(params.get("subsidy").toString()));
        newSalary.setSocialInsurance(Float.parseFloat(params.get("socialInsurance").toString()));
        newSalary.setAccumulationFund(Float.parseFloat(params.get("accumulationFund").toString()));
        newSalary.setOtherAdd(Float.parseFloat("0"));
        newSalary.setOtherMinus(Float.parseFloat("0"));
        Float total = Float.parseFloat(params.get("basicWage").toString())
                + Float.parseFloat(params.get("subsidy").toString())
                + Float.parseFloat(params.get("socialInsurance").toString())
                + Float.parseFloat(params.get("accumulationFund").toString());
        newSalary.setTotalWage(total);
        String dateStr = (new SimpleDateFormat("yyyy-MM")).format(new Date());
        newSalary.setYearMonth(dateStr);
        newSalary.setIsFile(0);
        newSalary.insert();
    }

    @Override
    public void updateUserInfo(Map<String, Object> params) {
        // 更新用户信息
        User user = new User().selectOne(new EntityWrapper<User>()
                .eq("id", params.get("id")).eq("deleteFlag", 0));
        if (user != null){
            // Department department = this.departmentServiceImpl.
            user.setName(params.get("name").toString());
            user.setJobCode(params.get("jobCode").toString());
            user.setAge(Integer.parseInt(params.get("age").toString()));
            user.setSex(Integer.parseInt(params.get("sex").toString()));
            user.setDepartId(Integer.parseInt(params.get("departId").toString()));
            user.setPostId(Integer.parseInt(params.get("postId").toString()));
            user.setPolitical(params.get("political").toString());
            user.setNation(params.get("nation").toString());
            user.setMarriageStatus(params.get("marriageStatus").toString());
            user.setIDnumber(params.get("IDnumber").toString());
            user.setAddress(params.get("address").toString());
            user.setPhone(params.get("phone").toString());
            user.setIsAdmin(Integer.parseInt(params.get("isAdmin").toString()));
            user.setBirthday(params.get("birthday").toString());
            if (params.get("email") != null) {
                user.setEmail(params.get("email").toString());
            }
            user.updateAllColumnById();
            // 如果存在本月薪资单，则进行薪资单的更新
            // 如果不存在本月工资单，则进行添加
            Salary salary = this.salaryServiceImpl.getSalaryByUid(Long.parseLong(user.getId().toString()));
            String dateStr = (new SimpleDateFormat("yyyy-MM")).format(new Date());
            if (salary != null && salary.getYearMonth().equals(dateStr)) {
                salary.setJobCode(params.get("jobCode").toString());
                salary.setBasicSalary(Float.parseFloat(params.get("basicWage").toString()));
                salary.setSubsidy(Float.parseFloat(params.get("subsidy").toString()));
                salary.setSocialInsurance(Float.parseFloat(params.get("socialInsurance").toString()));
                salary.setAccumulationFund(Float.parseFloat(params.get("accumulationFund").toString()));
                Float total = Float.parseFloat(params.get("basicWage").toString())
                        + Float.parseFloat(params.get("subsidy").toString())
                        + Float.parseFloat(params.get("socialInsurance").toString())
                        + Float.parseFloat(params.get("accumulationFund").toString())
                        + salary.getOtherAdd()
                        + salary.getOtherMinus();
                salary.setTotalWage(total);
                salary.updateAllColumnById();
            }
            else{
                Salary newSalary = new Salary();
                newSalary.setUid(Long.parseLong(params.get("id").toString()));
                newSalary.setJobCode(params.get("jobCode").toString());
                newSalary.setBasicSalary(Float.parseFloat(params.get("basicWage").toString()));
                newSalary.setSubsidy(Float.parseFloat(params.get("subsidy").toString()));
                newSalary.setSocialInsurance(Float.parseFloat(params.get("socialInsurance").toString()));
                newSalary.setAccumulationFund(Float.parseFloat(params.get("accumulationFund").toString()));
                newSalary.setOtherAdd(Float.parseFloat("0"));
                newSalary.setOtherMinus(Float.parseFloat("0"));
                Float total = Float.parseFloat(params.get("basicWage").toString())
                        + Float.parseFloat(params.get("subsidy").toString())
                        + Float.parseFloat(params.get("socialInsurance").toString())
                        + Float.parseFloat(params.get("accumulationFund").toString());
                newSalary.setTotalWage(total);
                newSalary.setYearMonth(dateStr);
                newSalary.setIsFile(0);
                newSalary.insert();
            }
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> getAllUserInfo() {
        // 要写sql语句，因为有departId啥的，要左连接三个表 depart、post、duty。
        Wrapper<User> wrapper = new EntityWrapper<>(new User(), "id, name, jobCode, age, sex, departId")
                .eq("deleteFlag", "0").orderBy(true, "id", false);
        return this.mapper.selectList(wrapper);
        // this.mapper.getAllUserInfoPage();
    }

    @Override
    public Page<UserAo> getAllUserInfoPage(Map<String, String> params) {
        // 排序
        SortUtil.genOrderBy(params);
        //分页
        Page<UserAo> page = new Page<>();
        page.setCurrent(Integer.parseInt(params.get(SystemConstants.CURRENT)));
        page.setSize(Integer.parseInt(params.get(SystemConstants.PAGE_SIZE)));
        List<UserAo> records = this.mapper.getAllUserInfoPage(page, params);
        page.setRecords(records);
        page.setTotal(records.size());
        return page;
    }

    @Override
    public List<User> getUserNameJobCode(){
        return this.mapper.getUserNameJobCode();
    }

    @Override
    public void deleteUser(String staffId){
        this.mapper.deleteById(Integer.parseInt(staffId));
    }

    @Override
    public Map<String, Object> getUserByJobCode(String jobCode){
        Map<String, Object> userInfo = new HashMap<>(16);
        User user = new User().selectOne(new EntityWrapper<User>().eq("jobCode", jobCode).eq("deleteFlag", 0));
        if (user.getPostId() != null){
            Post post = this.postServiceImpl.getPostById(user.getPostId().toString());
            userInfo.put("postName", post.getName());
            userInfo.put("postId", post.getId());
        } else{
            userInfo.put("postName", "");
            userInfo.put("postId", "");
        }
        if (user.getDepartId() != null){
            Map<String, Object> department = this.departmentServiceImpl.getNameById(user.getDepartId().toString());
            userInfo.put("departName", department.get("name"));
            userInfo.put("departId", department.get("id"));
        } else{
            userInfo.put("departName", "");
            userInfo.put("departId", "");
        }
        List<Salary> salary = this.salaryServiceImpl.getSalaryByJobCode(jobCode);
        if (salary.size() != 0){
            userInfo.put("basicWage", salary.get(0).getBasicSalary());
            userInfo.put("subsidy", salary.get(0).getSubsidy());
            userInfo.put("socialInsurance", salary.get(0).getSocialInsurance());
            userInfo.put("accumulationFund", salary.get(0).getAccumulationFund());
            userInfo.put("totalWage", salary.get(0).getTotalWage());
        } else{
            userInfo.put("basicWage", 0);
            userInfo.put("subsidy", 0);
            userInfo.put("socialInsurance", 0);
            userInfo.put("accumulationFund", 0);
            userInfo.put("totalWage", 0);
        }
        userInfo.put("id", user.getId());
        userInfo.put("name", user.getName());
        userInfo.put("jobCode", user.getJobCode());
        userInfo.put("age", user.getAge());
        userInfo.put("sex", user.getSex());
        userInfo.put("political", user.getPolitical());
        userInfo.put("nation", user.getNation());
        userInfo.put("marriageStatus", user.getMarriageStatus());
        userInfo.put("IDnumber", user.getIDnumber());
        userInfo.put("address", user.getAddress());
        userInfo.put("phone", user.getPhone());
        userInfo.put("isAdmin", user.getIsAdmin());
        userInfo.put("email", user.getEmail());
        userInfo.put("birthday", user.getBirthday());
        return userInfo;
    }

    @Override
    public List<UserAo> searchUserByJobCodeOrName(Map<String, Object> params){
        return this.mapper.search(params);
    }

    @Override
    public void updatePassword(Map<String, String> params){
        String jobCode = params.get("jobCode");
        User user = new User().selectOne(new EntityWrapper<User>().eq("jobCode", jobCode).eq("deleteFlag", 0));
        user.setPassword(params.get("password"));
        user.updateAllColumnById();
    }
}
