getStations();
getFillStatis();


function getStations() {
    fetch('http://localhost:8090/dashboard/get-statistics/station')
            .then(response => {
                return response.json();
            })
            .then(stations => {
                var s = new Array();
                var fraud = new Array();
                var fill = new Array();
                for (var i = 0; i < stations.length; i++) {
                    s.push(stations[i].name);
                    fraud.push(stations[i].fraudOpsNum);
                    fill.push(stations[i].fillOpsNum);
                }
                chart('fillops-chart', 'Fill Operations Statistics', s, fill, 'Operation');
                chart('fraudops-chart', 'Fraud Operations Statistics', s, fraud, 'Operation');
            })
            .catch(error => {
                if (error.status === 403) {
                    alert('ERROR: You are not authorize!');
                } else {
                    alert('ERROR: Something went error, please try againe;');
                }
                console.log(error);
            });
}

function getFillStatis() {
    fetch('http://localhost:8090/dashboard/get-statistics/fill-ops')
            .then(response => {
                return response.json();
            })
            .then(stations => {
                var s = new Array();
                var avgs = new Array();
                var total = new Array();
                for (var i = 0; i < stations.length; i++) {
                    s.push(stations[i][0]);
                    avgs.push(stations[i][2]);
                    total.push(stations[i][3]);
                }
                chart('ammount-chart', 'Ammounts Average Statistics', s, avgs, 'Average in Litre');
                chart('total-ammount-chart', 'Total Ammounts Statistics', s, total, 'Total in Litre');
            })
            .catch(error => {
                if (error.status === 403) {
                    alert('ERROR: You are not authorize!');
                } else {
                    alert('ERROR: Something went error, please try againe;');
                }
                console.log(error);
            });
}

function chart(id, title, categories, data, seriesName) {
    Highcharts.chart(id, {
        chart: {
            type: 'column'
        },
        title: {
            text: title
        },
        xAxis: {
            categories: categories,
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            labels: {
                overflow: 'justify'
            }
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 80,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
                name: seriesName,
                data: data
            }]
    });
}

