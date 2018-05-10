var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
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