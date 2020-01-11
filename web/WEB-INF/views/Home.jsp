<%-- 
    Document   : BasicListView
    Created on : 18/06/2018, 17:41:43
    Author     : Fabio Tavares Dippold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Language" content="pt-br">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css"> 
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"> 
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.2/js/buttons.colVis.min.js">       
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">  

        <title>${title}</title>

        <!-- Custom styles for this template -->
        <link href="../../assets/custom/css/menu.css" rel="stylesheet">
    </head> 

    <body>

        <!-- SIMPLE MENU BAR -->            
        <jsp:include page="../includes/MenuInclude.jsp" /> 
        <!-- /SIMPLE MENU BAR -->

        <!--
        <main role="main" class="container">
            <br><br><br>
            <div class="starter-template">
                <h1>Bootstrap starter template</h1>
                <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
            </div>

        </main>
        -->

        <div class="container">
            <br><br><br>
            <div class="card-deck mb-3 text-center">

                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">
                            <i class="fas fa-envelope"></i>&nbsp;Mensagens
                        </h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">3 <small class="text-muted">/ novas</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>10 users included</li>
                            <li>2 GB of storage</li>
                            <li>Email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary">Ver Mensagens</button>
                    </div>
                </div>

                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">
                            <i class="fas fa-tasks"></i>&nbsp;Minhas Tarefas
                        </h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">$15 <small class="text-muted">/ mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>20 users included</li>
                            <li>10 GB of storage</li>
                            <li>Priority email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="btn btn-lg btn-block btn-primary">Administrar</button>
                    </div>
                </div>

                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">
                            <i class="fas fa-project-diagram"></i>&nbsp;Meus Projetos
                        </h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">$29 <small class="text-muted">/ mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>30 users included</li>
                            <li>15 GB of storage</li>
                            <li>Phone and email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="btn btn-lg btn-block btn-primary">Adminitrar</button>
                    </div>
                </div>

                <!--
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">
                            <i class="fas fa-cubes"></i>&nbsp;Itens de Backlog
                        </h4>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-sm">
                                <thead>
                                    <tr>
                                        <th scope="col">Status</th>
                                        <th scope="col">Qt.</th>
                                        <th scope="col">%</th>
                                    </tr>
                                </thead>
                                <tbody>
                <c:forEach var="o" items="${backlogPerStatus}">
                    <tr>
                        <td style="text-align: left;">${o.name}</td>
                        <td>${o.count}</td>
                        <td>${o.percent}</td>
                    </tr>                                       
                </c:forEach>
            </tbody>
            <thead>
                <tr>
                    <th scope="col">Total</th>
                    <th scope="col">${backlogCount}</th>
                    <th scope="col"></th>
                </tr>
            </thead>                                
        </table>
    </div>
    <a class="btn btn-lg btn-block btn-primary" href="mvc?class=BacklogItemCmd&do=0">Administrar</a>
</div>                
                -->

                <!-- PORTLET BACKLOG ITENS -->
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">
                            ${backlogPorlet.icon}</i>&nbsp;${backlogPorlet.title}
                        </h4>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-sm">
                                <thead>
                                    <tr>
                                        <c:forEach var="o" items="${backlogPorlet.headers}">
                                            <th scope="col">${o}</th>
                                        </c:forEach>                                            
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${backlogPorlet.datasource}">
                                        <tr>
                                            <td style="text-align: left;">${o.name}</td>
                                            <td>${o.count}</td>
                                            <td>${o.percent}</td>
                                        </tr>                                       
                                    </c:forEach>
                                </tbody>
                                <thead>
                                    <tr>
                                        <th scope="col" style="text-align: right;">Total</th>
                                        <th scope="col">${backlogPorlet.count}</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>                                
                            </table>
                        </div>
                        <a id="${backlogPorlet.button.id}" class="${backlogPorlet.button.cssClass}" href="${backlogPorlet.button.url}">${backlogPorlet.button.label}</a>
                    </div>                     
                    <!-- /PORTLET BACKLOG ITENS -->

                </div>
            </div>        
        </div>


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script type="text/javascript">window.jQuery || document.write('<script src="assets/core/js/jquery-3.3.1.slim.min.js"><\/script>');</script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
        <script type="text/javascript" defer src="https://use.fontawesome.com/releases/v5.1.0/js/all.js" integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9" crossorigin="anonymous"></script>  

        <script type="text/javascript">

            $(document).ready(function () {


            });

        </script>

    </body>
</html>
