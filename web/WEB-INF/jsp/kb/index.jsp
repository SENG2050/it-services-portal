<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
    <jsp:attribute name="title">
        KB Index Page
    </jsp:attribute>
    <jsp:body>
        <h1 class="section-title text-center no-margin-top">Support Center</h1>
        <div class="panel panel-border">
            <div class="panel-body">
                <form method="get" action="/kb/search" class="m-b-none">
                    <div class="input-group">
                        <input type="text" class="form-control input-lg" value="${term}"
                               placeholder="Have a question? Ask or enter a search term here..." name="term"
                               required="required">
                        <span class="input-group-btn">
                            <button class="btn btn-ar btn-lg btn-primary" type="submit">Search</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <h2 class="right-line">Browse by Topic</h2>
            </div>
            <div class="col-md-6">
                <h3 class="no-margin-top">Getting Started</h3>
                <ul class="list-line">
                    <li><a href="#">Qui assumenda veniam impedit inventore blanditiis nulla?</a></li>
                    <li><a href="#">Amet, sunt, harum. Fugiat, reprehenderit mollitia dolore?</a></li>
                    <li><a href="#">Ullam possimus sapiente ratione, nemo eum saepe?</a></li>
                    <li><a href="#">Eius adipisci obcaecati quidem dolorum, saepe maxime?</a></li>
                    <li><a href="#">Ratione pariatur omnis sed ea quos optio?</a></li>
                </ul>
            </div>
            <div class="col-md-6">
                <h3 class="no-margin-top">Account and Preferences</h3>
                <ul class="list-line">
                    <li><a href="#">Qui assumenda veniam impedit inventore blanditiis nulla?</a></li>
                    <li><a href="#">Amet, sunt, harum. Fugiat, reprehenderit mollitia dolore?</a></li>
                    <li><a href="#">Ullam possimus sapiente ratione, nemo eum saepe?</a></li>
                    <li><a href="#">Eius adipisci obcaecati quidem dolorum, saepe maxime?</a></li>
                    <li><a href="#">Ratione pariatur omnis sed ea quos optio?</a></li>
                </ul>
            </div>
        </div>
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <h2 class="right-line">Premium &amp; Billing</h2>
            </div>
            <div class="col-md-6">
                <h3 class="no-margin-top">Versions &amp; Feedback</h3>
                <ul class="list-line">
                    <li><a href="#">Qui assumenda veniam impedit inventore blanditiis nulla?</a></li>
                    <li><a href="#">Amet, sunt, harum. Fugiat, reprehenderit mollitia dolore?</a></li>
                    <li><a href="#">Ullam possimus sapiente ratione, nemo eum saepe?</a></li>
                    <li><a href="#">Eius adipisci obcaecati quidem dolorum, saepe maxime?</a></li>
                    <li><a href="#">Ratione pariatur omnis sed ea quos optio?</a></li>
                </ul>
            </div>
            <div class="col-md-6">
                <h3 class="no-margin-top">Mobile &amp; Tablet</h3>
                <ul class="list-line">
                    <li><a href="#">Qui assumenda veniam impedit inventore blanditiis nulla?</a></li>
                    <li><a href="#">Amet, sunt, harum. Fugiat, reprehenderit mollitia dolore?</a></li>
                    <li><a href="#">Ullam possimus sapiente ratione, nemo eum saepe?</a></li>
                    <li><a href="#">Eius adipisci obcaecati quidem dolorum, saepe maxime?</a></li>
                    <li><a href="#">Ratione pariatur omnis sed ea quos optio?</a></li>
                </ul>
            </div>
        </div>
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <h2 class="right-line">Other Category</h2>
            </div>
            <div class="col-md-6">
                <h3 class="no-margin-top">Getting Started</h3>
                <ul class="list-line">
                    <li><a href="#">Qui assumenda veniam impedit inventore blanditiis nulla?</a></li>
                    <li><a href="#">Amet, sunt, harum. Fugiat, reprehenderit mollitia dolore?</a></li>
                    <li><a href="#">Ullam possimus sapiente ratione, nemo eum saepe?</a></li>
                    <li><a href="#">Eius adipisci obcaecati quidem dolorum, saepe maxime?</a></li>
                    <li><a href="#">Ratione pariatur omnis sed ea quos optio?</a></li>
                </ul>
            </div>
            <div class="col-md-6">
                <h3 class="no-margin-top">Account and Preferences</h3>
                <ul class="list-line">
                    <li><a href="#">Qui assumenda veniam impedit inventore blanditiis nulla?</a></li>
                    <li><a href="#">Amet, sunt, harum. Fugiat, reprehenderit mollitia dolore?</a></li>
                    <li><a href="#">Ullam possimus sapiente ratione, nemo eum saepe?</a></li>
                    <li><a href="#">Eius adipisci obcaecati quidem dolorum, saepe maxime?</a></li>
                    <li><a href="#">Ratione pariatur omnis sed ea quos optio?</a></li>
                </ul>
            </div>
        </div>
    </jsp:body>
</t:page>