<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>赵志强的网络日志</title>
    <base href="${base}">
    <link rel="shortcut icon" href="img/favicon.ico"/>
    <link rel="bookmark" href="img/favicon.ico"/>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="plugins/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="plugins/admin-lte/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="plugins/admin-lte/css/skins/skin-blue.min.css">

    <link rel="stylesheet" href="plugins/select2/css/select2.css">
    <link rel="stylesheet" href="css/common.css">

    <!-- todo 去了解hack -->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a href="javascript:void(0)" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>Z</b>zq</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Admin Manage</b></span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">

                    <!-- Notifications Menu -->
                    <#--                    <li class="dropdown notifications-menu">-->
                    <#--                        <!-- Menu toggle button &ndash;&gt;-->
                    <#--                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                    <#--                            <i class="fa fa-bell-o"></i>-->
                    <#--                            <span class="label label-warning">10</span>-->
                    <#--                        </a>-->
                    <#--                        <ul class="dropdown-menu">-->
                    <#--                            <li class="header">You have 10 notifications</li>-->
                    <#--                            <li>-->
                    <#--                                <!-- Inner Menu: contains the notifications &ndash;&gt;-->
                    <#--                                <ul class="menu">-->
                    <#--                                    <li><!-- start notification &ndash;&gt;-->
                    <#--                                        <a href="#">-->
                    <#--                                            <i class="fa fa-users text-aqua"></i> 5 new members joined today-->
                    <#--                                        </a>-->
                    <#--                                    </li>-->
                    <#--                                    <!-- end notification &ndash;&gt;-->
                    <#--                                </ul>-->
                    <#--                            </li>-->
                    <#--                            <li class="footer"><a href="#">View all</a></li>-->
                    <#--                        </ul>-->
                    <#--                    </li>-->
                    <!-- Tasks Menu -->
                    <#--                    <li class="dropdown tasks-menu">-->
                    <#--                        <!-- Menu Toggle Button &ndash;&gt;-->
                    <#--                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                    <#--                            <i class="fa fa-flag-o"></i>-->
                    <#--                            <span class="label label-danger">9</span>-->
                    <#--                        </a>-->
                    <#--                        <ul class="dropdown-menu">-->
                    <#--                            <li class="header">You have 9 tasks</li>-->
                    <#--                            <li>-->
                    <#--                                <!-- Inner menu: contains the tasks &ndash;&gt;-->
                    <#--                                <ul class="menu">-->
                    <#--                                    <li><!-- Task item &ndash;&gt;-->
                    <#--                                        <a href="#">-->
                    <#--                                            <!-- Task title and progress text &ndash;&gt;-->
                    <#--                                            <h3>-->
                    <#--                                                Design some buttons-->
                    <#--                                                <small class="pull-right">20%</small>-->
                    <#--                                            </h3>-->
                    <#--                                            <!-- The progress bar &ndash;&gt;-->
                    <#--                                            <div class="progress xs">-->
                    <#--                                                <!-- Change the css width attribute to simulate progress &ndash;&gt;-->
                    <#--                                                <div class="progress-bar progress-bar-aqua" style="width: 20%"-->
                    <#--                                                     role="progressbar"-->
                    <#--                                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">-->
                    <#--                                                    <span class="sr-only">20% Complete</span>-->
                    <#--                                                </div>-->
                    <#--                                            </div>-->
                    <#--                                        </a>-->
                    <#--                                    </li>-->
                    <#--                                    <!-- end task item &ndash;&gt;-->
                    <#--                                </ul>-->
                    <#--                            </li>-->
                    <#--                            <li class="footer">-->
                    <#--                                <a href="#">View all tasks</a>-->
                    <#--                            </li>-->
                    <#--                        </ul>-->
                    <#--                    </li>-->
                    <!-- User Account Menu -->
                    <#--                    <li class="dropdown user user-menu">-->
                    <#--                        <!-- Menu Toggle Button &ndash;&gt;-->
                    <#--                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                    <#--                            <!-- The user image in the navbar&ndash;&gt;-->
                    <#--                            <img src="plugins/admin-lte/img/user2-160x160.jpg" class="user-image" alt="User Image">-->
                    <#--                            <!-- hidden-xs hides the username on small devices so only the image appears. &ndash;&gt;-->
                    <#--                            <span class="hidden-xs">赵志强</span>-->
                    <#--                        </a>-->
                    <#--                        <ul class="dropdown-menu">-->
                    <#--                            <!-- The user image in the menu &ndash;&gt;-->
                    <#--                            <li class="user-header">-->
                    <#--                                <img src="plugins/admin-lte/img/user2-160x160.jpg" class="img-circle"-->
                    <#--                                     alt="User Image">-->

                    <#--                                <p>-->
                    <#--                                    Alexander Pierce - Web Developer-->
                    <#--                                    <small>Member since Nov. 2012</small>-->
                    <#--                                </p>-->
                    <#--                            </li>-->
                    <#--                            <!-- Menu Body &ndash;&gt;-->
                    <#--                            <li class="user-body">-->
                    <#--                                <div class="row">-->
                    <#--                                    <div class="col-xs-4 text-center">-->
                    <#--                                        <a href="#">Followers</a>-->
                    <#--                                    </div>-->
                    <#--                                    <div class="col-xs-4 text-center">-->
                    <#--                                        <a href="#">Sales</a>-->
                    <#--                                    </div>-->
                    <#--                                    <div class="col-xs-4 text-center">-->
                    <#--                                        <a href="#">Friends</a>-->
                    <#--                                    </div>-->
                    <#--                                </div>-->
                    <#--                                <!-- /.row &ndash;&gt;-->
                    <#--                            </li>-->
                    <#--                            <!-- Menu Footer&ndash;&gt;-->
                    <#--                            <li class="user-footer">-->
                    <#--                                <div class="pull-left">-->
                    <#--                                    <a href="#" class="btn btn-default btn-flat">Profile</a>-->
                    <#--                                </div>-->
                    <#--                                <div class="pull-right">-->
                    <#--                                    <a href="admin/logout" class="btn btn-default btn-flat">Sign out</a>-->
                    <#--                                </div>-->
                    <#--                            </li>-->
                    <#--                        </ul>-->
                    <#--                    </li>-->
                    <!-- Control Sidebar Toggle Button -->
                    <#--                    <li>-->
                    <#--                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>-->
                    <#--                    </li>-->
                    <li>
                        <a href="admin/logout" title="退出登录"><i class="fa fa-sign-out"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar user panel (optional) -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="plugins/admin-lte/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>赵志强</p>
                    <!-- Status -->
                    <a href="javascript:void(0)"><i class="fa fa-circle text-success"></i> 在线</a>
                </div>
            </div>

            <!-- search form (Optional) -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
                </div>
            </form>
            <!-- /.search form -->

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">HEADER</li>
                <!-- Optionally, you can add icons to the links -->
                <li class="treeview">
                    <a href="#" id="article-manage-link">
                        <i class="fa fa-book"></i>
                        <span>文章管理</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                    </a>
                    <ul class="treeview-menu" style="display: none;">
                        <li><a href="admin/article/add"><i class="fa fa-circle-o"></i> 新增文章</a></li>
                        <li><a href="admin/article/manage"><i class="fa fa-circle-o"></i> 管理文章</a></li>
                    </ul>
                </li>
                <li><a href="admin/tag/add"><i class="fa fa-book"></i> <span>标签管理</span></a></li>
                <li><a href="#"><i class="fa fa-pie-chart"></i> <span>网站统计</span></a></li>

            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <@block name="content">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    Page Header
                    <small>Optional description</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                    <li class="active">Here</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content container-fluid">

                <!--------------------------
                  | Your Page Content Here |
                  -------------------------->

            </section>
            <!-- /.content -->
        </@block>
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <#--    <footer class="main-footer">-->
    <#--        <!-- To the right &ndash;&gt;-->
    <#--        <div class="pull-right hidden-xs">-->
    <#--        Anything you want-->
    <#--        </div>-->
    <#--        <!-- Default to the left &ndash;&gt;-->
    <#--        <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.-->
    <#--    </footer>-->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
            <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- Home tab content -->
            <div class="tab-pane active" id="control-sidebar-home-tab">
                <h3 class="control-sidebar-heading">Recent Activity</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript:;">
                            <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                            <div class="menu-info">
                                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                <p>Will be 23 on April 24th</p>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

                <h3 class="control-sidebar-heading">Tasks Progress</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript:;">
                            <h4 class="control-sidebar-subheading">
                                Custom Template Design
                                <span class="pull-right-container">
                    <span class="label label-danger pull-right">70%</span>
                  </span>
                            </h4>

                            <div class="progress progress-xxs">
                                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

            </div>
            <!-- /.tab-pane -->
            <!-- Stats tab content -->
            <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
            <!-- /.tab-pane -->
            <!-- Settings tab content -->
            <div class="tab-pane" id="control-sidebar-settings-tab">
                <form method="post">
                    <h3 class="control-sidebar-heading">General Settings</h3>

                    <div class="form-group">
                        <label class="control-sidebar-subheading">
                            Report panel usage
                            <input type="checkbox" class="pull-right" checked>
                        </label>

                        <p>
                            Some information about this general settings option
                        </p>
                    </div>
                    <!-- /.form-group -->
                </form>
            </div>
            <!-- /.tab-pane -->
        </div>
    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="plugins/admin-lte/js/adminlte.js"></script>
<#--<script src="plugins/admin-lte/js/adminlte.min.js"></script>-->
<script src="js/showdown.js"></script>
<script src="plugins/select2/js/select2.full.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
<@block name="js">

</@block>
</body>
</html>