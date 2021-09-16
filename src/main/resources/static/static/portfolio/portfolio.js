
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
            document.getElementById("Sell").addEventListener('click', sellRequest)
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
                    '<td>' + result.assetAmount + ' </td><td><button data-id="' + result.assetID + '" data-action="Buy" ' + ' class="button-click">Buy</button></td>' +
                    '<td><button data-id="' + result.assetID + '" data-action="Sell" '+ ' class="button-click">Sell</button></td></tr>'
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
            if($(this).attr('data-action') === "Sell") {
                document.getElementById("assetID").innerHTML = "AssetID: " + selectedRowValues.assetID;
                document.getElementById("assetName").innerHTML = "Asset Name: " + selectedRowValues.assetName;
                document.getElementById("exchangeRate").innerHTML = "Price per asset: " + selectedRowValues.exchangeRate;
                document.getElementById("transactionCosts").innerHTML = "Transaction costs: ";
            } else {
                window.location.replace("http://localhost:8080/static/transaction/transaction.html");
            }
        });
}
function sellRequest(){
    var transactionSort = "Sell";
    var transactionAmount = document.getElementById(sellAmount_f).value;
    var opposingUserID = 1;
    var assetID = document.getElementById(assetID).value;

    $.ajax({
        type : "POST",
        url : 'http://localhost:8080/transaction',
        headers: {"Authorization": localStorage.getItem('Authentication'), "transactionSort": transactionSort,
            "amount" : transactionAmount, "oppossingUserID" : opposingUserID, "assetID": assetID },
        // data : { transactionSort: transactionSort, amount : transactionAmount, oppossingUserID : opposingUserID, assetID: assetID},
        contentType: "application/json",
        accepts: { json: "application/json"},
        success: function (data, status, xhr) {
            console.log(data);
            console.log(status);
            console.log(xhr);
            alert('request successful');

        },
        fail: function(xhr, status, error){
            console.log(xhr);
            console.log(status);
            console.log(error);
        },
        error: function (request) {
            alert(request.responseText);
        }
    });
}