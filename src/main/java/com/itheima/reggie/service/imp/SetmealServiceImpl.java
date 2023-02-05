package com.itheima.reggie.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomExeciption;
import com.itheima.reggie.domain.Category;
import com.itheima.reggie.domain.Setmeal;
import com.itheima.reggie.domain.Setmeal;
import com.itheima.reggie.domain.SetmealDish;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.mapper.SetmealMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.SetmealDishService;
import com.itheima.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private  SetmealService setmealService;

    @Autowired

    private SetmealDishService setmealDishService;

    @Autowired

    private CategoryService categoryService;

    /**
     *
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {

        this.save(setmealDto);

        List<SetmealDish> list = setmealDto.getSetmealDishes().stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(list);
    }
    @Transactional
    @Override
    public void removeWithDish(List<Long> ids) {

        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.in(Setmeal::getId,ids);

        queryWrapper.eq(Setmeal::getStatus,1);

        int count = setmealService.count(queryWrapper);

        if(count>0){

            throw  new CustomExeciption("改套餐未停售");
        }

        setmealService.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> queryWrapper1 =new LambdaQueryWrapper<>();

        queryWrapper1.in(SetmealDish::getSetmealId,ids);

        setmealDishService.remove(queryWrapper1);


    }

    @Override
    public SetmealDto getByIdWithDish(Long id) {

        Setmeal setmeal = setmealService.getById(id);

        SetmealDto setmealDto=new SetmealDto();

        BeanUtils.copyProperties(setmeal,setmealDto);
//
//        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();

//          LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();

          Long categoryId = setmeal.getCategoryId();


//
//        queryWrapper.eq(Setmeal::getCategoryId, setmeal.getCategoryId());

//        List<SetmealDish> list=setmealDishService.list(queryWrapper);
        LambdaQueryWrapper<SetmealDish> queryWrapper1=new LambdaQueryWrapper<>();

        queryWrapper1.eq(SetmealDish::getSetmealId,setmeal.getId());

        List<SetmealDish> list=setmealDishService.list(queryWrapper1);

        setmealDto.setSetmealDishes(list);

        Category category = categoryService.getById(categoryId);
//
        String categoryName = category.getName();

        setmealDto.setCategoryName(categoryName);

        return setmealDto;
    }

    @Override
    public void updateWithDish(SetmealDto setmealDto) {

        this.updateById(setmealDto);


        //更新setmeal_dish表信息delete操作
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, setmealDto.getId());
        setmealDishService.remove(queryWrapper);

        //更新setmeal_dish表信息insert操作
        List<SetmealDish> SetmealDishes = setmealDto.getSetmealDishes();

        SetmealDishes = SetmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(SetmealDishes);


    }
}
