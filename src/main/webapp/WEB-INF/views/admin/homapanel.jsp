<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<%@ include file="/WEB-INF/views/admin/header.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/views/admin/navbar.jsp" %>
	<div class="page-layout">
	    <%@ include file="/WEB-INF/views/admin/menu.jsp" %>
	    <main class="main-panel">
	        <div class="content-wrapper">
	            <div class="content-container">
	                <section class="content-card">
	                    <div class="content-card-body">
	                        <jsp:include page="${contentPage}" />
	                    </div>
	                </section>
	            </div>
	        </div>
	    </main>
	</div>
</body>
</html>