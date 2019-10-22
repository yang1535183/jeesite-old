<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商家管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});

		// 地址选择
		function choosePoint() {
		    var jwd = $("#jwd").val();
			window.open("${ctx}/map/baiDuMap/baseMap?jwd="+jwd);
        }
        
        // 确认坐标点
		function chcekPoint(j,w,address) {
		    $("#address").val(address);
		    $("#jwd").val(j+","+w);
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shop/business/">商家列表</a></li>
		<li class="active"><a href="${ctx}/shop/business/form?id=${business.id}">商家<shiro:hasPermission name="shop:business:edit">${not empty business.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="shop:business:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="business" action="${ctx}/shop/business/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商家名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商家电话：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商家地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<form:input path="jwd" cssStyle="display: none" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<input type="button" onclick="choosePoint()" value="选择">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经验范围：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商家照片：</label>
			<div class="controls">
				<form:input path="photo" htmlEscape="false" maxlength="150" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">营业执照：</label>
			<div class="controls">
				<form:input path="license" htmlEscape="false" maxlength="150" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">卫生许可证：</label>
			<div class="controls">
				<form:input path="hygiene" htmlEscape="false" maxlength="150" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="shop:business:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>