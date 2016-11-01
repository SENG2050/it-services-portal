<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:plain>
    <jsp:attribute name="title">
        Page Not Found
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $(document).ready(wrapResize);
            $(window).resize(wrapResize);

            function wrapResize() {
                var size = $(window).height();
                var back = $('.paper-back-full');

                back.css('min-height', size + 'px');
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="paper-back-full">
            <div class="absolute-center">
                <div class="text-center">
                    <div class="transparent-div animated fadeInUp animation-delay-8">
                        <h1>Error 404</h1>
                        <h2>Page Not Found</h2>
                        <a href="${baseURL}" class="btn btn-ar btn-primary btn-lg">Go Home</a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:plain>