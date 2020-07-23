<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Sign Up | Bootstrap Based Admin Template - Material Design</title>
        <!-- Favicon-->
        <link rel="icon" href="favicon.ico" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="plugins/animate-css/animate.css" rel="stylesheet" />

        <!-- Custom Css -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body class="signup-page">
        <div class="signup-box">
            <div class="logo">
                <a href="javascript:void(0);">Elearning</a>
                <small>University of algiers 1</small>
            </div>
            <div class="card">
                <div class="body">
                    <form id="sign_up" action="sign">
                        <div class="msg">Register a new membership</div>
                        <%if (request.getAttribute("alert") != null) {%>
                        <div class="alert <%=request.getAttribute("alert")%> alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <%=request.getAttribute("message")%>
                        </div>
                        <%}%>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">person</i>
                            </span>
                            <div class="form-line <%if (request.getAttribute("us") != null) {%>focused error<%}%>">
                                <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">email</i>
                            </span>
                            <div class="form-line <%if (request.getAttribute("em") != null) {%>focused error<%}%>">
                                <input type="email" class="form-control email" name="email" placeholder="Email Address" required>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">lock</i>
                            </span>
                            <div class="form-line <%if (request.getAttribute("ps") != null) {%>focused error<%}%>">
                                <input type="password" class="form-control" name="password" minlength="6" placeholder="Password" required>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">lock</i>
                            </span>
                            <div class="form-line <%if (request.getAttribute("ps") != null) {%>focused error<%}%>">
                                <input type="password" class="form-control" name="confirm" minlength="6" placeholder="Confirm Password" required>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">person_outline</i>
                            </span>
                            <div class="form-line">
                                <input type="text" class="form-control" name="firstName" placeholder="First Name" required>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">person_outline</i>
                            </span>
                            <div class="form-line">
                                <input type="text" class="form-control" name="lastName" placeholder="Last Name" required>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">place</i>
                            </span>
                            <div class="form-line">
                                <input type="text" class="form-control" name="adress" minlength="6" placeholder="Adress" required>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">smartphone</i>
                            </span>
                            <div class="form-line">
                                <input type="text" class="form-control" name="mobile" minlength="6" placeholder="Phone Number" required>
                            </div>
                        </div>
                        <div class="input-group">
                            <input type="checkbox" id="professor" class="filled-in chk-col-pink" name="professor">
                            <label for="professor">I am a Professor</label>
                        </div>

                        <button class="btn btn-block btn-lg bg-pink waves-effect" type="submit">SIGN UP</button>

                        <div class="m-t-25 m-b--5 align-center">
                            <a href="home">You already have a membership?</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Jquery Core Js -->
        <script src="plugins/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core Js -->
        <script src="plugins/bootstrap/js/bootstrap.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="plugins/node-waves/waves.js"></script>

        <!-- Validation Plugin Js -->
        <script src="plugins/jquery-validation/jquery.validate.js"></script>

        <!-- Custom Js -->
        <script src=".s/admin.js"></script>
        <script src="js/pages/examples/sign-up.js"></script>
    </body>

</html>