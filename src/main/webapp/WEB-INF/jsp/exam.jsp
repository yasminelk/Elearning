<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Cours"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Profile | Bootstrap Based Admin Template - Material Design</title>
        <!-- Favicon-->
        <link rel="icon" href="../../favicon.ico" type="image/x-icon">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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

        <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
        <link href="css/themes/all-themes.css" rel="stylesheet" />
    </head>

    <body class="theme-indigo">
        <!-- Page Loader -->
        <div class="page-loader-wrapper">
            <div class="loader">
                <div class="preloader">
                    <div class="spinner-layer pl-red">
                        <div class="circle-clipper left">
                            <div class="circle"></div>
                        </div>
                        <div class="circle-clipper right">
                            <div class="circle"></div>
                        </div>
                    </div>
                </div>
                <p>Please wait...</p>
            </div>
        </div>
        <!-- #END# Page Loader -->
        <!-- Overlay For Sidebars -->
        <div class="overlay"></div>
        <!-- #END# Overlay For Sidebars -->
        <!-- Search Bar -->
        <div class="search-bar">
            <div class="search-icon">
                <i class="material-icons">search</i>
            </div>
            <input type="text" placeholder="START TYPING...">
            <div class="close-search">
                <i class="material-icons">close</i>
            </div>
        </div>
        <!-- #END# Search Bar -->
        <!-- Top Bar -->
        <nav class="navbar">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
                    <a href="javascript:void(0);" class="bars"></a>
                    <a class="navbar-brand">E-Learning</a>
                </div>

            </div>
        </nav>
        <!-- #Top Bar -->
        <section>
            <!-- Left Sidebar -->
            <aside id="leftsidebar" class="sidebar">
                <!-- User Info -->
                <div class="user-info">
                    <div class="image">
                        <img src="images/user.png" width="48" height="48" alt="User" />
                    </div>
                    <div class="info-container">
                        <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("firstname") + " " + session.getAttribute("lastname")%></div>
                        <div class="email"><%=session.getAttribute("email")%></div>
                        <div class="btn-group user-helper-dropdown">
                            <i class="material-icons" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
                            <ul class="dropdown-menu pull-right">
                                <li><a href="logout"><i class="material-icons">input</i>Sign Out</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- #User Info -->
                <!-- Menu -->
                <div class="menu">
                    <ul class="list">
                        <li class="header">MAIN NAVIGATION</li>
                        <li>
                            <a href="home">
                                <i class="material-icons">home</i>
                                <span>Home</span>
                            </a>
                        </li>
                        <% if (Boolean.parseBoolean(session.getAttribute("teacher").toString())) {%>
                        <li>
                            <a href="coursProfessor">
                                <i class="material-icons">assignment</i>
                                <span>Cours</span>
                            </a>
                        </li>
                        <%}%>
                        <% if (!Boolean.parseBoolean(session.getAttribute("teacher").toString())) {%>
                        <li>
                            <a href="subscriptions">
                                <i class="material-icons">subscriptions</i>
                                <span>Subscriptions</span>
                            </a>
                        </li>
                        <%} else {%>
                        <li>
                            <a href="subscriptionsProfessor">
                                <i class="material-icons">subscriptions</i>
                                <span>Subscriptions</span>
                            </a>
                        </li>
                        <%}%>
                        <li class="active">
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">widgets</i>
                                <span>Classes</span>
                            </a>
                            <ul class="ml-menu">
                                <%
                                    if (session.getAttribute("cours") != null) {
                                        List<Cours> list = (List<Cours>) session.getAttribute("cours");
                                        for (Cours c : list) {%>

                                        <li <%if(request.getAttribute("coursName").equals(c.getCoursName())){%>
                                        class="active"
                                        <%}%>>
                                    <a href="javascript:void(0);" class="menu-toggle">
                                        <span><%=c.getCoursName()%></span>
                                    </a>
                                    <ul class="ml-menu">
                                        <li>
                                            <a href="pdfs?coursName=<%=c.getCoursName()%>">Pdfs</a>
                                        </li>

                                        <%if (Boolean.parseBoolean(session.getAttribute("teacher").toString())) {%>
                                        <li <%if(request.getAttribute("coursName").equals(c.getCoursName())){%>
                                        class="active"
                                        <%}%>>
                                            <a href="exampage?direction=<%=c.getCoursName()%>">Exam</a>
                                        </li>
                                        <li>
                                            <a href="marks?direction=<%=c.getCoursName()%>">Marks</a>
                                        </li>
                                        <%} else {%>
                                        <li <%if(request.getAttribute("coursName").equals(c.getCoursName())){%>
                                        class="active"
                                        <%}%>>
                                            <a href="exampass?coursName=<%=c.getCoursName()%>">Exam</a>
                                        </li>
                                        <%}%>
                                    </ul>
                                </li>

                                <%      }
                                    }
                                %>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- #Menu -->
            </aside>
            <!-- #END# Left Sidebar -->
        </section>

        <section class="content">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="card">
                        <form class="form-horizontal" action="exam" method="POST">
                            <div class="header">
                                <h3>
                                    Exam
                                </h3>
                            </div>
                            <div class="body">
                                <input type="hidden" value="<%=request.getAttribute("coursName")%>" name="coursName" />
                                <div class="form-group">
                                    <label for="duration" class="col-sm-1 control-label">Duration</label>
                                    <div class="col-sm-10">
                                        <div class="form-line">
                                            <input type="time" class="form-control" id="duration" name="duration" placeholder="Duration" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="startdate" class="col-sm-1 control-label">Start Date</label>
                                    <div class="col-sm-10">
                                        <div class="form-line">
                                            <input type="date" class="form-control" id="startdate"  name="startdate" placeholder="Start Date" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="starttime" class="col-sm-1 control-label">Start Time</label>
                                    <div class="col-sm-10">
                                        <div class="form-line">
                                            <input type="time" class="form-control" id="starttime" name="starttime" placeholder="Start Time" required>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="header">
                                <h2>
                                    Questions
                                </h2>
                            </div>
                            <div class="body">
                                <div class="expandable">
                                    <div class="form-group">
                                        <label for="question" class="col-sm-1 control-label">Question 1</label>
                                        <div class="col-sm-10">
                                            <div class="form-line">
                                                <input type="text" class="form-control" id="question" name="questions" placeholder="Question 1" required>
                                            </div>
                                        </div>
                                        <div class="col-sm-1">
                                            <div class="form-line">
                                                <input type="number" class="form-control" id="points" name="points" placeholder="Points" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="answer00" class="col-sm-1 control-label">Answer 1</label>
                                        <div class="col-sm-10">
                                            <div class="form-line">
                                                <input type="text" class="form-control" id="answer00" name="answers0" placeholder="Answer 1" required>
                                            </div>
                                        </div>
                                        <div class="col-sm-1">
                                            <input name="answer0" type="radio" id="canswer00" value="0" checked required>
                                            <label for="canswer00">Answer 1</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="answer01" class="col-sm-1 control-label">Answer 2</label>
                                        <div class="col-sm-10">
                                            <div class="form-line">
                                                <input type="text" class="form-control" id="answer01" name="answers0" placeholder="Answer 2" required>
                                            </div>
                                        </div>
                                        <div class="col-sm-1">
                                            <input name="answer0" type="radio" id="canswer01" value="1" checked required>
                                            <label for="canswer01">Answer 2</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="answer02" class="col-sm-1 control-label">Answer 3</label>
                                        <div class="col-sm-10">
                                            <div class="form-line">
                                                <input type="text" class="form-control" id="answer02" name="answers0" placeholder="Answer 3" required>
                                            </div>
                                        </div>
                                        <div class="col-sm-1">
                                            <input name="answer0" type="radio" id="canswer02" value="2" checked required>
                                            <label for="canswer02">Answer 3</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <div class="form-line">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-1">
                                    <button type="button" class="btn bg-blue-grey waves-effect add_field_button ">
                                        <i class="material-icons">add</i>
                                        <span>Add Field</span>
                                    </button></div>
                                <button type="submit" class="btn bg-green waves-effect">
                                    <i class="material-icons">save</i>
                                    <span>SAVE</span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>

        <script type="text/javascript">
            jQuery(document).ready(function () {
                var x = 1;
                var wrapper = $(".expandable"); //Fields wrapper
                var add_button = $(".add_field_button"); //Add button ID
                $(add_button).click(function (e) { //on add input button click
                    e.preventDefault();
                    $(wrapper).append('<div class="form-group"><label for="question' + x + '" class="col-sm-1 control-label">Question ' + (x + 1) + '</label><div class="col-sm-10"><div class="form-line"><input type="text" class="form-control" id="question' + x + '" name="questions" placeholder="Question ' + (x + 1) + '" required></div></div><div class="col-sm-1"><div class="form-line"><input type="number" class="form-control" id="points" name="points" placeholder="Points" required></div></div></div><div class="form-group"><label for="answer' + x + '0" class="col-sm-1 control-label">Answer 1</label><div class="col-sm-10"><div class="form-line"><input type="text" class="form-control" id="answer' + x + '0" name="answers' + x + '" placeholder="Answer 1" required></div></div><div class="col-sm-1"><input name="answer' + x + '" type="radio" id="canswer' + x + '0" value="0" checked required><label for="canswer' + x + '0">Answer 1</label></div></div><div class="form-group"><label for="answer' + x + '1" class="col-sm-1 control-label">Answer 2</label><div class="col-sm-10"><div class="form-line"><input type="text" class="form-control" id="answer' + x + '1" name="answers' + x + '" placeholder="Answer 2" required></div></div><div class="col-sm-1"><input name="answer' + x + '" type="radio" id="canswer' + x + '1" value="1" checked required><label for="canswer' + x + '1">Answer 2</label></div></div><div class="form-group"><label for="answer' + x + '2" class="col-sm-1 control-label">Answer 3</label><div class="col-sm-10"><div class="form-line"><input type="text" class="form-control" id="answer' + x + '2" name="answers' + x + '" placeholder="Answer 3" required></div></div><div class="col-sm-1"><input name="answer' + x + '" type="radio" id="canswer' + x + '2" value="2" checked required><label for="canswer' + x + '2">Answer 3</label></div></div><div class="form-group"><div class="col-sm-12"><div class="form-line"></div></div></div>'); //add input box
                    x++;
                });
            });
        </script>

        <!-- Jquery Core Js -->
        <script src="plugins/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core Js -->
        <script src="plugins/bootstrap/js/bootstrap.js"></script>

        <!-- Select Plugin Js -->
        <script src="plugins/bootstrap-select/js/bootstrap-select.js"></script>

        <!-- Slimscroll Plugin Js -->
        <script src="plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="plugins/node-waves/waves.js"></script>

        <!-- Custom Js -->
        <script src="js/admin.js"></script>
        <script src="js/pages/examples/profile.js"></script>

        <!-- Demo Js -->
        <script src="js/demo.js"></script>
    </body>

</html>
