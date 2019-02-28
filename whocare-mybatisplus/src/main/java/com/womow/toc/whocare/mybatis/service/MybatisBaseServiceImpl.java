package com.womow.toc.whocare.mybatis.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @param <M>
 * @param <T>
 * @author changqingshun
 */
public class MybatisBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements MybatisBaseService<T> {
}
