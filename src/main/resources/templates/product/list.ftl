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
                            <th>商品id</th>
                            <th>商品名</th>
                            <th>商品图</th>
                            <th>商品库存</th>
                            <th>描述</th>
                            <th>单价</th>
                            <th>类别</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list productInfoPage.getContent() as productInfo>
                <tr>
                    <td>${productInfo.productId}</td>
                    <td>${productInfo.productName}</td>
                    <td><img src="${productInfo.productIcon}" alt="商品图" width="100" height="100"></td>
                    <td>${productInfo.productStock}</td>
                    <td>${productInfo.productDescription}</td>
                    <td>${productInfo.productPrice}</td>
                    <td>${productInfo.categoryType}</td>
                    <td>${productInfo.createTime}</td>
                    <td>${productInfo.updateTime}</td>

                    <td>
                        <a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a>
                    </td>
                    <td>
                        <#if productInfo.getProductStatusEnum().getMessage() == "在架">
                            <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                            <#else >
                            <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
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
                <li><a href="/sell/seller/product/list?page=${currentPage - 1}&size=${size}"">上一页</a></li>
                </#if>

                <#list 1..productInfoPage.getTotalPages() as index>
                    <#if currentPage == index>
                <li class="disabled"><a href="javascript::">${index}</a></li>
                    <#else>
                <li ><a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>

                <#if currentPage gte productInfoPage.getTotalPages()>
                <li class="disabled"><a href="javascript::">下一页</a></li>
                <#else >
                <li><a href="/sell/seller/product/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                </#if>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>