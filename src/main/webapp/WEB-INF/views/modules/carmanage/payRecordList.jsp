<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>缴费记录管理</title>
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
        //     $("#carId").val("");
        //     $("#payDate").val("");
        //     $("#endDate").val("");
        //     $("#payMoney").val("");
        //     $("#payName").val("");
        //     $("#remarks").val("");
        //     page();
        // });

		function resetForm() {
            $("#parkNumber").val("");
            $("#payDate").val("");
            $("#endDate").val("");
            $("#payMoney").val("");
            $("#payName").val("");
            $("#remarks").val("");
            page();
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/carmanage/payRecord/">缴费记录列表</a></li>
		<%--<shiro:hasPermission name="carmanage:payRecord:edit"><li><a href="${ctx}/carmanage/payRecord/form">缴费记录添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="payRecord" action="${ctx}/carmanage/payRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车位号：</label>
				<input id="parkNumber" name="carId.parkNumber" value="${payRecord.carId.parkNumber}" maxlength="255" class="input-medium"/>
			</li>
			<li><label>缴费时间：</label>
				<input name="payDate" type="text" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${payRecord.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>到期时间：</label>
				<input name="endDate" type="text" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${payRecord.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>缴费金额：</label>
				<form:input path="payMoney" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>缴费人：</label>
				<form:input path="payName" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>缴费时间</th>
				<th>到期时间</th>
				<th>缴费金额</th>
				<th>缴费人</th>
				<th>备注</th>
				<shiro:hasPermission name="carmanage:payRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payRecord">
			<tr>
				<td><a href="${ctx}/carmanage/payRecord/form?id=${payRecord.id}">
					${payRecord.carId.parkNumber}
				</td>
				<td>
					<fmt:formatDate value="${payRecord.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${payRecord.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${payRecord.payMoney}
				</td>
				<td>
					${payRecord.payName}
				</td>
				<td>
						${payRecord.remarks}
				</a></td>
				<shiro:hasPermission name="carmanage:payRecord:edit"><td>
    				<a href="${ctx}/carmanage/payRecord/form?id=${payRecord.id}">修改</a>
					<a href="${ctx}/carmanage/payRecord/delete?id=${payRecord.id}" onclick="return confirmx('确认要删除该缴费记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>