package com.vren.weldingmonitoring_java.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vren.weldingmonitoring_java.common.domain.OrderItem;
import com.vren.weldingmonitoring_java.common.domain.PageParam;
import com.vren.weldingmonitoring_java.common.domain.PageResult;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页工具类
 */

public class PageUtil {

    public static <T> PageResult<T> convert2PageResult(IPage<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        result.setList(page.getRecords());
        return result;
    }
    public static <T> PageResult<T> convert2PageResult(List<T> sourceList, PageParam baseDTO) {
        List<List<T>> partition = ListUtils.partition(sourceList, baseDTO.getPageSize());
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPage((long) baseDTO.getPageIndex());
        pageResult.setPages((long) partition.size());
        pageResult.setTotal((long) sourceList.size());
        pageResult.setPageSize((long) baseDTO.getPageSize());

        if (baseDTO.getPageIndex() > partition.size()) {
            pageResult.setList(new ArrayList<>());
        } else {
            pageResult.setList(partition.get(baseDTO.getPageIndex() - 1));
        }
        return pageResult;
    }
    public static <T> Page<T> convert2QueryPage(PageParam baseDTO) {
        Page<T> page = new Page<>();
        List<OrderItem> orders = baseDTO.getOrders();
        if (orders != null && !orders.isEmpty()) {
            List<com.baomidou.mybatisplus.core.metadata.OrderItem> orderItemList = orders.stream().map(PageUtil::convertOrderItem).collect(Collectors.toList());
            page.setOrders(orderItemList);
        }
        page.setCurrent(baseDTO.getPageIndex());
        page.setSize(baseDTO.getPageSize());
        if (null != baseDTO.getSearchCount()) {
            page.setSearchCount(baseDTO.getSearchCount());
        }
        return page;
    }

    private static com.baomidou.mybatisplus.core.metadata.OrderItem convertOrderItem(OrderItem orderItemDTO) {
        if (orderItemDTO.isAsc()) {
            return com.baomidou.mybatisplus.core.metadata.OrderItem.asc(orderItemDTO.getColumn());
        } else {
            return com.baomidou.mybatisplus.core.metadata.OrderItem.desc(orderItemDTO.getColumn());
        }
    }

    /**
     * 转换为 PageResult 对象
     *
     * @param page
     * @param sourceList  原list
     * @param targetClazz 目标类
     * @return
     */
    public static <T, E> PageResult<T> convert2PageResult(IPage page, List<E> sourceList, Class<T> targetClazz) {
        PageResult<T> pageResult = setPage(page);
        List<T> records = BeanUtil.copyList(sourceList, targetClazz);
        page.setRecords(records);
        pageResult.setList(records);
        return pageResult;
    }

    /**
     * 转换为 PageResultDTO 对象
     *
     * @param page
     * @param sourceList list
     * @return
     */
    public static <T, E> PageResult<T> convert2PageResult(IPage page, List<E> sourceList) {
        PageResult pageResult = setPage(page);
        page.setRecords(sourceList);
        pageResult.setList(sourceList);
        return pageResult;
    }

    private static PageResult setPage(IPage page) {
        PageResult pageResult = new PageResult();
        pageResult.setPage(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotal(page.getTotal());
        pageResult.setPages(page.getPages());
        return pageResult;
    }
}
