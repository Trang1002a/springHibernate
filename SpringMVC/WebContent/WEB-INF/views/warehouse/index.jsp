<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../layout/header.jsp"%>
<section class="content">
	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title"></h3>
			<form action="searchCategores" method="get" class="form-inline"
				role="form">
				<div class="form-group">
					<input oninput="searchByName(this)" id="content" type="text"
						name="name" class="form-control" placeholder="Tìm kiếm ...">
				</div>
				<button type="submit" class="btn btn-primary">
					<i class="fa fa-search" aria-hidden="true"></i>
				</button>
				<a
					href="${pageContext.request.contextPath}/warehouse/insertCategory"
					class="btn btn-success btn-sm">Thêm mới</a>
			</form>
			<div class="box-tools pull-right">
				<button type="button" class="btn btn-box-tool"
					data-widget="collapse" data-toggle="tooltip" title="Collapse">
					<i class="fa fa-minus"></i>
				</button>
				<button type="button" class="btn btn-box-tool" data-widget="remove"
					data-toggle="tooltip" title="Remove">
					<i class="fa fa-times"></i>
				</button>
			</div>
		</div>
		<div class="box-body">
			<form:form action="importWarehouseDB" method="POST" modelAttribute="warehouse" class="form-inline">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Tên sản phẩm</th>
							<th>Số lượng</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pros}" var="ware" varStatus="loop">
							<tr>
								<form:input path="products.id" value = "${ware.id}" type="hidden"/>
								<td>${loop.count}</td>
								<td>${ware.name}</td>
								<td>
								<form:input path="quantity" type="number" id="quantity" name="quantity" min="0" max="100" value="1" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<button type="submit" class="btn btn-success ml-auto">Nhập kho</button>
			</form:form>
		</div>
		<!-- /.box-body -->
		<!-- /.box-footer-->
	</div>
</section>

<%@ include file="../layout/footer.jsp"%>