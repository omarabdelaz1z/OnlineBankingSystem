<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Entities.Customer customer =
            (Entities.Customer) session.getAttribute("currentCustomer");

%>

<html>
<head>
    <title>View All Accounts</title>
</head>
<body>
    <table>

        <tr>
            <th>AccountID</th>
            <th>CreationDate</th>
            <th>Amount</th>
        </tr>

        <tr>
            <td> <%= customer.getCustomerAccount().getAccountID() %> </td>
            <td> <%= customer.getCustomerAccount().getCreationDate() %> </td>
            <td> <%= customer.getCustomerAccount().getCurrentBalance() %> </td>
        </tr>

    </table>
</body>
</html>
