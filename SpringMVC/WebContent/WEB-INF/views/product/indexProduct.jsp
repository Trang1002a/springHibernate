<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layout/header.jsp"%>
<section class="content">
	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title"></h3>
			<form action="searchProducts" method="get" class="form-inline"
				role="form">
				<div class="form-group">
					<input oninput="searchByName(this)" id="content" type="text"
						name="key" class="form-control" placeholder="Tìm kiếm ...">
				</div>
				<button type="submit" class="btn btn-primary">
					<i class="fa fa-search" aria-hidden="true"></i>
				</button>
				<a href="${pageContext.request.contextPath}/product/insertProduct"
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
						<th>Ảnh</th>
						<th>Tên sản phẩm</th>
						<th>Danh mục</th>
						<th>Giá/Giá KM</th>
						<th>Status</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pros}" var="pro">
						<tr>
							<td>${pro.id}</td>
							<td><img
								src="<c:url value="/resources/images/${pro.image }"/>"
								width="60px">
							</td>
							<td>${pro.name}</td>
							<td>
								<label class="label label-success">${pro.categories.name}</label>
							</td>
							<td><fmt:formatNumber type = "number" pattern="#,##0" value = "${pro.price}" />đ/ <fmt:formatNumber type = "number" pattern="#,##0" value = "${pro.sale_price}" />đ</td>
							<td><c:choose>
									<c:when test="${pro.status == 1}">
										<span class="label label-success">Hiện thị</span>
									</c:when>
									<c:when test="${pro.status == 0}">
										<span class="label label-danger">Ẩn</span>
									</c:when>
								</c:choose></td>
							<td><a class="btn btn-small btn-warning"
								href="${pageContext.request.contextPath}/product/edit?id=${pro.id}">Sửa</a>
								<a class="btn btn-small btn-danger"
								href="${pageContext.request.contextPath}/product/delete?id=${pro.id}"
								onclick="return confirm('Bạn có muốn xóa không ?')">Xóa</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- /.box-body -->
		<!-- /.box-footer-->
		<div class="box-footer text-center">
			<nav aria-label="Page navigation ">
				<ul class="pagination">
					<c:forEach begin="1" end="${Math.ceil(totalRecords/10)}" var="i">
						<li class="page-item"><a class="page-link" id="${i}"
							href="${pageContext.request.contextPath}/product?page=${i}">${i}</a>
						</li>
					</c:forEach>
				</ul>
			</nav>
		</div>
	</div>
</section>

<%@ include file="../layout/footer.jsp"%>