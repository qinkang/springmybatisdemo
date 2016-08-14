/**
 * json转为csv文件
 * @param JSONData
 * @param ReportTitle
 * @param Arr
 */
function JSONToCSVConvertor(JSONData, ReportTitle, Arr) {
    //If JSONData is not an object then JSON.parse will parse the JSON string in an Object
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
    var CSV = '';

    // show label
    var firstRow = "";
    for (var x in Arr[0]) {
        firstRow += x + ',';
    }
    firstRow = firstRow.slice(0, -1);
    CSV += firstRow + '\r\n';

    //1st loop is to extract each row
    for (var i = 0; i < arrData.length; i++) {
        var row = "";
        //2nd loop will extract each column and convert it in string comma-seprated
        for (var index in Arr[0]) {
            var actualIndex = Arr[0][index];
            row += '"' + (arrData[i][actualIndex] ? arrData[i][actualIndex] : '') + '",';
        }
        row.slice(0, row.length - 1);
        CSV += row + '\r\n';
    }
    if (CSV == '') {
        alert("Invalid data");
        return;
    }
    //Generate a file name
    var fileName = "";
    fileName += ReportTitle.replace(/ /g, "_");
    //Initialize file format you want csv or xls
    var uri = 'data:text/csv;charset=utf-8,\uFEFF' + encodeURI(CSV);
    //this trick will generate a temp <a /> tag
    var link = document.createElement("a");
    link.href = uri;
    link.style = "visibility:hidden";
    link.download = fileName + ".csv";
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}