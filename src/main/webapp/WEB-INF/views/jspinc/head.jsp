<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link href="<%=request.getContextPath()%>/resources/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/resources/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css" />	
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />	
		<script src="<%=request.getContextPath()%>/resources/js/tether-1.3.3/dist/js/tether.min.js"></script>						
		<script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-3.2.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/jquery.validate.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/script.js"></script>
		<script>
			UPLOADCARE_LOCALE = "en";
			UPLOADCARE_PUBLIC_KEY = '402840513ca8fdd44f3b';
		    UPLOADCARE_TABS = 'file camera dropbox';
		    UPLOADCARE_IMAGES_ONLY = true;
		</script>
		<script charset="utf-8" src="//ucarecdn.com/widget/3.1.4/uploadcare/uploadcare.full.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Login</title>
	</head>
	<body>	