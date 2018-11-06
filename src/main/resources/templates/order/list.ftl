<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>

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
                    <td>详情</td>
                    <td>修改</td>
                </tr>
                </#list>

                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>