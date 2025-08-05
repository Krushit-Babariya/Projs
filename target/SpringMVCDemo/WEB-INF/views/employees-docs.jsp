<%@ include file="header.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/employee-docs.js"></script>

<div class="modal fade" id="docModal" tabindex="-1" aria-labelledby="docModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="docModalLabel">Document Preview</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="docModalBody" style="text-align:center;">
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-5">
        <h3>Documents for Employee ID: ${employeeId}</h3>

        <form:form class="form w-100 align-center border border-dark p-3" action="${pageContext.request.contextPath}/employee-docs-api/add" method="post" enctype="multipart/form-data" modelAttribute="ed">
            <input type="hidden" name="employeeId" value="${employeeId}" />

            Photo: <input type="file" class="form-control" name="photoPath" required><br>
            Resume: <input type="file" class="form-control" name="resumePath" required><br>

            <button type="submit" class="btn btn-secondary w-100">Upload</button>
        </form:form></div>

    <div class="col-7">
        <table border="1" class="table w-100">
            <tr>
                <th>Photo</th>
                <th>Resume</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${docs}" var="doc">
                <tr>
                    <td><a class="btn btn-sm btn-outline-primary mb-1 viewDoc"
                           href="#" 
                           data-doc-url="${pageContext.request.contextPath}${doc.photoPath}" 
                           data-doc-type="image" 
                           data-bs-toggle="modal" 
                           data-bs-target="#docModal">View</a>
                        <a class="btn btn-sm btn-outline-success"
                           href="${pageContext.request.contextPath}/employee-docs-api/download?jsId=${doc.employeeId}&type=photo">Download</a>
                    </td>
                    <td><a class="btn btn-sm btn-outline-primary mb-1 viewDoc"
                           href="#" 
                           data-doc-url="${pageContext.request.contextPath}${doc.resumePath}" 
                           data-doc-type="image" 
                           data-bs-toggle="modal" 
                           data-bs-target="#docModal">View</a>
                        <a class="btn btn-sm btn-outline-success"
                           href="${pageContext.request.contextPath}/employee-docs-api/download?jsId=${doc.employeeId}&type=resume">Download</a>
                    </td>
                    <td>
                        <a class="btn btn-sm btn-danger"
                           href="${pageContext.request.contextPath}/employee-docs-api/delete/${doc.id}?employeeId=${employeeId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/api/employees" class="btn btn-secondary w-100">Back</a>
    </div>
</div>
<!--
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>-->

<%@ include file="footer.jsp" %>
