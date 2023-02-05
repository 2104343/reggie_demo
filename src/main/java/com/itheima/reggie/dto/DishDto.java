package com.itheima.reggie.dto;

import com.itheima.reggie.domain.Dish;
import com.itheima.reggie.domain.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:reggie_take_out
 * @description:
 * @author:左毅
 * @createData:2022/9/30
 **/
@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors=new ArrayList<>();

    private String categoryName;

    private Integer copies;

}
