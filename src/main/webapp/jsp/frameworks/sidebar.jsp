<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" pageEncoding="UTF-8" %>

<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul
                class="page-sidebar-menu  page-header-fixed page-sidebar-menu-hover-submenu "
                data-keep-expanded="false" data-auto-scroll="true"
                data-slide-speed="200" style="padding-top: 20px">
            <li class="sidebar-toggler-wrapper hide">
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler"></div> <!-- END SIDEBAR TOGGLER BUTTON -->
            </li>
            <li class="nav-item start <c:if test='${url == "/search"}'>active open</c:if>">
                <a href="${pageContext.request.contextPath}/search" class="nav-link nav-toggle">
                    <i class="icon-magnifier"></i>
                    <span class="title">搜索</span>
                    <c:if test='${url == "/search"}'><span class="selected"></span></c:if>
                    <span class="arrow"></span>
                </a>
            </li>
            <li class="heading">
                <h3 class="uppercase">文章维护</h3>
            </li>

            <li class="nav-item <c:if test='${url == "/article/add"}'>active open</c:if>">
                <a href="${pageContext.request.contextPath}/article/add" class="nav-link nav-toggle">
                    <i class="icon-docs"></i>
                    <span class="title">新增文章</span>
                    <c:if test='${url == "/article/add"}'><span class="selected"></span></c:if>
                    <span class="arrow"></span>
                </a>
            </li>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
</div>
<!-- END SIDEBAR -->