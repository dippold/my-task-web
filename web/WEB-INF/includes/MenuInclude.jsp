<%-- 
    Document   : MenuInclude
    Created on : 17/05/2018, 11:31:34
    Author     : Fabio Tavares Dippold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- MenuInclude.jsp -->
<!-- FORM TO SIGNOUT -->
<form id="formSair" name="formSair" method="POST" action="mvc?class=LogOutCmd">
</form><!-- /FORM TO SIGNOUT -->

<!-- DIV MODAL SAIR -->
<div class="modal fade" id="sair-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalLabel">SAIR</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>                
            </div>
            <div class="modal-body">Deseja sair do sistema?</div>
            <div class="modal-footer">
                <button id="btnSair" type="button" class="btn btn-primary" onclick="javascript:$('#formSair').submit();">Sim</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
            </div>
        </div>
    </div>
</div><!-- /DIV MODAL SAIR-->

<!-- DIV MODAL ABOUT -->
<div class="modal fade" id="about-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalLabel">Sobre o sistema</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                </span>&nbsp; Produto: My Task<br>
                </span>&nbsp; Versão: 2.0.1<br>
                </span>&nbsp; Release 2017/07/29<br>
                </span>&nbsp; Autor: Fábio Tavares Dippold<br>
                </span>&nbsp; <a href="mailto:dippold.br@gmail.com">dippold.br@gmail.com</a><br>
                </span>&nbsp; FTD BuilderForce Software Engineer<br>
                </span>&nbsp; Usuário: ${userName}<br>
                </span>&nbsp; Empresa: ${companyId}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div><!-- /DIV MODAL ABOUT-->

<!-- NAV BAR -->
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    
    <a class="navbar-brand" href="mvc?class=HomeCmd">
        <i class="fas fa-hands-helping"></i>&nbsp;${appname}
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">


            <!--
            <li class="nav-item active">
                <a class="nav-link" href="mvc?class=HomeCmd">Home <span class="sr-only">(current)</span></a>
            </li>
            -->

            <!--
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
            -->

            <!-- SUB-MENU TAREFAS -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="dropdownTarefas" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-tasks"></i>&nbsp;Tarefas&nbsp;
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdownTarefas">
                    <a class="dropdown-item" href="mvc?class=TimeSheetCmd&do=2">Apontar Horas</a>
                    <a class="dropdown-item" href="mvc?class=TaskProgressCmd&do=0">Reportar Progresso</a>
                    <c:if test="${ruleId == '1' || ruleId == '2'}">
                        <a class="dropdown-item" href="mvc?class=TaskCmd&do=0">Planejar</a>
                        <a class="dropdown-item" href="mvc?class=TaskTypeCmd&do=0">Tipos</a>
                        <a class="dropdown-item" href="mvc?class=TaskStatusCmd&do=0">Status</a>                                               
                    </c:if>                    
                </div>
            </li><!-- /SUB-MENU TAREFAS -->               

            <!-- SUB-MENU SPRINT -->
            <c:if test="${ruleId == '1' || ruleId == '2'}"> 
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="dropdownSprint" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-people-carry"></i>&nbsp;Sprints&nbsp;
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownSprint">
                        <a class="dropdown-item" href="mvc?class=SprintCmd&do=0">Sprints</a>
                        <a class="dropdown-item" href="mvc?class=SprintResourceCmd&do=0">Recursos</a>
                        <a class="dropdown-item" href="mvc?class=SprintStatusCmd&do=0">Status</a>                      
                    </div>
                </li>
            </c:if><!-- /SUB-MENU SPRINT -->             

            <!-- SUB-MENU PROJETOS -->
            <c:if test="${ruleId == '1' || ruleId == '2'}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="dropdownProjetos" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-project-diagram"></i>&nbsp;Projetos&nbsp;
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownProjetos">                                            
                        <a class="dropdown-item" href="mvc?class=ProjectCmd&do=0">Projetos</a>
                        <a class="dropdown-item" href="mvc?class=ProjectGroupCmd&do=0">Grupos</a>
                        <a class="dropdown-item" href="mvc?class=ProjectPhaseCmd&do=0">Fases</a>
                        <a class="dropdown-item" href="mvc?class=ProjectStatusCmd&do=0">Status</a>
                        <a class="dropdown-item" href="mvc?class=ProjectTypeCmd&do=0">Tipos</a>                         
                    </div>
                </li>
            </c:if><!-- /SUB-MENU PROJETOS -->            

            <!-- SUB-MENU ITENS DE BACKLOG -->
            <c:if test="${ruleId == '1' || ruleId == '2'}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="dropdownBacklog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-cubes"></i>&nbsp;Backlog&nbsp;
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownBacklog">
                        <a class="dropdown-item" href="mvc?class=BacklogItemCmd&do=0">Administrar</a>
                        <a class="dropdown-item" href="mvc?class=CompanyAreaCmd&do=0">Áreas</a>                         
                    </div>
                </li>
            </c:if><!-- /SUB-MENU CADASTROS -->             

            <!-- SUB-MENU STAKEHOLDERS -->
            <c:if test="${ruleId == '1' || ruleId == '2'}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="dropdownStakeholders" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-address-card"></i>&nbsp;Stakeholders&nbsp;
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownStakeholders">
                        <a class="dropdown-item" href="mvc?class=StakeholderCmd&do=0">Administrar</a>
                        <a class="dropdown-item" href="mvc?class=StakeholderGroupCmd&do=0">Grupos</a>                                      
                    </div>
                </li>
            </c:if><!-- /SUB-MENU STAKEHOLDERS -->             

            <!-- SUB-MENU CONFIG -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="dropdownConfig" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-cogs"></i>&nbsp;Config&nbsp;
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdownConfig">
                    <a class="dropdown-item" href="mvc?class=ChangePasswdCmd&do=2">
                        <i class="fas fa-user-edit"></i>&nbsp;Mudar Senha
                    </a>
                    <c:if test="${ruleId == '1' || ruleId == '2'}">
                        <a class="dropdown-item" href="mvc?class=UserCmd&do=0">
                            <i class="fas fa-users-cog"></i>&nbsp;Usuários
                        </a>
                        <a class="dropdown-item" href="mvc?class=ConfigKeyCmd&do=0">
                            <i class="fas fa-key"></i>&nbsp;Chaves
                        </a>
                        <a class="dropdown-item" href="mvc?class=LogCmd&do=0">
                            <i class="fas fa-user-check"></i>&nbsp;Logs
                        </a>
                    </c:if>
                    <a id="linkAbout" href="#" class="dropdown-item" data-toggle="modal" data-target="#about-modal" title="INF. SOBRE O SISTEMA">
                        <i class="fas fa-chalkboard-teacher"></i>&nbsp;Sobre o Sistema</a>
                    </a>
                </div>
            </li><!-- /SUB-MENU CONFIG -->

            <!-- SAIR OPTION -->
            <li class="nav-item">
                <a id="linkSair" href="#" class="nav-link" data-toggle="modal" data-target="#sair-modal" title="SAIR">
                    <i class="fas fa-power-off"></i>&nbsp;Sair
                </a>                
            </li> <!-- /SAIR OPTION -->           

        </ul>

    </div>
</nav><!-- /NAV BAR -->
