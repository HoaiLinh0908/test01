<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api/news" />
<c:url var="Newsurl" value="/quan-tri/news/list" />
<c:url var="createNewsURL" value="/quan-tri/news/edit" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
<div class="main-content">
<div class="dt-buttons btn-overlap btn-group">
	<a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
		data-toggle="tooltip" title="Add News" href="${createNewsURL}">
			<span>
				<i class="fa fa-plus-circle" aria-hidden="true">Add</i>
			</span>	
	</a>
	<button type="button" id="btnDelete" onclick="warningBeforeDelete()"
			class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
			data-toggle="tooltip" title="Delete News">
				<span>
					<i class="fa fa-trash" aria-hidden="true">Delete</i>
				</span>			
	</button>
</div>
<c:if test="${not empty message}">
	<div class="alert alert-${alert}">
  		${message}
	</div>
</c:if>
<form action="<c:url value='/quan-tri/news/list'/>" id="formSubmit" method="get">
  <table class="table table-striped">
	<thead>
		<tr>
			<th><input type="checkbox" id="checkAll"></th>
			<th>Name</th>
			<th>Short Description</th>
			<th>Method</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${model.listResult}">
			<tr>
				<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
				<td>${item.title}</td>
				<td>${item.shortDescription}</td>
				<td>
					<c:url var="updateNewsURL" value="/quan-tri/news/edit">
						<c:param name="id" value="${item.id}" />
					</c:url>
					<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
						title="Update News" href="${updateNewsURL}">
							<i class="fa fa-pencil-square" aria-hidden="true">Edit</i>	
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
  </table>
  <ul class="pagination" id="pagination"></ul>
  <input type="hidden" value="" id="page" name="page"/>
  <input type="hidden" value="" id="limit" name="limit"/>
</form>
</div>
 <script type="text/javascript">
 var totalPage = ${model.totalPage};
 var currentPage = ${model.page};
  $(function () {
      window.pagObj = $('#pagination').twbsPagination({
          totalPages: totalPage,
          visiblePages: 5,
          startPage: currentPage,
          onPageClick: function (event, page) {
        	  if (currentPage != page) {
          			$('#limit').val(2);
					$('#page').val(page);
					$('#formSubmit').submit();
				}
          }
      });
  });
	
  
  function warningBeforeDelete() {
	  swal({
		  title: "Xác nhận xóa",
		  text: "Bạn có chắc chắn muốn xóa hay không",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonClass: "btn-success",
		  cancelButtonClass: "btn-danger",
		  confirmButtonText: "Xác nhận",
		  cancelButtonText: "Hủy bỏ",
		}).then(function(isConfirm) {
		  if (isConfirm) {
				var ids = $('tbody input[type=checkbox]:checked').map(function () {
		            return $(this).val();
		        }).get();
				deleteNew(ids);
		  }
		});
	  
	  function deleteNew(data) {
	        $.ajax({
	            url: '${APIurl}',
	            type: 'DELETE',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            success: function (result) {
	                window.location.href = "${Newsurl}?page=1&limit=2&message=delete_success";
	            },
	            error: function (error) {
	            	window.location.href = "${Newsurl}?page=1&limit=2&message=error_system";
	            }
	        });
	    }
  }
</script>
</body>
</html>