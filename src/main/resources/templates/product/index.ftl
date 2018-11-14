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
                    <form role="form" method="post" action="/sell/seller/product/save">

                        <input type="hidden" class="form-control" name="productId" id="productId"  value="${(productInfo.productId)!""}"/>

                        <div class="form-group">
                            <label for="productName">商品名</label>
                            <input type="text" class="form-control" id="productName"  value="${(productInfo.productName)!""}" name="productName"/>
                        </div>
                        <div class="form-group">
                            <label for="productPrice">商品价格</label>
                            <input type="text" class="form-control" id="productPrice" value="${(productInfo.productPrice)!""}" name="productPrice"/>
                        </div>
                        <div class="form-group">
                            <label for="productStock">商品库存</label>
                            <input type="text" class="form-control" id="productStock" value="${(productInfo.productStock)!""}" name="productStock"/>
                        </div>
                        <div class="form-group">
                            <label for="productDescription">商品描述</label>
                            <input type="text" class="form-control" id="productDescription" value="${(productInfo.productDescription)!""}" name="productDescription"/>
                        </div>
                        <div class="form-group">
                            <label for="productIcon">商品小图</label>
                            <input type="text" class="form-control" id="productIcon" value="${(productInfo.productIcon)!""}" name="productIcon"/>
                        </div>
                        <div class="form-group">
                            <label for="categoryType">商品类别</label>
                            <select id="categoryType" name="categoryType" class="form-control">
                                <#list productCategoryList as productCategory>
                                    <option value="${productCategory.categoryType}" <#if ((productInfo.categoryType)!1) == productCategory.categoryType>selected</#if>>${productCategory.categoryName}</option>
                                </#list>
                            </select>
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