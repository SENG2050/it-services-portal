<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:plain>
    <jsp:attribute name="title">
        Login
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
            <div class="login-form-full">
                <div class="fix-box">
                    <div class="text-center title-logo animated fadeInDown animation-delay-5">
                        artificial<span>reason</span>
                    </div>
                    <div class="animated fadeInUp animation-delay-8">
                        <form role="form" method="post" action="/login">
                            <div class="form-group">
                                <div class="input-group login-input m-b">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input type="email" class="form-control" name="email" placeholder="Email"
                                           required="required">
                                </div>
                                <div class="input-group login-input m-b">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input type="password" class="form-control" name="password" placeholder="Password"
                                           required="required">
                                </div>
                                <button type="submit" class="btn btn-ar btn-primary pull-right">Login</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:plain>