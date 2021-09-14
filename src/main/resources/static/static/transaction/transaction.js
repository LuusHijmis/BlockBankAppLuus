window.onload= changeTabel;
document.addEventListener('DOMContentLoaded', main);
function main(){
    //eerst request sturen naar backend om data naar binnen te halen
    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/transaction/get/json',
        //data : JSON.stringify(dataRaw),
        contentType: "application/json",
        accepts: {json: "application/json"},
        success: function (data, status, xhr) {
            console.log(data);
            console.log(status);
            console.log(xhr);

            CreateTableFromJSON(data);

            changeTabel()



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

    document.getElementsByTagName('th').item(0).innerHTML="Symbol";
    document.getElementsByTagName('th').item(1).innerHTML="Name";
    document.getElementsByTagName('th').item(2).innerHTML="Exchange Rate (EUR)";

}



function CreateTableFromJSON(data) {
    var myAssets = JSON.parse(data);
    // EXTRACT VALUE FOR HTML HEADER.
    // ('Asset ID', 'Symbol''Name', 'Disription' and 'exchangerate')
    var col = [];
    for (var i = 0; i < myAssets.length; i++) {
        for (var key in myAssets[i]) {
            if (col.indexOf(key) === -1) {
                col.push(key);
            }
        }
    }
//create two extra columns
    col.push("Buy");
    col.push("Sell");


    // CREATE DYNAMIC TABLE.
    var table = document.createElement("table");

    // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

    var tr = table.insertRow(-1);                   // TABLE ROW.

    for (var i = 0; i < col.length; i++) {
        var th = document.createElement("th");      // TABLE HEADER.
        th.innerHTML = col[i];
        tr.appendChild(th);
        /*document.getElementsByTagName("th");
        headers[0].innerHTML.replace("SYMBOL");*/
    }

    // ADD JSON DATA TO THE TABLE AS ROWS.
    for (var i = 0; i < myAssets.length; i++) {

        tr = table.insertRow(-1);

        for (var j = 0; j < col.length; j++) {
            var tabCell = tr.insertCell(-1);
            tabCell.innerHTML = myAssets[i][col[j]];
        }
    }
    //add button sell and buy
    for (var i = 0; i <table.length; i++){

    }



    // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
    var divContainer = document.getElementById('showData');
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
    /*function changeTabel(){

        //document.getElementsByTagName("th").innerHTML.replace("symbol","Symbol");
        document.getElementById("myTable").rows[0].cells;
        x[0].innerHTML = "NEW CONTENT"

    }*/

}



