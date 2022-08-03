<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Random sequences generation service</title>
        <link rel="stylesheet" href="css/main1.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
                      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body class="centered">
        <h2>Random sequences generation service</h2><br/>
        <h4>Set the length of the array to a length from 10 to 100</h4><br/>
        <form action="/index" method="post">
            <div>
                <input type="number" name="length" class="number" placeholder="Enter the length from 10 to 100"
                autocomplete="off" max="100" min="10" value="10" required step="1"/>
            </div></br/>
            <div>
                <input type="submit" name="length" value="SET"/>
            </div>
        </form>
    </body>
</html>