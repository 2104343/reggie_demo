package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.common.R;
import com.itheima.reggie.domain.Category;
import org.springframework.stereotype.Service;

//@Service
public interface CategoryService extends IService<Category> {
    public void remove(Long ids);
}
