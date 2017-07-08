package com.xmg.wms.web.action;

import java.util.List;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.xmg.wms.util.MD5;
import lombok.Getter;
import lombok.Setter;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.wms.domain.Department;
import com.xmg.wms.domain.Employee;
import com.xmg.wms.domain.Role;
import com.xmg.wms.page.PageResult;
import com.xmg.wms.query.EmployeeQueryObject;
import com.xmg.wms.service.IDepartmentService;
import com.xmg.wms.service.IEmployeeService;
import com.xmg.wms.service.IRoleService;
import com.xmg.wms.util.RequiredPermission;


public class EmployeeAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    @Setter
    private IEmployeeService empService;
    @Setter
    private IDepartmentService deptService;
    @Setter
    private IRoleService roleService;
    @Getter
    private Employee e = new Employee();
    @Setter
    private String repassword;//接收页面提交的密码
    @Getter
    private EmployeeQueryObject qo = new EmployeeQueryObject();
    /*//批量删除--保存删除员工id的集合(抽取到父类中)
    @Setter
    @Getter
    private List<Long> ids = new ArrayList<>();*/

    @Override
    @RequiredPermission("员工列表")
    @InputConfig(methodName="input")
    //1,判断在执行此方法前是否有错误信息,如果有就跳转到指定方法/result视图;
        //此时从saveOrUpdate重定向到execute(),由于存在错误,再次重定向到result视图,存在两次重定向,请求中数据丢失
    //2,不可以直接跳转到resultName='input'
    //3,此标签应标记在excute()方法上,因为saveOrUpdate()方法重定向到此方法
    public String execute() throws Exception {
        try {
            PageResult result = empService.pageQuery(qo);
            putContext("result",result);
            List<Department> depts = deptService.list();
            putContext("depts",depts);
            //int i = 1 / 0;//模拟异常
        } catch (Exception e1) {
            addActionError(e1.getMessage());
            e1.printStackTrace();
        }
        return LIST;
    }

    @RequiredPermission("员工删除")
    public String delete() throws Exception {
        try {
            if (e.getId() != null) {
                empService.delete(e.getId());
                putMsg("删除成功");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            addActionError(e1.getMessage());
        }
        return NONE;//异步请求,此处应该改为NONE,否则会返回一个页面
    }

    @RequiredPermission("员工编辑")
    public String input() throws Exception {
        try {
            if (e.getId() != null) {
                e = empService.get(e.getId());
            }
            //将全部部门信息传过去
            List<Department> depts = deptService.list();
            putContext("depts",depts);
            //查询全部角色信息
            List<Role> roles = roleService.list();
            /*//角色去重操作
            Iterator<Role> iterator = roles.iterator();
            while (iterator.hasNext()) {
                Role role1 = iterator.next();
                for (Role role2 : e.getRoles()) {
                    if (role1.getName().equals(role2.getName())) {
                        iterator.remove();
                    }
                }
            }*/
            //int i = 1 / 0;//模拟执行input方法出现异常
            putContext("roles",roles);
        } catch (Exception e1) {
            addActionError(e1.getMessage());
            e1.printStackTrace();
        }
        return INPUT;
    }

    @RequiredPermission("员工保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (e.getId() != null) {
                empService.update(e);
                addActionMessage("修改成功!");
            } else {
                //对密码进行MD5加密
                e.setPassword(MD5.encode(e.getPassword()));
                empService.save(e);
                addActionMessage("保存成功!");
            }
            //int i = 1 / 0;//模拟编辑页面出现异常
        } catch (Exception e1) {
            addActionError(e1.getMessage());
            e1.printStackTrace();
        }
        return SUCCESS;
    }

    @RequiredPermission("员工批量删除")
    public String batchDelete() throws Exception {
        try {
            if (this.ids.size() != 0) {
                empService.batchDelete(ids);
                putMsg("批量删除成功");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            addActionError(e1.getMessage());
        }
        return NONE;
    }
}
