<%-- 
    Document   : SimpleListView.jsp
    Created on : 18/06/2018, 10:01:43
    Author     : Fabio Tavares Dippold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Language" content="pt-br">
        <meta name="description" content="">
        <meta name="author" content="Fábio Tavares Dippold">

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css"> 
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"> 
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.2/js/buttons.colVis.min.js">       
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">  
        
        <!-- Custom styles for this template -->
        <link href="../../assets/custom/css/basiclist.css" rel="stylesheet">        

        <title>${title}</title>

    </head> 

    <body>

        <!-- SIMPLE MENU BAR -->            
        <jsp:include page="../includes/MenuInclude.jsp" /> 
        <!-- /SIMPLE MENU BAR -->

        <!-- MAIN CONTAINER -->
        <main role="main" class="container-fluid">
            <br><br><br>

            <h4>
                ${title}
                <a class="btn btn-outline-primary  btn-sm" href="${urlToCreate}" title="ADICIONAR">
                    <span class="fas fa-plus"></span>
                    <!-- Novo -->
                </a>
            </h4>            

            <table id="datagridTable" class="display" style="width:100%">
                <thead>
                    <tr>
                        <c:forEach var="o" items="${headers}">
                            <th>${o}</th>
                            </c:forEach>   
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="o" items="${datasource}">
                        <tr>
                            <td>
                                ${o.name}                              
                            </td>                           

                            <c:if test="${showGroup == true}">
                                <td>
                                    ${o.group}                              
                                </td>
                            </c:if> 

                            <!-- BARRA DE BOTÕES / AÇÕES CRUD -->
                            <td class="actions">
                                <!-- LIST CRUD MENU BAR -->            
                                <jsp:include page="../includes/ListCrudBarInclude.jsp">
                                    <jsp:param name="id" value="${o.id}" />
                                </jsp:include>
                                <!-- /LIST CRUD MENU BAR -->
                            </td>
                            <!-- BARRA DE BOTÕES / AÇÕES CRUD -->

                        </tr>
                    </c:forEach>                    

            </table>            

            <!-- MESSAGE BAR -->
            <jsp:include page="../includes/MessageBarInclude.jsp" /> 
            <!-- /MESSAGE BAR -->  

        </main><!-- /MAIN CONTAINER -->

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

                $('#datagridTable').DataTable({

                    "responsive": true,
                    "paging": true,
                    "ordering": true,
                    "info": true,
                    "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],

                    "language": {
                        "lengthMenu": "_MENU_ registros por pg",
                        "zeroRecords": "Nothing found - sorry",
                        "info": "&nbsp;pg _PAGE_ de _PAGES_",
                        "infoEmpty": "No records available",
                        "infoFiltered": "(filtered from _MAX_ total records)"
                    }

                });

            });

        </script>

    </body>
</html>
