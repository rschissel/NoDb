<%-- 
    Document   : index
    Created on : Feb 6, 2017, 1:42:17 PM
    Author     : Ryan Schissel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <title>Book Web App</title>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                   <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                   </button>
                <a class="navbar-brand" href="#">My Incredibly Awesome Book Web App</a>
            </div>
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="actionMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">Action<span class="caret"></span></button>
                <ul class="dropdown-menu" aria-labelledby="actionMenu">
                    <li>Add</li>
                    <li>Edit</li>
                    <li>Delete</li>
                </ul>
            </div>
        </nav>
  <script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> 
        <script src="resources/js/bookwebapp.js" type="text/javascript"></script>
    </body>
</html>
