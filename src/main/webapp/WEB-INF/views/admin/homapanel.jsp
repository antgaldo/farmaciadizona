<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/views/admin/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<%@ include file="/WEB-INF/views/admin/navbar.jsp" %>
	<div class="page-body-wrapper pt-5">
	    <%@ include file="/WEB-INF/views/admin/menu.jsp" %>
		<div class="main-panel w-100">
			<div class="content-wrapper">
				<div class="row">
					<div class="col-lg-12 grid-margin stretch-card">
						<div class="card">
							<div class="card-body p-4">

								<jsp:include page="${contentPage}" />

							</div>
						
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>