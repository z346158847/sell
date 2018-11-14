<!DOCTYPE html>
<html lang="zh-cn">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTOPage.getContent() as order>
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.buyerName}</td>
                    <td>${order.buyerPhone}</td>
                    <td>${order.buyerAddress}</td>
                    <td>${order.orderAmount}</td>
                    <td>${order.getOrderStatusEnum().getMessage()}</td>
                    <td>${order.getPayStatusEnum().getMessage()}</td>
                    <td>${order.createTime}</td>
                    <td>
                        <a href="/sell/seller/order/detail?orderId=${order.orderId}">详情</a>
                    </td>
                    <td>
                        <#if order.getOrderStatusEnum().getMessage() == "已取消" >
                        <#else >
                            <a href="/sell/seller/order/cancel?orderId=${order.orderId}">取消</a>
                        </#if>
                    </td>
                </tr>
                </#list>

                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">

                <#if currentPage lte 1>
                <li class="disabled"><a href="javascript::">上一页</a></li>
                <#else >
                <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}"">上一页</a></li>
                </#if>

                <#list 1..orderDTOPage.getTotalPages() as index>
                    <#if currentPage == index>
                <li class="disabled"><a href="javascript::">${index}</a></li>
                    <#else>
                <li ><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>

                <#if currentPage gte orderDTOPage.getTotalPages()>
                <li class="disabled"><a href="javascript::">下一页</a></li>
                <#else >
                <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                </#if>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>