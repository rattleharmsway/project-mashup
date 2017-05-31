

var heightElem = document.getElementById("height");
var formElem = document.getElementById("draw-form");

formElem.oninput = function(event) {
    //document.querySelector(".height-value").innerHTML = "";
    var heightValue = document.getElementById("height-bar");
    document.getElementById("height-value").innerHTML = heightValue.value;

    height = parseInt(heightValue.value);
    drawPyramid(height);
}

/**
 * displayError
 *
 * Displays an error message on the text input, and colors it red
 */
function displayError(message) {
    heightElem.className = "invalid-field";
    document.querySelector(".error-message").innerHTML = message;
    document.querySelector(".success-message").innerHTML = "";
}


/*
 * clearError
 *
 * Undisplays the error message and removes the red CSS style
 */
function clearError(message) {
    heightElem.className = "valid-field";
    document.querySelector(".error-message").innerHTML = "";
    document.querySelector(".success-message").innerHTML = message;
    // TODO 3
    // implement this function.
}



/**
 * drawPyramid
 *
 * Renders, in the HTML document, a Mario pyramid of the specified height
 */
function drawPyramid(height) {

    // first, clear the old content
    document.getElementById("pyramid").innerHTML = "";

    // for each row....
    for (var row = 0; row < height; row++) {

        // figure out number of bricks and spaces
        var numBricks = row + 2;
        var numSpaces = height - row - 1;

        // build up a string for this row
        var rowStr = "";
        for (var i = 0; i < numSpaces; i++) {
            var spaceChar = "&nbsp"; // this is the HTML encoding for a space " "
            rowStr += spaceChar;
        }
        for (var i = 0; i < numBricks; i++) {
            rowStr += "#";
        }

        // make a <p> element for this row, and insert it into the #pyramid container
        rowElem = document.createElement("p");
        rowElem.innerHTML = rowStr;
        document.getElementById("pyramid").appendChild(rowElem);
    }
}
