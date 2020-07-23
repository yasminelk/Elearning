<%@page import="entities.Exam"%>
<%@page import="entities.SubQuestion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Question"%>
<%@page import="java.util.List"%>
<%@page import="entities.Cours"%>
<%
    List<Question> questions = (List<Question>) request.getAttribute("questions");
    List<ArrayList<SubQuestion>> subQuestions = (List<ArrayList<SubQuestion>>) request.getAttribute("subQuestions");
    int i = 1;
%>
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
                    <%if(request.getAttribute("questions")!=null || request.getAttribute("subQuestions")!=null){%>
                    <div class="card">
                        <form class="form-horizontal" action="examanswer" method="POST">
                            <div class="header">
                                <div class="form-group">
                                    <div class="col-sm-11">
                                        <h3>
                                            Exam
                                        </h3>
                                    </div>
                                    <div class="col-sm-1">
                                        <h3>
                                            <%=((Exam)request.getAttribute("exam")).getTotalPoints()%> Pts
                                        </h3>
                                    </div>
                                </div>
                            </div>
                            <div class="header">
                                <h2>
                                    Questions
                                </h2>
                            </div>
                            <div class="body">
                                <%
                                    
                                    for(Question q : questions){
                                %>
                                <div class="question_row">
                                    <div class="form-group">
                                        <label for="question" class="col-sm-1 control-label">Question <%=i%> :</label>
                                        <div class="col-sm-11">
                                            <label id="question" class="control-label"><%=q.getText()+" ("+q.getPoints()+" Pts)"%></label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-1"></div>
                                        <input name="<%=q.getId()%>" type="radio" value="<%=subQuestions.get(i-1).get(0).getId()%>" id="radio_1<%=i%>" disabled="true" <%if(subQuestions.get(i-1).get(0).isCorrect()){%>checked=""<%}%>>
                                        <label for="radio_1<%=i%>"><%=subQuestions.get(i-1).get(0).getText()%></label>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-1"></div>
                                        <input name="<%=q.getId()%>" type="radio" value="<%=subQuestions.get(i-1).get(1).getId()%>" id="radio_2<%=i%>" disabled="true" <%if(subQuestions.get(i-1).get(1).isCorrect()){%>checked=""<%}%>>
                                        <label for="radio_2<%=i%>"><%=subQuestions.get(i-1).get(1).getText()%></label>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-1"></div>
                                        <input name="<%=q.getId()%>" type="radio" value="<%=subQuestions.get(i-1).get(2).getId()%>" id="radio_3<%=i%>" disabled="true" <%if(subQuestions.get(i-1).get(2).isCorrect()){%>checked=""<%}%>>
                                        <label for="radio_3<%=i%>"><%=subQuestions.get(i-1).get(2).getText()%></label>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <div class="form-line">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                        i++;
                                    }
                                %>
                            </div>
                        </form>
                    </div>
                    <%} else {%>
                    <div class="card">
                        <div class="header bg-red">
                            <h3>
                                <%=request.getAttribute("title")%>
                            </h3>
                        </div>
                        <div class="body">
                            <%=request.getAttribute("msg")%>
                        </div>
                    </div>
                    <%}%>
                </div>
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

        <!-- Custom Js -->
        <script src="js/admin.js"></script>
        <script src="js/pages/examples/profile.js"></script>

        <!-- Demo Js -->
        <script src="js/demo.js"></script>
    </body>

</html>
