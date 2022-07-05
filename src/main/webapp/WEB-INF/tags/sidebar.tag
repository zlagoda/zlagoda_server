<%@ tag language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="sidebar sidebar_flex">
  <div>
    <a class="sidebar__item" href="/">Home</a>
    <a class="sidebar__item" href="/products">Products</a>
    <a class="sidebar__item" href="/products-in-store">Products in store</a>
    <sec:authorize access="hasRole('MANAGER')">
      <a class="sidebar__item" href="/employees">Employees</a>
      <a class="sidebar__item" href="/manager/categories">Categories</a>
    </sec:authorize>
    <a class="sidebar__item" href="/customers">Customers</a>
    <a class="sidebar__item" href="/cashier/cart">Cart</a>
    <a class="sidebar__item" href="/checks">Checks</a>
    <a class="sidebar__item" href="/me">About Me</a>
  </div>
  <a class="sidebar__item sidebar__logout" href="/logout">Log out </a>
</nav>
