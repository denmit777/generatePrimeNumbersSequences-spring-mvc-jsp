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
        <h4>Random sequences table</h4>
        <table>

            <tr align="center">
                <th>№</th>
                <th>Sequence</th>
            </tr>

            <c:forEach var="entry" items="${sequences}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>

            <c:forEach var="entry" items="${genSequences}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>

            <c:forEach var="entry" items="${autoSequences}">
                 <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>

        </table><br/>
        <form action="/get2Variants" method="post">
            <div>
                <input type="submit" name="generate" value="GENERATE"/>
                <input type="submit" name="generate" value="AUTO"/>
            </div><br/>
            <div>
                <input type="submit" name="generate" value="GET BACK" class="back"/>
            </div>
        </form>
    </body>
</html>