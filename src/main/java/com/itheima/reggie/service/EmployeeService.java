package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.domain.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
 * @program:reggie_take_out
 * @description:
 * @author:左毅
 * @createData:2022/9/20
 **/
@Service
public interface EmployeeService extends IService<Employee> {
}
