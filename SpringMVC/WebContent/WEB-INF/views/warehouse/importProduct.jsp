<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layout/header.jsp"%>
<section class="content">
	<div class="box">
		<div class="row">
			<div class="col-md-6">
				<div class="box-header with-border">
					<h3 class="box-title">Chọn sản phẩm cần nhập kho</h3>
					<form action="" method="get" class="form-inline" role="form">
						<div class="form-group">
							<input oninput="searchByName(this)" id="content" type="text"
								name="name" class="form-control" placeholder="Tìm kiếm ...">
						</div>
						<button type="submit" class="btn btn-primary">
							<i class="fa fa-search" aria-hidden="true"></i>
						</button>
					</form>
					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse" data-toggle="tooltip" title="Collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-widget="remove" data-toggle="tooltip" title="Remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<div class="box-body">
					<form action="createVotes" method="POST" class="form-inline" role="form">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Id</th>
									<th>Ảnh</th>
									<th>Tên sản phẩm</th>
									<th>Giá/Giá KM</th>
									<th></th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${pros}" var="pro">

									<tr>
										<td>
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													value="${pro.id}" name="listImport" id="listImport">
											</div>
										</td>
										<td><img
											src="<c:url value="/resources/images/${pro.image }"/>"
											width="60px"></td>
										<td>${pro.name}</td>
										<td><fmt:formatNumber type="number" pattern="#,##0"
												value="${pro.price}" />đ/ <fmt:formatNumber type="number"
												pattern="#,##0" value="${pro.sale_price}" />đ</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
						<button type="submit" class="btn btn-success ml-auto">Tạo phiếu nhập kho</button>
					</form>
				</div>
				<!-- /.box-body -->
				<!-- /.box-footer-->
				<div class="box-footer">
					<nav aria-label="Page navigation ">
						<ul class="pagination">
							<c:forEach begin="1" end="${Math.ceil(totalRecords/10)}" var="i">
								<li class="page-item"><a class="page-link" id="${i}"
									href="${pageContext.request.contextPath}/warehouse/importWarehouse?<c:if test="${name != ''}">name=${name}&</c:if>page=${i}">${i}</a>
								</li>
							</c:forEach>
						</ul>

					</nav>
				</div>
			</div>
			<div class="col-md-6"></div>
		</div>
	</div>
</section>

<%@ include file="../layout/footer.jsp"%>