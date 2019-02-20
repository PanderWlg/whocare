package com.womow.toc.whocare.clbx.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.womow.toc.whocare.clbx.service.BxClService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author changqingshun
 * @since 2019-01-29
 */
@RestController
@RequestMapping("/clbx/BxClDto")
public class BxClController {
 /**
  服务实现类
 **/
 @Autowired
 private BxClService bxClService;
}
