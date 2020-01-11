<%-- 
    Document   : __OneColumnFormEngine.jsp
    Created on : 22/06/2018
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

        <!-- MAIN CONTAINER -->   
        <div id="main" class="container-fluid">

            <br><br><br>

            <div class="row">
                <div class="col-md-6"><h4>${oForm.title}</h4></div>
            </div>

            <!-- FORM MAIN -->
            <form id="${oForm.id}" name="${oForm.id}" method="${oForm.method}" action="${oForm.urlToGo}">

                <c:forEach var="oField" items="${oForm.fields}">

                    <div class="row">
                        <div class="form-group col-md-${oField.divSize}">                            

                            <label for="${oField.id}">${oField.label}:</label>

                            <c:if test="${oField.type == 'input'}">
                                <input type="text" class="form-control" id="${oField.id}" name="${oField.id}" placeholder="${oField.placeholder}" maxlength="${oField.maxSize}" value="${oField.value}" ${oField.required}>                       
                            </c:if> 

                            <c:if test="${oField.type == 'email'}">
                                <input type="email" class="form-control" id="${oField.id}" name="${oField.id}" placeholder="${oField.placeholder}" maxlength="${oField.maxSize}" value="${oField.value}" ${oField.required}>                       
                            </c:if> 

                            <c:if test="${oField.type == 'text'}">
                                <textarea class="form-control" id="${oField.id}" name="${oField.id}" placeholder="${oField.placeholder} (Máx. ${oField.maxSize} caracteres)" rows="${oField.rows}" maxlength="${oField.maxSize}" ${oField.required}>${oField.value}</textarea>                        
                            </c:if>

                            <c:if test="${oField.type == 'number'}">
                                <input type="number" class="form-control" id="${oField.id}" name="${oField.id}" min="0" max="${oField.maxSize}" step="1" value="${oField.value}" ${oField.required}>                       
                            </c:if> 

                            <c:if test="${oField.type == 'date'}">
                                <input type="date" class="form-control" id="${oField.id}" name="${oField.id}" value="${oField.value}" ${oField.required}>                       
                            </c:if>                                 

                            <c:if test="${oField.type == 'combobox'}">
                                <SELECT id="${oField.id}" name="${oField.id}" placeholder="${oField.placeholder}" ${oField.required} size="1" class="form-control" >
                                    <c:forEach var="o" items="${oField.values}">
                                        <option value="${o.id}" ${oField.value == o.id ? 'selected' : ''}>${o.name}</option>  
                                    </c:forEach>                                    
                                </SELECT>
                            </c:if>                                

                        </div>
                    </div>

                </c:forEach>        

                <!-- BUTTON-BAR: BUTTONS MAIN-ACTION AND CANCEL -->
                <div class="row">

                    <div class="col-md-12">

                        <c:forEach var="oButton" items="${oForm.buttons}">

                            <c:if test="${oButton.type == 'submit'}">
                                <button type="submit" class="${oButton.cssClass}">${oButton.label}</button>
                            </c:if>

                            <c:if test="${oButton.type == 'reset'}">
                                <button type="reset" class="${oButton.cssClass}">${oButton.label}</button>
                            </c:if>                                

                            <c:if test="${oButton.type == 'button'}">
                                <a href="${oButton.url}" class="${oButton.cssClass}">${oButton.label}</a>
                            </c:if>       

                        </c:forEach>

                    </div>

                </div><!-- /BUTTON-BAR: BUTTONS MAIN-ACTION AND CANCEL -->

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

            function isNumber(evt) {
                evt = (evt) ? evt : window.event;
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if ((charCode > 31 && charCode < 48) || charCode > 57) {
                    return false;
                }
                return true;
            }


            $(document).ready(function () {

            });
            
        </script><!-- /DATA MODEL & EVENTS FUNCTIONS -->  

    </body><!-- BODY -->        
</html>
