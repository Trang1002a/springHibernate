<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../layout/header.jsp"%>
<style>
.erorr {
	color: red;
}
</style>
<section class="content">
	<!-- Default box -->
	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title">Edit Product</h3>
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
			<form:form method="post" action="updateProduct" modelAttribute="pro"
				enctype="multipart/form-data">
				<form:input path="id" type="hidden" value="${pro.id}"/>
				<div class="row">
					<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
						<div class="form-group">
							<label for="">Product Name :</label>
							<form:input path="name" cssClass="form-control" />
							<br>

						</div>
						<div class="form-group">
							<label for="">Image :</label> <input type="file" class="form-control" name="upload" id="upload" placeholder="Ảnh sản phẩm ">
							<img src="<c:url value="/resources/images/${pro.image}"/>" width="60px" id="show_image" alt="" style="width: 300px">
						</div>
						<div class="form-group">
							<label for="">Trạng thái</label>
							<div class="radio">
								<label> <form:radiobutton path="status" value="1"/>Hiển
									thị
								</label>
							</div>
							<div class="radio">
								<label> <form:radiobutton path="status" value="0" />Ẩn
								</label>
							</div>


							<form:errors path="status" cssClass="erorr" />
						</div>
						<button type="submit" class="btn btn-primary">Cập nhật</button>
					</div>
					<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<div class="form-group">
							<label for="">Category :</label>
							<form:select path="categories.id" cssClass="form-control">
								<form:options items="${list}" itemLabel="name" itemValue="id" />
							</form:select>
							<br>
						</div>
						<div class="form-group">
							<label for="">Price</label>
							<form:input path="price" cssClass="form-control" />
							<br>

						</div>
						<div class="form-group">
							<label for="">Sale Price</label>
							<form:input path="sale_price" cssClass="form-control" />
							<br>

						</div>

					</div>
				</div>
			</form:form>
		</div>
	</div>
	<!-- /.box -->
</section>

<%@ include file="../layout/footer.jsp"%>