<%
	response.setHeader("Cache-Control", "no-cache, no-store");

	Entities.Customer customer =
			(Entities.Customer) session.getAttribute("currentCustomer");

	if(customer == null){
		response.sendRedirect("login.html");
	}
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer</title>
</head>

<body>

	<div>
		<h1> Welcome, <%= customer.getName() %> </h1>
	</div>
<div>
	<form action = "Account" method="POST">
		<button>Add Account</button>
	</form>
</div>

	<div>
	<form action = "Account" method="GET">
		<button>My Account</button>
	</form>
</div>

	<div>
		<form action = "CustomerSession" method="GET">
			<button> Log Out </button>
		</form>
	</div>

</body>
</html>