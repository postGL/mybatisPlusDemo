//package com.zbs.mybatisplus.controller;
//
//import com.baomidou.mybatisplus.core.metadata.PageList;
//import com.github.pagehelper.PageHelper;
//import com.zbs.mybatisplus.common.AjaxResult;
//import com.zbs.mybatisplus.common.HttpStatus;
//import com.zbs.mybatisplus.page.PageInfo;
//import com.zbs.mybatisplus.page.PageParam;
//import com.zbs.mybatisplus.page.TableSupport;
//import com.zbs.mybatisplus.utils.DateUtils;
//import com.zbs.mybatisplus.utils.SqlUtil;
//import com.zbs.mybatisplus.utils.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//
//import java.beans.PropertyEditorSupport;
//import java.util.Date;
//import java.util.List;
//
///**
// * web层通用数据处理
// *
// * @author SPL
// * @since 2020-07-29
// */
//public class BaseController {
//    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);
//
//    /**
//     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
//     */
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        // Date 类型转换
//        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) {
//                setValue(DateUtils.parseDate(text));
//            }
//        });
//    }
//
//    /**
//     * 设置请求分页数据
//     */
//    protected void startPage() {
//        PageParam pageParam = TableSupport.buildPageRequest();
//        Integer pageNum = pageParam.getPageNum();
//        Integer pageSize = pageParam.getPageSize();
//        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
//            String orderBy = SqlUtil.escapeOrderBySql(pageParam.getOrderBy());
//            PageHelper.startPage(pageNum, pageSize, orderBy);
//        }
//    }
//
//    /**
//     * 响应请求分页数据
//     */
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    protected PageInfo getDataTable(List<?> list) {
//        PageInfo rspData = new PageInfo();
//        rspData.setCode(HttpStatus.SUCCESS);
//        rspData.setMsg("查询成功");
//        rspData.setRows(list);
//        rspData.setTotal(new com.github.pagehelper.PageInfo(list).getTotal());
//        return rspData;
//    }
//
//    /**
//     * 响应请求分页数据-02
//     */
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    protected PageInfo getDataTable(PageList<?> list) {
//        PageInfo rspData = new PageInfo();
//        rspData.setCode(HttpStatus.SUCCESS);
//        rspData.setMsg("查询成功");
////        rspData.setRows((List<?>) list.getData());
////        rspData.setTotal(list.getRecordsTotal());
//        return rspData;
//    }
//
//    /**
//     * 响应返回结果
//     *
//     * @param rows 影响行数
//     * @return 操作结果
//     */
//    protected AjaxResult toAjax(int rows) {
//        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
//    }
//
//    /**
//     * 页面跳转
//     */
//    public String redirect(String url) {
//        return StringUtils.format("redirect:{}", url);
//    }
//
//    /**
//     * 响应返回结果
//     */
//    protected AjaxResult renderSuccess() {
//        return AjaxResult.success();
//    }
//
//    /**
//     * 响应返回结果：成功
//     */
//    protected AjaxResult renderSuccess(Object o) {
//        return AjaxResult.success(o);
//    }
//
//    /**
//     * 响应返回结果：失败1
//     */
//    protected AjaxResult renderError() {
//        return AjaxResult.error();
//    }
//
//    /**
//     * 响应返回结果：失败2
//     */
//    protected AjaxResult renderError(String msg) {
//        return AjaxResult.error(msg);
//    }
//
//    /**
//     * 响应返回结果：失败3
//     */
//    protected AjaxResult renderError(String msg, Object o) {
//        return AjaxResult.error(msg, o);
//    }
//
//}
