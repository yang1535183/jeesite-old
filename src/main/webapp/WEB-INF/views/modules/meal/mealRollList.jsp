<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>餐卷管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/meal/mealRoll/">餐卷管理列表</a></li>
		<shiro:hasPermission name="meal:mealRoll:edit"><li><a href="${ctx}/meal/mealRoll/form">餐卷管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mealRoll" action="${ctx}/meal/mealRoll/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建者：</label>
				<sys:treeselect id="createBy" name="createBy.id" value="${mealRoll.createBy.id}" labelName="" labelValue="${mealRoll.createBy.id}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mealRoll.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${mealRoll.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>面值：</label>
				<form:input path="faceValue" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>合作商家：</label>
				<sys:treeselect id="coopBusiness" name="coopBusiness.id" value="${mealRoll.coopBusiness.id}" labelName="" labelValue="${mealRoll.coopBusiness.id}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>是否领取：</label>
				<form:input path="receive" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remarks" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnClean" class="btn btn-primary" type="button" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>创建者</th>
				<th>创建时间</th>
				<th>面值</th>
				<th>合作商家</th>
				<th>是否领取</th>
				<th>备注</th>
				<shiro:hasPermission name="meal:mealRoll:edit">
					<th>操作</th>
				</shiro:hasPermission>
				<shiro:hasPermission name="meal:mealRoll:receive">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mealRoll">
			<tr>
				<td><a href="${ctx}/meal/mealRoll/form?id=${mealRoll.id}">
					${mealRoll.createBy.name}
				</a></td>
				<td>
					<fmt:formatDate value="${mealRoll.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mealRoll.faceValue}
				</td>
				<td>
					${mealRoll.coopBusiness.name}
				</td>
				<td>
					<c:choose>
						<c:when test="${mealRoll.receive eq '0'}">
							未领取
						</c:when>
						<c:otherwise>
							已领取
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					${mealRoll.remarks}
				</td>
				<shiro:hasPermission name="meal:mealRoll:edit"><td>
					<c:if test="${mealRoll.receive eq '0'}">
						<a href="${ctx}/meal/mealRoll/form?id=${mealRoll.id}">修改</a>
						<a href="${ctx}/meal/mealRoll/delete?id=${mealRoll.id}" onclick="return confirmx('确认要删除该餐卷管理吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${mealRoll.receive eq '1'}">
						<span>已领取</span>
					</c:if>
				</td></shiro:hasPermission>

				<shiro:hasPermission name="meal:mealRoll:receive">
					<td>
						<c:if test="${mealRoll.receive eq '0'}">
							<a href="${ctx}/meal/mealRoll/receive?id=${mealRoll.id}" onclick="return confirmx('确认要领取该餐卷吗？', this.href)">领取</a>
						</c:if>
						<c:if test="${mealRoll.receive eq '1'}">
							<span>已领取</span>
						</c:if>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>