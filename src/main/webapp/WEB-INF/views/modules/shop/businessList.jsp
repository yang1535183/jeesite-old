<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商家管理</title>
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

        // 查询参数重置
        function cleanParams() {
            $("#createBy.id").val("");
            $("#beginCreateDate").val("");
            $("#endCreateDate").val("");
            $("#name").val("");
            $("#phone").val("");
		    $("#remarks").val("");
            page();
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/shop/business/">商家列表</a></li>
		<shiro:hasPermission name="shop:business:edit"><li><a href="${ctx}/shop/business/form">商家添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="business" action="${ctx}/shop/business/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建人：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${business.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${business.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>商家名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>商家电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="cleanButton" class="btn btn-primary" type="button" value="重置" onclick="cleanParams()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>创建人</th>
				<th>创建时间</th>
				<th>商家名称</th>
				<th>商家电话</th>
				<th>备注</th>
				<shiro:hasPermission name="shop:business:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="business">
			<tr>
				<td><a href="${ctx}/shop/business/form?id=${business.id}">
					${business.createBy.name}
				</a></td>
				<td>
					<fmt:formatDate value="${business.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${business.name}
				</td>
				<td>
					${business.phone}
				</td>
				<td>
						${business.remarks}
				</td>
				<shiro:hasPermission name="shop:business:edit"><td>
    				<a href="${ctx}/shop/business/form?id=${business.id}">修改</a>
					<a href="${ctx}/shop/business/delete?id=${business.id}" onclick="return confirmx('确认要删除该商家吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>