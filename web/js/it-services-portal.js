var IssuesViewAdmin = {
    submitAs: function (status) {
        var statusElement = document.getElementById('issueStatus');

        statusElement.value = status;

        document.viewForm.submit();
    }
};