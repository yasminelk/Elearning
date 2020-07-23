<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Subscription"%>
<%@page import="entities.Cours"%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Form Examples | Bootstrap Based Admin Template - Material Design</title>
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

        <!-- Sweet Alert Css -->
        <link href="plugins/sweetalert/sweetalert.css" rel="stylesheet" />

        <!-- JQuery DataTable Css -->
        <link href="../../plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.css" rel="stylesheet">

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
                        <li class="active">
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
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">widgets</i>
                                <span>Classes</span>
                            </a>
                            <ul class="ml-menu">
                                <%
                                    if (session.getAttribute("cours") != null) {
                                        List<Cours> list = (List<Cours>) session.getAttribute("cours");
                                        for (Cours c : list) {%>

                                <li>
                                    <a href="javascript:void(0);" class="menu-toggle">
                                        <span><%=c.getCoursName()%></span>
                                    </a>
                                    <ul class="ml-menu">
                                        <li>
                                            <a href="pdfs?coursName=<%=c.getCoursName()%>">Pdfs</a>
                                        </li>

                                        <%if (Boolean.parseBoolean(session.getAttribute("teacher").toString())) {%>
                                        <li>
                                            <a href="exampage?direction=<%=c.getCoursName()%>">Exam</a>
                                        </li>
                                        <li>
                                            <a href="marks?direction=<%=c.getCoursName()%>">Marks</a>
                                        </li>
                                        <%} else {%>
                                        <li>
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
                <div class="block-header">
                    <h2>Subscriptions</h2>
                </div>

                <!-- Vertical Layout | With Floating Label -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>
                                    Subscribe
                                    <!-- <small>With floating label</small> -->
                                </h2>
                            </div>
                            <div class="body">
                                <%if(request.getAttribute("alert")!=null){%>
                                <div class="alert <%=request.getAttribute("alert")%> alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                    <%=request.getAttribute("message")%>
                                </div>
                                <%}%>
                                <form action="subscribe" method="POST">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" id="name" class="form-control" name="coursName" required="">
                                            <label class="form-label">Cours Name</label>
                                        </div>
                                    </div>

                                    <br>
                                    <button type="submit" class="btn bg-indigo waves-effect btn-lg">Subscribe</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>
                                    My Subscriptions
                                </h2>
                            </div>
                            <div class="body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped table-hover js-basic-example dataTable">
                                        <thead>
                                            <tr>
                                                <th>Cours Name</th>
                                                <th>Professor</th>
                                                <th>Accepted</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%  if (request.getAttribute("subscriptions_list") != null) {
                                                    if (!request.getAttribute("subscriptions_list").equals("")) {
                                                        for (Subscription s : (List<Subscription>) request.getAttribute("subscriptions_list")) {
                                                            out.print("<tr>");
                                                            out.print("<td>" + s.getCours().getCoursName() + "</td>");
                                                            out.print("<td>" + s.getCours().getUser().getFirstName() + " " + s.getCours().getUser().getLastName() + "</td>");
                                                            if (s.isValidated()) {
                                                                out.print("<td>Yes</td>");
                                                            } else {
                                                                out.print("<td>Not Validated Yet</td>");
                                                            }
                                                            out.print("</tr>");
                                                        }
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Vertical Layout | With Floating Label -->

            </div>
        </section>

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

        <!-- Jquery DataTable Plugin Js -->
        <script src="plugins/jquery-datatable/jquery.dataTables.js"></script>
        <script src="plugins/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/dataTables.buttons.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/buttons.flash.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/jszip.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/pdfmake.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/vfs_fonts.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/buttons.html5.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/buttons.print.min.js"></script>

        <!-- Custom Js -->
        <script src="js/admin.js"></script>
        <script src="js/pages/tables/jquery-datatable.js"></script>

        <!-- Demo Js -->
        <script src="js/demo.js"></script>


    </body>

</html>
