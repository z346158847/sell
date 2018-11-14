<!DOCTYPE html>
<html lang="zh-cn">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <form role="form" method="post" action="/sell/seller/category/save">

                        <input type="hidden" class="form-control" name="categoryId" id="categoryId"  value="${(productCategory.categoryId)!""}"/>

                        <div class="form-group">
                            <label for="categoryName">类别名</label>
                            <input type="text" class="form-control" id="categoryName"  value="${(productCategory.categoryName)!""}" name="categoryName"/>
                        </div>
                        <div class="form-group">
                            <label for="categoryType">类别类型</label>
                            <input type="text" class="form-control" id="categoryType" value="${(productCategory.categoryType)!""}" name="categoryType"/>
                        </div>

                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>