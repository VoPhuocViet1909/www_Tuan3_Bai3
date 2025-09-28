<body>
<div class="container">
    <h2>Cart</h2>

    <c:if test="${empty cart.items}">
        <p>Cart is empty!</p>
    </c:if>

    <c:if test="${not empty cart.items}">
        <table class="table table-border">
            <tr>
                <th>Model</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>

            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>${item.product.model}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/cart"
                              method="post" style="display:inline;">
                            <input type="hidden" name="action" value="update"/>
                            <input type="hidden" name="productId" value="${item.product.id}"/>
                            <input type="number" name="quantity"
                                   value="${item.quantity}" min="1"/>
                            <input type="submit" value="Update"/>
                        </form>
                    </td>
                    <td>${item.product.price}</td>
                    <td>${item.product.price * item.quantity}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/cart"
                              method="post" style="display:inline;">
                            <input type="hidden" name="action" value="remove"/>
                            <input type="hidden" name="productId" value="${item.product.id}"/>
                            <input type="submit" value="Remove"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <p><strong>Total: </strong> ${cart.total}</p>
    </c:if>

    <a href="product">Continue Shopping</a>
</div>
</body>
