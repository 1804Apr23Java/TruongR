//behavior that will execute when page loads
window.onload = function() {
    console.log('page loaded');
    counter = getCount();
    appleCounter = getCount('apple');
    orangeCounter = getCount('orange');
}

function getCount(noun) {
    var count = 0;
    return function() {
        count += 1;
        if(noun) {
            console.log(noun + ' count is: ' + count);
            
        } else {
            console.log('count is: ' + count);
        }
        return count;
    }
}