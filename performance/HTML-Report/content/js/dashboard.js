/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 95.51633986928104, "KoPercent": 4.483660130718954};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.28921568627450983, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.36941176470588233, 500, 1500, "TC-012 Customer - Add Details"], "isController": false}, {"data": [0.09529411764705882, 500, 1500, "Checkout"], "isController": true}, {"data": [0.36941176470588233, 500, 1500, "TC-020 Shipping - Set Address"], "isController": false}, {"data": [0.2611764705882353, 500, 1500, "Payment - Set Method"], "isController": false}, {"data": [0.2888235294117647, 500, 1500, "TC-001 Login"], "isController": false}, {"data": [0.3488235294117647, 500, 1500, "TC-004 Cart - Add Product"], "isController": false}, {"data": [0.26941176470588235, 500, 1500, "Payment - Set Address"], "isController": false}, {"data": [0.2888235294117647, 500, 1500, "Login"], "isController": true}, {"data": [0.3211764705882353, 500, 1500, "Shipping - Set Method"], "isController": false}, {"data": [0.2188235294117647, 500, 1500, "TC-014 Order - Place Order"], "isController": false}, {"data": [0.27647058823529413, 500, 1500, "Cart"], "isController": true}, {"data": [0.3629411764705882, 500, 1500, "TC-007 Cart - View Products"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 7650, 343, 4.483660130718954, 3871.0253594771225, 28, 20065, 3534.5, 7726.500000000025, 18328.19999999998, 20005.0, 27.486544168900323, 23.384310084372913, 7.306716850194742], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["TC-012 Customer - Add Details", 850, 38, 4.470588235294118, 3544.8905882352974, 32, 20010, 2171.0, 7526.999999999997, 15225.999999999993, 20004.49, 3.5394250308138178, 2.9446113164787304, 1.1157809689280123], "isController": false}, {"data": ["Checkout", 850, 113, 13.294117647058824, 21119.585882352956, 291, 66952, 24521.0, 43768.4, 51559.84999999999, 65637.35, 3.2724780743969015, 16.63190360530834, 5.648562594083744], "isController": true}, {"data": ["TC-020 Shipping - Set Address", 850, 39, 4.588235294117647, 3734.0729411764723, 33, 20013, 3007.0, 8010.299999999999, 17443.49999999998, 20004.49, 3.473953522588872, 2.8610864968448326, 1.1620382515489747], "isController": false}, {"data": ["Payment - Set Method", 850, 15, 1.7647058823529411, 3290.421176470585, 31, 20013, 4072.5, 4316.7, 6843.449999999997, 20003.0, 3.29551886975334, 2.781366433589479, 0.8283095854043409], "isController": false}, {"data": ["TC-001 Login", 850, 78, 9.176470588235293, 5485.648235294116, 48, 20065, 4070.0, 19377.1, 20003.0, 20011.49, 3.759348615454019, 3.425814403446659, 0.8435902276838432], "isController": false}, {"data": ["TC-004 Cart - Add Product", 850, 57, 6.705882352941177, 4456.970588235299, 31, 20051, 2706.5, 15676.799999999996, 20002.0, 20007.49, 3.683432786018556, 3.0650866635899168, 0.8792418384879724], "isController": false}, {"data": ["Payment - Set Address", 850, 17, 2.0, 3360.6517647058804, 28, 20009, 4063.5, 4347.099999999999, 11649.699999999975, 20004.49, 3.3473132888337567, 2.8520347542185993, 1.146847064701597], "isController": false}, {"data": ["Login", 850, 78, 9.176470588235293, 5485.6482352941175, 48, 20065, 4070.0, 19377.1, 20003.0, 20011.49, 3.7498786803956343, 3.417184653125193, 0.8414651934055074], "isController": true}, {"data": ["Shipping - Set Method", 850, 31, 3.6470588235294117, 3699.048235294114, 33, 20019, 4050.0, 7058.899999999999, 14640.549999999992, 20005.0, 3.4088083608043185, 2.974968568781732, 0.8660268759474482], "isController": false}, {"data": ["TC-014 Order - Place Order", 850, 15, 1.7647058823529411, 3490.5011764705855, 31, 20010, 4078.0, 4373.7, 7010.6999999999925, 20003.49, 3.230207379313752, 2.7717695491675562, 0.7375219582847218], "isController": false}, {"data": ["Cart", 850, 84, 9.882352941176471, 8233.994117647066, 70, 40054, 5679.5, 22097.699999999997, 32363.8, 40008.0, 3.6403193202453146, 6.054423175343475, 1.6089508760321376], "isController": true}, {"data": ["TC-007 Cart - View Products", 850, 53, 6.235294117647059, 3777.0235294117624, 28, 20017, 2487.0, 12139.699999999999, 20002.0, 20005.0, 3.6135153978267893, 3.002937575458704, 0.7345516238500518], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 343, 100.0, 4.483660130718954], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 7650, 343, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 343, "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["TC-012 Customer - Add Details", 850, 38, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 38, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["TC-020 Shipping - Set Address", 850, 39, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 39, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Payment - Set Method", 850, 15, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 15, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["TC-001 Login", 850, 78, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 78, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["TC-004 Cart - Add Product", 850, 57, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 57, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Payment - Set Address", 850, 17, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 17, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["Shipping - Set Method", 850, 31, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 31, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["TC-014 Order - Place Order", 850, 15, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 15, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["TC-007 Cart - View Products", 850, 53, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 53, "", "", "", "", "", "", "", ""], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
