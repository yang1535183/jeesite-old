<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

        // $("#btnReset").click(function () {
		// 	$("#parkNumber").val("");
        //     $("#carType").val("");
        //     $("#owner").val("");
        //     $("#phone").val("");
        //     $("#address").val("");
        //     $("#remarks").val("");
        //     page();
        // });

        function resetForm() {
            $("#parkNumber").val("");
            $("#carType").val("");
            $("#owner").val("");
            $("#phone").val("");
            $("#address").val("");
            $("#remarks").val("");
            page();
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/carmanage/car/">车辆信息列表</a></li>
		<shiro:hasPermission name="carmanage:car:edit"><li><a href="${ctx}/carmanage/car/form">车辆信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="car" action="${ctx}/carmanage/car/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车位号：</label>
				<form:input path="parkNumber" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>车辆类型：</label>
				<form:select path="carType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('car_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>车主姓名：</label>
				<form:input path="owner" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>联系方式：</label>
				<form:input path="phone" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>住址：</label>
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"><input id="btnReset" onclick="resetForm()" class="btn btn-primary" type="button" value="重置"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>车位号</th>
				<th>车辆类型</th>
				<th>车主姓名</th>
				<th>联系方式</th>
				<th>住址</th>
				<th>备注</th>
				<shiro:hasPermission name="carmanage:car:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="car">
			<tr>
				<td><a href="${ctx}/carmanage/car/form?id=${car.id}">
					${car.parkNumber}
				</td>
				<td>
					${fns:getDictLabel(car.carType, 'car_type', '')}
				</td>
				<td>
					${car.owner}
				</td>
				<td>
					${car.phone}
				</td>
				<td>
					${car.address}
				</td>
				<td>
						${car.remarks}
				</a></td>
				<shiro:hasPermission name="carmanage:car:edit"><td>
					<a href="${ctx}/carmanage/payRecord/form?carId=${car.id}">缴费</a>
    				<a href="${ctx}/carmanage/car/form?id=${car.id}">修改</a>
					<a href="${ctx}/carmanage/car/delete?id=${car.id}" onclick="return confirmx('确认要删除该车辆信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>