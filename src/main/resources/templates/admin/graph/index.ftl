<html>
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
</head>
<body>
<canvas id="canvas" height="450" width="600"></canvas>
<script type="text/javascript">
    var ctx = new Chart(document.getElementById("canvas").getContext("2d"));
    var options = {scaleFontSize: 13, scaleFontColor: "#ffa45e"};

    // 线型图
    var LineChart = {
        labels: ["Ruby", "jQuery", "Java", "ASP.Net", "PHP"],
        datasets: [{
            fillColor: "rgba(151,249,190,0.5)",
            strokeColor: "rgba(255,255,255,1)",
            pointColor: "rgba(220,220,220,1)",
            pointStrokeColor: "#fff",
            data: [10, 20, 30, 40, 50]
        }, {
            fillColor: "rgba(252,147,65,0.5)",
            strokeColor: "rgba(255,255,255,1)",
            pointColor: "rgba(173,173,173,1)",
            pointStrokeColor: "#fff",
            data: [28, 68, 40, 19, 96]
        }]
    };
    var myLineChart = ctx.Line(LineChart, options);

    // 条形图
    var BarChart = {
        labels: ["Ruby", "jQuery", "Java", "ASP.Net", "PHP"],
        datasets: [{
            fillColor: "rgba(151,249,190,0.5)",
            strokeColor: "rgba(255,255,255,1)",
            data: [13, 20, 30, 40, 50]
        }, {
            fillColor: "rgba(252,147,65,0.5)",
            strokeColor: "rgba(255,255,255,1)",
            data: [28, 68, 40, 19, 96]
        }]
    };
    //var myBarChart = ctx.Bar(BarChart, options);


    // 饼状图
    var pieChart = [
        {value: 40, color: "#fcc79c"},
        {value: 30, color: "#beefd2"},
        {value: 90, color: "#ffddfb"},
    ];
    //var myPieChart = ctx.Pie(pieChart);


    // 环状图
    var doughnutChart = [
        {value: 60, color: "#fcc79e"},
        {value: 30, color: "#beefd2"},
        {value: 50, color: "#ffddfb"},
        {value: 120, color: "#cdecff"},
        {value: 90, color: "#fff5bc"}
    ];
    //var myRingChart = ctx.Doughnut(doughnutChart);
</script>
</body>
</html>