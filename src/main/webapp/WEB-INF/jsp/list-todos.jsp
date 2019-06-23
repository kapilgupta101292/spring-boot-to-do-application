<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<div class="container">
    <h1>Your Todos</h1>
    <table class="table table-striped">
        <caption>Your todos are</caption>
        <thead>
        <tr>
            <th>Description</th>
            <th>Date</th>
            <th>Completed?</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todos}" var="todo">
            <tr>
                <th>${todo.desc}</th>
                <th><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></th>
                <th>${todo.done}</th>
                <th><a type="button" class="btn btn-success" href="/update-todo?id=${todo.id}">UPDATE</a></th>
                <th><a type="button" class="btn btn-warning" href="/delete-todo?id=${todo.id}">DELETE</a></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a class="button" href="/add-todo">Add a Todo</a>
        Your Name : ${name}
    </div>
</div>
<%@include file="common/footer.jspf"%>