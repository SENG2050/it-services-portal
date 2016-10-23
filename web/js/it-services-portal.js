var IssuesViewAdmin = {
    submitAs: function (status) {
        var statusElement = document.getElementById('issueStatus');

        statusElement.value = status;

        document.viewForm.submit();
    }
};

var IssuesViewUser = {
    submitAs: function (status) {
        var statusElement = document.getElementById('issueStatus');

        statusElement.value = status;

        document.viewForm.submit();
    }
};

var IssuesNew = {
    updateIframe: debounce(
        function (description) {
            var iframeElement = document.getElementById('related-articles');

            iframeElement.src = '/kb/suggest?term=' + encodeURIComponent(description);
        },
        300
    ),
    relatedArticles: function (status) {
        var descriptionElement = document.getElementById('description');

        var description = descriptionElement.value;

        this.updateIframe(description);
    }
};

// http://stackoverflow.com/a/20722176
function resizeIframe(obj) {
    obj.style.height = 0;
    obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
};

// http://stackoverflow.com/a/24004942
function debounce(func, wait, immediate) {
    // 'private' variable for instance
    // The returned function will be able to reference this due to closure.
    // Each call to the returned function will share this common timer.
    var timeout;

    // Calling debounce returns a new anonymous function
    return function () {
        // reference the context and args for the setTimeout function
        var context = this,
            args = arguments;

        // Should the function be called now? If immediate is true
        //   and not already in a timeout then the answer is: Yes
        var callNow = immediate && !timeout;

        // This is the basic debounce behaviour where you can call this
        //   function several times, but it will only execute once
        //   [before or after imposing a delay].
        //   Each time the returned function is called, the timer starts over.
        clearTimeout(timeout);

        // Set the new timeout
        timeout = setTimeout(function () {

            // Inside the timeout function, clear the timeout variable
            // which will let the next execution run when in 'immediate' mode
            timeout = null;

            // Check if the function already ran with the immediate flag
            if (!immediate) {
                // Call the original function with apply
                // apply lets you define the 'this' object as well as the arguments
                //    (both captured before setTimeout)
                func.apply(context, args);
            }
        }, wait);

        // Immediate mode and no wait timer? Execute the function..
        if (callNow) func.apply(context, args);
    };
};