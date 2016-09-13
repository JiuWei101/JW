<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" pageEncoding="UTF-8" %>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en"><!--<![endif]-->
<head>
    <title>${web_entity.title}</title>

    <jsp:include page="../frameworks/commonmeta.jsp"/>
    <!-- Add custom meta follow -->

    <jsp:include page="../frameworks/commoncss.jsp"/>
    <!-- Add custom css files follow -->
    <!-- DATATABLES -->
    <link href="${pageContext.request.contextPath}/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css"
          rel="stylesheet"
          type="text/css"/>
    <!-- selected -->
    <link href="${pageContext.request.contextPath}/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/select2/css/select2-bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-content-white">
<!-- BEGIN HEADER -->
<jsp:include page="../frameworks/header.jsp"/>
<!-- END HEADER -->

<div class="clearfix"></div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
    <jsp:include page="../frameworks/sidebar.jsp"/>

    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
        <div class="page-content" style="min-height: 1496px">


            <!-- 查询的FORM表单 -->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN VALIDATION STATES-->
                    <div class="portlet light portlet-fit portlet-form ">
                        <div class="portlet-body">
                            <form action="#" id="query_form" class="form-horizontal">
                                <div class="form-body">
                                    <c:forEach var="rows" items="${web_entity.queryForm}">
                                    <div class="row">
                                        <c:forEach var="column" items="${rows}">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <c:choose>
                                                <c:when test="${'input_hidden' == column.type}">
                                                    <input id="edit_form_${column.name}"
                                                           name="${column.name}"
                                                           type="hidden" class="form-control"/>
                                                </c:when>
                                                <c:when test="${'input_text' == column.type}">
                                                    <label class="control-label col-md-4"
                                                           for="query_form_${column.name}">${column.title}
                                                        <c:if test="${column.required}">
                                                            <span class="required"> * </span>
                                                        </c:if>
                                                    </label>
                                                    <div class="col-md-8">
                                                        <input id="query_form_${column.name}"
                                                               name="${column.name}"
                                                               type="text" class="form-control"/>
                                                    </div>
                                                </c:when>
                                                <c:when test="${'select_one' == column.type}">
                                                    <label class="control-label col-md-4"
                                                           for="query_form_${column.name}">${column.title}
                                                        <c:if test="${column.required}">
                                                            <span class="required"> * </span>
                                                        </c:if>
                                                    </label>
                                                    <div class="col-md-8">
                                                        <select id="query_form_${column.name}"
                                                                name="${column.name}"
                                                                class="form-control select2me">
                                                            <c:forEach var="option"
                                                                       items="${column.options}">
                                                                <option value="${option.key}">${option.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </c:when>
                                                <c:when test="${'textarea' == column.type}">
                                                    <label class="control-label col-md-4"
                                                           for="query_form_${column.name}">${column.title}
                                                        <c:if test="${column.required}">
                                                            <span class="required"> * </span>
                                                        </c:if>
                                                    </label>
                                                    <div class="col-md-8">
                                                        <select id="query_form_${column.name}"
                                                                name="${column.name}"
                                                                class="form-control select2me">
                                                            <c:forEach var="option"
                                                                       items="${column.options}">
                                                                <option value="${option.key}">${option.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </c:when>
                                                <c:when test="${'datepicker' == column.type}">
                                                <label class="control-label col-md-4"
                                                       for="query_form_${column.name}">${column.title}</label>
                                                <div class="col-md-8">
                                                    <div class="input-group input-medium date date-picker"
                                                         data-date-format="yyyy-mm-dd"
                                                         data-date-viewmode="years"/>
                                                    <input id="query_form_${column.name}"
                                                           name="${column.name}"
                                                           type="text"
                                                           class="form-control"
                                                           readonly="">
                                                                        <span class="input-group-btn">
                                                                            <button class="btn default" type="button">
                                                                                <i class="fa fa-calendar"></i>
                                                                            </button>
                                                                        </span>
                                                </div>
                                            </div>
                                            </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                    </c:forEach>
                                </div>
                                </c:forEach>
                        </div>
                        <div class="form-actions">
                            <div class="row">
                                <div class="col-md-offset-10 col-md-2">
                                    <button type="button"
                                            onclick="queryDataFunc('${web_entity.queryUrl}','${web_entity.addUrl}','${web_entity.editUrl}','${web_entity.deleteUrl}')"
                                            class="btn green">提交
                                    </button>
                                </div>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- 查询结果的TABLE -->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet light portlet-fit ">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-settings font-red"></i>
                            <span class="caption-subject font-red sbold uppercase">查询结果</span>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="table-toolbar">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="extra-buttons">
                                            <span class="input-group-btn" id="button_add">
                                                <a onclick="" class="btn green btn-outline sbold" data-toggle="modal">
                                                    <i class="fa fa-plus"></i>新增
                                                </a>
                                            </span>
                                            <span class="input-group-btn" id="button_delete">
                                                <a onclick="" class="btn purple btn-outline sbold">
                                                    <i class="fa fa-times"></i>删除
                                                </a>
                                            </span>
                                            <span class="input-group-btn" id="button_edit">
                                                <a onclick="" class="btn red btn-outline sbold" data-toggle="modal">
                                                    <i class="fa fa-edit"></i>修改
                                                </a>
                                            </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <table class="table table-striped table-hover table-bordered" id="result_table">
                            <thead>
                            <tr>
                                <c:forEach var="column" items="${web_entity.resultTable}">
                                    <th name="${column.name}" <c:if test="${!column.visible}">invisible
                                        hidden</c:if>>${column.title}</th>
                                </c:forEach>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- 隐藏的新增FORM表单 -->
        <div id="form_add_div" class="modal fade" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"></button>
                        <h4 class="modal-title">新增记录</h4>
                    </div>

                    <div class="modal-body col-md-12">
                        <form action="#" id="add_form" class="horizontal-form">
                            <div class="form-body">
                                <c:forEach var="rows" items="${web_entity.addForm}">
                                    <div class="row">
                                        <c:forEach var="column" items="${rows}">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <c:choose>
                                                        <c:when test="${'input_hidden' == column.type}">
                                                            <input id="add_form_${column.name}"
                                                                   name="${column.name}"
                                                                   type="hidden" class="form-control"/>
                                                        </c:when>
                                                        <c:when test="${'input_text' == column.type}">
                                                            <label class="control-label"
                                                                   for="add_form_${column.name}">${column.title}
                                                                <c:if test="${column.required}">
                                                                    <span class="required"> * </span>
                                                                </c:if>
                                                            </label>
                                                            <input id="add_form_${column.name}"
                                                                   name="${column.name}"
                                                                   type="text" class="form-control"/>
                                                        </c:when>
                                                        <c:when test="${'select_one' == column.type}">
                                                            <label class="control-label"
                                                                   for="add_form_${column.name}">${column.title}
                                                                <c:if test="${column.required}">
                                                                    <span class="required"> * </span>
                                                                </c:if>
                                                            </label>
                                                            <select id="add_form_${column.name}"
                                                                    name="${column.name}"
                                                                    class="form-control select2me">
                                                                <c:forEach var="option" items="${column.options}">
                                                                    <option value="${option.key}">${option.value}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </c:when>
                                                        <c:when test="${'textarea' == column.type}">
                                                            <label class="control-label"
                                                                   for="add_form_${column.name}">${column.title}
                                                                <c:if test="${column.required}">
                                                                    <span class="required"> * </span>
                                                                </c:if>
                                                            </label>
                                                                <textarea id="add_form_${column.name}"
                                                                          name="${column.name}"
                                                                          class="form-control"
                                                                          rows="${column.rows}"></textarea>
                                                        </c:when>
                                                        <c:when test="${'datepicker' == column.type}">
                                                            <label class="control-label"
                                                                   for="add_form_${column.name}">${column.title}</label>
                                                            <div class="input-group input-medium date date-picker"
                                                                 data-date-format="yyyy-mm-dd"
                                                                 data-date-viewmode="years">
                                                                <input id="add_form_${column.name}"
                                                                       name="${column.name}"
                                                                       type="text"
                                                                       class="form-control"
                                                                       readonly=""/>
                                                                    <span class="input-group-btn">
                                                                        <button class="btn default" type="button">
                                                                            <i class="fa fa-calendar"></i>
                                                                        </button>
                                                                    </span>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:forEach>

                            </div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn green" id="form_add_btn">保存</button>
                        <button type="button" data-dismiss="modal" class="btn dark btn-outline">关闭</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- 隐藏的编辑FORM表单 -->
        <div id="form_edit_div" class="modal fade" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"></button>
                        <h4 class="modal-title">编辑记录</h4>
                    </div>

                    <div class="modal-body col-md-12">
                        <form action="#" id="edit_form" class="horizontal-form">
                            <div class="form-body">
                                <c:forEach var="rows" items="${web_entity.editForm}">
                                    <div class="row">
                                        <c:forEach var="column" items="${rows}">
                                            <div class="col-md-<fmt:formatNumber type="number" value="${12/fn:length(rows)}" maxFractionDigits="0"/>">
                                                <div class="form-group">
                                                    <c:choose>
                                                        <c:when test="${'input_hidden' == column.type}">
                                                            <input id="edit_form_${column.name}"
                                                                   name="${column.name}"
                                                                   type="hidden" class="form-control"/>
                                                        </c:when>
                                                        <c:when test="${'input_text' == column.type}">
                                                            <label class="control-label"
                                                                   for="edit_form_${column.name}">${column.title}
                                                                <c:if test="${column.required}">
                                                                    <span class="required"> * </span>
                                                                </c:if>
                                                            </label>
                                                            <input id="edit_form_${column.name}"
                                                                   name="${column.name}"
                                                                   type="text" class="form-control"/>
                                                        </c:when>
                                                        <c:when test="${'select_one' == column.type}">
                                                            <label class="control-label"
                                                                   for="edit_form_${column.name}">${column.title}
                                                                <c:if test="${column.required}">
                                                                    <span class="required"> * </span>
                                                                </c:if>
                                                            </label>
                                                            <select id="edit_form_${column.name}"
                                                                    name="${column.name}"
                                                                    class="form-control select2me">
                                                                <c:forEach var="option" items="${column.options}">
                                                                    <option value="${option.key}">${option.value}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </c:when>
                                                        <c:when test="${'textarea' == column.type}">
                                                            <label class="control-label"
                                                                   for="edit_form_${column.name}">${column.title}
                                                                <c:if test="${column.required}">
                                                                    <span class="required"> * </span>
                                                                </c:if>
                                                            </label>
                                                                <textarea id="edit_form_${column.name}"
                                                                          name="${column.name}"
                                                                          class="form-control"
                                                                          rows="${column.rows}"></textarea>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:forEach>
                            </div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn green" id="form_edit_btn">保存</button>
                        <button type="button" data-dismiss="modal" class="btn dark btn-outline">关闭</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- END CONTENT BODY -->
</div>


<!-- BEGIN FOOTER -->
<jsp:include page="../frameworks/footer.jsp"/>
<!-- END FOOTER -->

<jsp:include page="../frameworks/commonjs.jsp"/>
<!-- Add the js files here -->

<!-- 页面使用的plugin代码 -->
<script src="${pageContext.request.contextPath}/assets/global/plugins/datatables/datatables.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
        type="text/javascript"></script>

<!-- 页面使用的js代码 -->
<script src="${pageContext.request.contextPath}/assets/pages/scripts/components-date-time-pickers.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/template/page_a.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/template/page_a-datatable.js"
        type="text/javascript"></script>

<!-- 基础库 -->
<script src="${pageContext.request.contextPath}/assets/js/frameworks/formexchange.js" type="text/javascript"></script>


</body>

</html>