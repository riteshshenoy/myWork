/*Write a program that asks the user for a number n, then prints to the console the sum
of the numbers 1 to n, and shows the user an alert box as described below*/

//Get Input from the user,Also parse to Integer.
var numberEntered = parseInt(prompt("How many numbers do you want to sum?"));
//Function to add given N numbers
function sumOfNumbers(x){
    return x*(x +1) / 2;
}
//Result of n Numbers
var valueOfN = sumOfNumbers(numberEntered);
//Prints sum on the console
console.log(valueOfN);
//Shows an alert
alert("The sum of the first " + numberEntered + " numbers is " + valueOfN );