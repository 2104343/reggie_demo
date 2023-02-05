package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.domain.Category;
import com.itheima.reggie.domain.Dish;
import com.itheima.reggie.domain.DishFlavor;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.service.CategoryService;
import com.sun.javafx.logging.PulseLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program:reggie_take_out
 * @description:
 * @author:左毅
 * @createData:2022/9/27
 **/
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService cateGoryService;

    @PostMapping
    public R<String> save(@RequestBody Category category)
    {
        log.info("category:{}",category);
        cateGoryService.save(category);
        return R.success("添加成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        Page pageInfo=new Page(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Category::getSort);
        cateGoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);

    }
    @DeleteMapping
    public R<String> deleteById(Long ids){
           log.info("要删除的id为{}",ids);
//           cateGoryService.removeById(id);
           cateGoryService.remove(ids);
           return R.success("删除成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("要修改的分类信息{}",category);
        cateGoryService.updateById(category);
        return R.success("修改成功");

    }
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        log.info("id为：{}",category.getId());
        LambdaQueryWrapper<Category>  queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = cateGoryService.list(queryWrapper);
        return R.success(list);

    }
}
