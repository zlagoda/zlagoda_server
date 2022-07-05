<%@ tag language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="sidebar sidebar_flex">
  <div>
    <a class="sidebar__item" href="/products">Products</a>
    <a class="sidebar__item" href="/products-in-store">Products in store</a>
    <a class="sidebar__item" href="/customers">Customers</a>
    <sec:authorize access="hasRole('MANAGER')">
      <a class="sidebar__item" href="/employees">Employees</a>
      <a class="sidebar__item" href="/manager/categories">Categories</a>
      <a class="sidebar__item" href="/check">Checks</a>
    </sec:authorize>
    <sec:authorize access="hasRole('CASHIER')">
      <a class="sidebar__item" href="/cashier/cart">Cart</a>
    </sec:authorize>
    <a class="sidebar__item" href="/me">About Me</a>
  </div>
  <a class="sidebar__item sidebar__logout" href="/logout">Log out </a>
</nav>
