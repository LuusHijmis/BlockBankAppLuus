window.onload= changeTabel;
document.addEventListener('DOMContentLoaded', main);
function main() {
    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/portfolio/get/json',
        headers: {"Authorization": localStorage.getItem('Authentication')},
        //data : JSON.stringify(dataRaw),
        contentType: "application/json",
        accepts: {json: "application/json"},
        success: function (data, status, xhr) {
            console.log(data);
            console.log(status);
            console.log(xhr);
            CreateTableFromJSON(data);

            // changeTabel();
            // addBuyButtons();

            alert('request successful');
            //data opslaan in een variabele
            //
        },
        fail: function(xhr, status, errorThrown){
            console.log(xhr);
            console.log(status);
            console.log(errorThrown);
            alert('request failed');
        }
    })

}
function changeTabel(){

    document.getElementsByTagName('th').item(0).innerHTML="Asset ID";
    document.getElementsByTagName('th').item(1).innerHTML="Name";
    document.getElementsByTagName('th').item(2).innerHTML="Symbol";
    document.getElementsByTagName('th').item(3).innerHTML="Exchange rate (EUR)";

}

function addBuyButtons() {
    var data =document.getElementsByTagName('td');
    var tables = document.getElementsByTagName('table');
    // var headers= document.getElementsByTagName('hd');
    var row = document.getElementsByTagName('tr').item(1);
    // var table = tables.item(0);
    console.log(row);

    var rows = document.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) {
        var row = document.getElementsByTagName('tr').item(i);
        var rowCell = row.cells[5];
        var rowCellAssetID= row.cells[1];
        console.log(rowCellAssetID.firstChild.nodeValue);
        var id = "buyButton" + i;
        var buttonString = "<button id='buyButton" + i + "'class=\"button-click\">Buy</button>";
        console.log(id);
        console.log(buttonString);
        rowCell.innerHTML = buttonString;
        document.getElementById(id).addEventListener("click", () => {
            var dataValue = $(this).attr('assetID');
            var filteredResults = results.filter(function (result) {
                if (result.IdNum.toString() === dataValue.toString()) {
                    return result;
                }
            });

            console.log(filteredResults);
            // var huidigeRij = de listener moet achterhalen aan welke rij hij gekoppeld is
            // var huidigeAssetID = huidigeRij.cells[1];
            // var assetID = rowCellAssetID.firstChild.nodeValue;
            // console.log(assetID);
        console.log(alert(rowCellAssetID.firstChild.nodeValue));
        });



        // tables.onclick = e => {
        //     if (!e.target.matches("button.buyButton1")) return console.log(e.target.textContent);
        // }
    }

}




    function CreateTableFromJSON(data) {

        var results = JSON.parse(data);

        function buildTable(currentResults) {

            console.log(currentResults);
            var resultsHtml = '<table>';
            resultsHtml += ' <tr><th>Asset ID</th><th>Asset Name</th><th>Symbol</th><th>Exchange Rate</th><th>Amount</th><th></th>' +
                '<th></th></th></tr>';
            currentResults.forEach(function (result){
            // currentResults.forEach(function (result) {
                // add a class to the table cell here and also another attr, which we will call data-value which will contain 			the 	value of the data.
                resultsHtml += '<tr><td dataValue="' + result.assetID + '" class="table-cell-action">' + result.assetID + '</td>' +
                    '<td>' + result.assetName + '</td><td>' + result.assetSymbol + '</td><td> ' + result.exchangeRate + '</td> ' +
                    '<td>' + result.assetAmount + ' </td><td><button data-id="' + result.assetID + '" class="button-click">Buy</button></td>' +
                    '<td><button data-id="' + result.assetID + '" class="button-click">Sell</button></td></tr>'
            });

            resultsHtml += '</table>'

            return resultsHtml
        }

        $('#results-table-holder').empty();
        console.log('append all results')
        $('#results-table-holder').append(buildTable(results));

        $('.table-cell-action').off('click')
        $('.table-cell-action').on('click', function (e) {
            // you could just use the text value of the cell here
            var dataValue = $(this).attr('assetID');
            var filteredResults = results.filter(function (result) {
                if (result.assetID.toString() === dataValue.toString()) {
                    return result;
                }
            });

            console.log(filteredResults);
        });

        $('.button-click').off();
        $('.button-click').on('click', function () {
            // navigate to new page
            // console.log('button click')

            // what you could do here is get the serialnumber which is an attribute on the button
            var id = $(this).attr('data-id') // as long as this is unique this should always work

            var filteredResults = results.filter(function (result) {
                if (result.assetID.toString() === id.toString()) {
                    return result;
                }
            });

            var selectedRowValues = filteredResults[0];

// this will contain all the values for that row
            console.log(selectedRowValues)

            ;
            // var dataValue = $(this).attr('data-url'); // dont't need this any more
            alert(selectedRowValues.assetName + " " + selectedRowValues.assetID);
        });


// var myAssets = JSON.parse(data);
        // // EXTRACT VALUE FOR HTML HEADER.
        // // ('Asset ID', 'Symbol''Name', 'Disription' and 'exchangerate')
        // var col = [];
        // for (var i = 0; i < myAssets.length; i++) {
        //     for (var key in myAssets[i]) {
        //         if (col.indexOf(key) === -1) {
        //             col.push(key);
        //         }
        //     }
        // }
        //
        // col.push("Buy");
        // col.push("Sell");
        //
        // // CREATE DYNAMIC TABLE.
        // var table = document.createElement("table");
        //
        // // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.
        //
        // var tr = table.insertRow(-1);                   // TABLE ROW.
        //
        // for (var i = 0; i < col.length; i++) {
        //     var th = document.createElement("th");      // TABLE HEADER.
        //     th.innerHTML = col[i];
        //     tr.appendChild(th);
        // }
        //
        // // ADD JSON DATA TO THE TABLE AS ROWS.
        // for (var i = 0; i < myAssets.length; i++) {
        //
        //     tr = table.insertRow(-1);
        //
        //     for (var j = 0; j < col.length; j++) {
        //         var tabCell = tr.insertCell(-1);
        //         tabCell.innerHTML = myAssets[i][col[j]];
        //     }
        //
        // }
        //
        // // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
        // var divContainer = document.getElementById('showData');
        // divContainer.innerHTML = "";
        // divContainer.appendChild(table);

    }