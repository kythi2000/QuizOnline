<%-- 
    Document   : login.jsp
    Created on : Feb 7, 2021, 10:11:33 AM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <title>Login Page</title>
        <style>
            body {
                background-image: url('images/login.jpg') ; 
                background-repeat: no-repeat; 
                background-size: 100% 100%; 
                background-attachment:  fixed;
                width : 300px; 
                margin: auto
            }
        </style>
    </head>
    <body>
        <h1 style="text-align: center; color: pink">Login</h1>
        <form action="login" method="POST">
            <div class="mb-3">
                <label class="form-label"></label>
                <input class="form-control"  name="txtEmail" value="" placeholder="Email">
            </div>
            <c:if test = "${not empty requestScope.ERROR}">
                <font color="red">
                ${requestScope.ERROR}
                </font><br>
            </c:if>
            <div class="mb-3">
                <label class="form-label"></label>
                <input type="password" class="form-control" name="txtPassword" value="" placeholder="Password">
            </div>
            <input type="submit" value="Login" name="btnAction" />
        </form>
        <a href="signUpPage">Sign Up</a>

        <script>

            function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
                console.log('statusChangeCallback');
                console.log(response);                   // The current login status of the person.
                if (response.status === 'connected') {   // Logged into your webpage and Facebook.
                    testAPI();
                } else {                                 // Not logged into your webpage or we are unable to tell.
                    document.getElementById('status').innerHTML = 'Please log ' +
                            'into this webpage.';
                }
            }


            function checkLoginState() {               // Called when a person is finished with the Login Button.
                FB.getLoginStatus(function (response) {   // See the onlogin handler
                    statusChangeCallback(response);
                });
            }


            window.fbAsyncInit = function () {
                FB.init({
                    appId: '{app-id}',
                    cookie: true, // Enable cookies to allow the server to access the session.
                    xfbml: true, // Parse social plugins on this webpage.
                    version: '{api-version}'           // Use this Graph API version for this call.
                });


                FB.getLoginStatus(function (response) {   // Called after the JS SDK has been initialized.
                    statusChangeCallback(response);        // Returns the login status.
                });
            };

            function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
                console.log('Welcome!  Fetching your information.... ');
                FB.api('/me', function (response) {
                    console.log('Successful login for: ' + response.name);
                    document.getElementById('status').innerHTML =
                            'Thanks for logging in, ' + response.name + '!';
                });
            }

        </script>


        <!-- The JS SDK Login Button -->

    <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
    </fb:login-button>

    <div id="status">
    </div>

    <!-- Load the JS SDK asynchronously -->
    <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>
</body>
</html>
