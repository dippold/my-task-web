<%-- 
    Document   : BaseReadDeleteView.jsp
    Created on : 19/06/2018
    Author     : Fabio Tavares Dippold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <!-- HEAD -->
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
    </head><!-- /HEAD -->

    <body>

        <!-- SIMPLE MENU BAR -->            
        <jsp:include page="../includes/MenuInclude.jsp" /> 
        <!-- /SIMPLE MENU BAR -->        

        <!-- DIV MODAL -->
        <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">                        
                        <h4 class="modal-title" id="modalLabel">
                            Excluir&nbsp;(${o.id})&nbsp;&nbsp;${o.name}</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>                            
                    </div>
                    <div class="modal-body">
                        Deseja realmente excluir este item?
                    </div>
                    <div class="modal-footer">
                        <button id="btnDeleteYes" type="button" class="btn btn-primary">Sim</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
                    </div>
                </div>
            </div>
        </div><!-- /DIV MODAL -->          

        <!-- MAIN CONTAINER -->   
        <div id="main" class="container-fluid">

            <br><br><br>

            <div class="row">
                <div class="col-md-6"><h3>${title}</h3></div>
            </div>

            <!-- FORM MAIN -->
            <form id="formDelete" name="formDelete" method="POST" action="${urlToGo}">

                <!-- LINHA-1 -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <!--
                        <label for="${descriptionFieldLabel}">${descriptionFieldLabel}:</label>
                        -->
                        <textarea class="form-control" id="${descriptionFieldId}" name="${descriptionFieldId}" required="${descriptionFieldRequired}" placeholder="${descriptionFieldPlaceHolder}" rows="${descriptionFieldRows}" maxlength="${descriptionFieldMaxSize}" readonly>${descriptionFieldValue}</textarea>  
                    </div>
                </div><!-- /LINHA-1 -->                     


                <!-- LINHA-FINAL : BUTTONS DELETE AND CANCEL -->
                <div class="row">

                    <div class="col-md-6">
                        <!--
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        -->
                        <a href="#" class="btn btn-outline-danger" data-toggle="modal" data-target="#delete-modal">Excluir</a>
                        <a href="${urlToGoBack}" class="btn btn-outline-success">Cancelar</a>
                    </div>

                </div><!-- /LINHA-3 -->

                <br><br>

            </form><!-- /FORM MAIN -->

            <!-- MESSAGE BAR -->
            <jsp:include page="../includes/MessageBarInclude.jsp" /> 
            <!-- /MESSAGE BAR -->            

        </div> <!--/MAIN CONTAINER -->        

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

                $("#btnDeleteYes").click(function () {
                    $("#formDelete").submit();
                });

            });
        </script><!-- /DATA MODEL & EVENTS FUNCTIONS -->  

    </body><!-- BODY -->        
</html>
