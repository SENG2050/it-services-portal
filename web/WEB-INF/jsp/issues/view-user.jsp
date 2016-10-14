<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
    <jsp:attribute name="title">
        ${issue.getTitle()}
    </jsp:attribute>
    <jsp:attribute name="breadcrumbs">
        <ol class="breadcrumb pull-right">
            <li>
                <a href="/">Home</a>
            </li>
            <li>
                <a href="/issues">Issues</a>
            </li>
            <li class="active">${issue.getTitle()}</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <section class="css-section">
            <div class="panel panel-primary-dark">
                <div class="panel-body">
                    <p>${issue.getDescription()}</p>
                </div>
            </div>
        </section>

        <form method="post">
            <div class="row">
                <div class="col-md-9">
                    <section class="css-section">
                        <h2 class="section-title">Comments</h2>
                        <ul class="list-unstyled">
                            <li class="comment">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <label class="control-label" for="reply">Reply</label>

                                        <textarea class="form-control" rows="5" id="reply" name="reply"
                                                  required="required" placeholder="Type your reply here..."></textarea>
                                    </div>
                                </div>
                            </li>
                            <li class="comment">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-1">
                                                <img src="https://www.gravatar.com/avatar/abc?d=mm&s=50"
                                                     class="imageborder img-circle">
                                            </div>
                                            <div class="col-sm-11">
                                                <p>Thanks Giang, I'll check in on this and get back to you soon.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-footer">
                                        <div class="row">
                                            <div class="col-lg-10 col-md-9 col-sm-8">
                                                <i class="fa fa-user"></i>
                                                Mitchell Davis
                                                <i class="fa fa-clock-o"></i>
                                                Sep 29, 2013
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="comment">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-1">
                                                <img src="https://www.gravatar.com/avatar/abc?d=mm&s=50"
                                                     class="imageborder img-circle">
                                            </div>
                                            <div class="col-sm-11">
                                                <p>Here is some secondary information that may be helpful.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-footer">
                                        <div class="row">
                                            <div class="col-lg-10 col-md-9 col-sm-8">
                                                <i class="fa fa-user"></i>
                                                Giang Pham
                                                <i class="fa fa-clock-o"></i>
                                                Sep 29, 2013
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </section>
                </div>
                <div class="col-md-3">
                    <section class="css-section">
                        <h2 class="section-title">Properties</h2>

                        <div class="panel panel-primary-dark">
                            <div class="panel-body">
                                <dl>
                                    <dt>User</dt>
                                    <dd>Giang Pham</dd>

                                    <dt>Time Created</dt>
                                    <dd>${issue.getCreated()}</dd>

                                    <dt>Status</dt>
                                    <dd>${issue.getStatus()}</dd>

                                    <dt>Category</dt>
                                    <dd>${issue.getCategory()}</dd>
                                </dl>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">
                            Submit Comment
                        </button>
                    </section>
                </div>
            </div>
        </form>
    </jsp:body>
</t:page>