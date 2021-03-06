var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
    /*
        Precondition: N is a nonnegative integer.
        Iterate, keeping the current and previous fibonacci values to generate new ones,
        and return the n-th one.
    */
    if (!Number.isInteger(n) || n < 0) {
        console.log("Invalid input.");
        return undefined;
    }
    if (n == 0)
        return 0;
    else if (n == 1)
        return 1;
    else {
        var i;
        var prev = 0;
        var cur = 1;
        for (i = 1; i < n; i++) {
            cur = cur + prev;
            prev = cur - prev;
        }
        return cur;
    }
};

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/


homework.merge = function(array1, array2) {
    /*
    Precondition: array1 and array2 are sorted arrays of numbers
    Postcondition: returns a sorted array that combines the elements of array1 and array2
    */
    var mergeArray = [];
    while (array1.length + array2.length > 0) {
        if (array1.length == 0)
            return mergeArray.concat(array2);
        else if (array2.length == 0)
            return mergeArray.concat(array1);
        else if (array1[0] < array2[0]) {
            mergeArray.push(array1.shift());
        }
        else {
            mergeArray.push(array2.shift());
        }
    }
    return mergeArray;
};

homework.sort = function(array) {
    /*
    Precondition: Array is an array of numbers.
    Postcondition: Returns the array sorted, via mergesort.
    */
    if (array.constructor != Array) {
        console.log("Invalid input.");
        return undefined;
    }
    var mid = Math.floor(array.length / 2);
    if (array.length <= 1)
        return array;
    else
        var array1 = homework.sort(array.slice(0, mid));
        var array2 = homework.sort(array.slice(mid, array.length));
        return homework.merge(array1, array2);
};




/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){
    /* 
    Precondition: n is a nonnegative integer.
    Postcondition: returns n!, calculated via multiplying into an accumulator.
    */
    if (!Number.isInteger(n) || n < 0) {
        console.log("Invalid input.");
        return undefined;
    }
    var i;
    var acc = 1;
    for (i = 1; i <= n; i++)
        acc *= i;
    return acc;
};

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) {
    /*
    Precondition: array is an array, number is a nonnegative integer.
    Postcondition: returns the array with the element in front shifted and
    pushed to the back n % array.length times. The use of mod is to reduce
    unnecessary rotations.
    */
    if (array.constructor != Array || !Number.isInteger(n) || n < 0) {
        console.log("Invalid input.");
        return undefined;
    }
    var i;
    n %= array.length; //reduces number of rotations
    for (i = 0; i < n; i++) {
        array.push(array.shift());
    }
    return array;
};

/*
 5. Balanced Brackets

 A bracket is any one of the following: (, ), {, }, [, or ]

 The following are balanced brackets:
    ()
    ()()
    (())
    ({[]})

 The following are NOT balanced brackets:
 (
 )
 (()
 ([)]

 Return true if balanced
 Return false if not balanced
*/
homework.balancedBrackets = function(bracketsString){
    /*
    Precondition: bracketsString is a string only consisting of the ([{}]) characters.
    Postcondition: Returns whether the string has balanced brackets. This is done by
    pushing left brackets onto a stack, and for right-brackets checking the top of the
    stack if they match, and popping if they do. We return false when there is a bracket
    mismatch or if there are elements still in the stack when it's done processing.
    */
    if (typeof(bracketsString) != 'string') {
        console.log("Invalid input.");
        return undefined;
    }
    var stack = [];
    while(bracketsString.length != 0) {
        var nextChar = bracketsString.charAt(0);
        bracketsString = bracketsString.substring(1);
        switch (nextChar) {
            case '[': case '(': case '{':
                stack.unshift(nextChar);
                break;
            case ']':
                if (stack[0] != '[') {
                    return false;
                } else {
                    stack.shift();
                }
                break;
            case ')':
                if (stack[0] != '(') {
                    return false;
                } else {
                    stack.shift();
                }
                break;
            case '}':
                if (stack[0] != '{') {
                    return false;
                } else {
                    stack.shift();
                }
                break;
            default:
                console.log("Invalid input.");
                return undefined;
        }
    }
    return (stack.length == 0) ? true : false;
};

//silly one-line inefficient version of above
homework.balancedBrackets2 = function(bracketsString) {
    return (bracketsString.match(/\[\]|\(\)|{}/g) != null) 
        ? this.balancedBrackets2(bracketsString.replace(/\[\]|\(\)|{}/g, ""))
        : (bracketsString.length == 0);
}