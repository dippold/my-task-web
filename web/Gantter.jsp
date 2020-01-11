<%-- 
    Document   : Gantter.jsp
    Created on : 10/07/2018, 10:01:43
    Updated on : 13/06/2018, 13:06:00
    Author     : Fabio Tavares Dippold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Language" content="pt-br">
        <meta name="description" content="MyMoney App">
        <meta name="author" content="Fábio Tavares Dippold">

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">       


        <style type="text/css">
            body {
                font-family: tahoma, verdana, helvetica;
                font-size: 0.8em;
                padding: 10px;
            }
        </style>        

        <title>My Tasks App</title>

        <!-- Custom styles for this template -->
    </head>

    <body>

        <div id="chart_div"></div>


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script type="text/javascript">window.jQuery || document.write('<script src="assets/core/js/jquery-3.3.1.slim.min.js"><\/script>');</script>        
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>        
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

        <script type="text/javascript" defer src="https://use.fontawesome.com/releases/v5.1.0/js/all.js" integrity="sha384-3LK/3kTpDE/Pkp8gTNp2gR/2gOiwQ6QaO7Td0zV76UFJVhqLl4Vl3KL1We6q6wR9" crossorigin="anonymous"></script> 

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <script type="text/javascript">
            
            google.charts.load('current', {'packages': ['gantt']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {

                var data = new google.visualization.DataTable();
                
                data.addColumn('string', 'Task ID');
                data.addColumn('string', 'Task Name');
                data.addColumn('string', 'Resource');
                data.addColumn('date', 'Start Date');
                data.addColumn('date', 'End Date');
                data.addColumn('number', 'Duration');
                data.addColumn('number', 'Percent Complete');
                data.addColumn('string', 'Dependencies');

                data.addRows([
                    ['2014Spring', 'Spring 2014', 'spring',
                        new Date(2014, 2, 22), new Date(2014, 5, 20), null, 100, null],
                    ['2014Summer', 'Summer 2014', 'summer',
                        new Date(2014, 5, 21), new Date(2014, 8, 20), null, 100, '2014Spring'],
                    ['2014Autumn', 'Autumn 2014', 'autumn',
                        new Date(2014, 8, 21), new Date(2014, 11, 20), null, 100, null],
                    ['2014Winter', 'Winter 2014', 'winter',
                        new Date(2014, 11, 21), new Date(2015, 2, 21), null, 100, null],
                    ['2015Spring', 'Spring 2015', 'spring',
                        new Date(2015, 2, 22), new Date(2015, 5, 20), null, 50, null],
                    ['2015Summer', 'Summer 2015', 'summer',
                        new Date(2015, 5, 21), new Date(2015, 8, 20), null, 0, null],
                    ['2015Autumn', 'Autumn 2015', 'autumn',
                        new Date(2015, 8, 21), new Date(2015, 11, 20), null, 0, null],
                    ['2015Winter', 'Winter 2015', 'winter',
                        new Date(2015, 11, 21), new Date(2016, 2, 21), null, 0, null],
                    ['Football', 'Football Season', 'sports',
                        new Date(2014, 8, 4), new Date(2015, 1, 1), null, 100, null],
                    ['Baseball', 'Baseball Season', 'sports',
                        new Date(2015, 2, 31), new Date(2015, 9, 20), null, 14, null],
                    ['Basketball', 'Basketball Season', 'sports',
                        new Date(2014, 9, 28), new Date(2015, 5, 20), null, 86, null],
                    ['Hockey', 'Hockey Season', 'sports',
                        new Date(2014, 9, 8), new Date(2015, 5, 21), null, 89, null]
                ]);

                var options = {
                    height: 400,
                    gantt: {
                        trackHeight: 30,
                        percentEnabled: true,
                        arrow: {
                            angle: 100,
                            width: 3,
                            color: 'blue',
                            radius: 0
                        }
                    }
                };

                var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

                chart.draw(data, options);
            }

            $(document).ready(function () {

            });

        </script>

    </body>
</html>
