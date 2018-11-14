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
                            <th>类别名</th>
                            <th>类别类型</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list productCategoryList as productCategory>
                <tr>
                    <td>${productCategory.categoryName}</td>
                    <td>${productCategory.categoryType}</td>
                    <td>${productCategory.createTime}</td>
                    <td>${productCategory.updateTime}</td>
                    <td>
                        <a href="/sell/seller/category/index?categoryId=${productCategory.categoryId}">修改</a>
                    </td>
                </tr>
                </#list>

                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>


</body>
</html>