<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../layout/header.jsp"%>
<section class="content">
	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title"></h3>
			<form action="searchCategores" method="get" class="form-inline"
				role="form">
				<div class="form-group">
					<input oninput="searchByName(this)" id="content" type="text"
						name="key" class="form-control" placeholder="Tìm kiếm ...">
				</div>
				<button type="submit" class="btn btn-primary">
					<i class="fa fa-search" aria-hidden="true"></i>
				</button>
				<a href="${pageContext.request.contextPath}/category/insertCategory"
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
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Category Name</th>
						<th>Status</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cats}" var="cat">
						<tr>
							<td>${cat.id}</td>
							<td>${cat.name}</td>
							<td>
								<c:choose>
									<c:when test="${cat.status == 1}">
										<span class="label label-success">Hiện thị</span>
									</c:when>
									<c:when test="${cat.status == 0}">
										<span class="label label-danger">Ẩn</span>
									</c:when>
								</c:choose>
							</td>
							<td>
							<a class="btn btn-small btn-success" href="${pageContext.request.contextPath}/category/edit?id=${cat.id}">Sửa</a>
							<a class="btn btn-small btn-danger" href="${pageContext.request.contextPath}/category/delete?id=${cat.id}" onclick="return confirm('Bạn có muốn xóa không ?')">Xóa</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- /.box-body -->
		<!-- /.box-footer-->
	</div>
</section>

<%@ include file="../layout/footer.jsp"%>