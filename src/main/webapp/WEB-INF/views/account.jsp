<jsp:include page="components/header.jsp"/>
<jsp:include page="components/navbar.jsp" flush="true"/>

<!-- Page Title -->
<div class="section section-breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Account</h1>
            </div>
        </div>
    </div>
</div>
<!-- End Page Title -->

<div class="col-md-4 col-sm-6">
    <div class="panel panel-default">
        <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Portlet Heading</h4></div>
        <div class="panel-body">
            <ul class="list-group">
                <li class="list-group-item">Bootply Playground</li>
                <li class="list-group-item">Bootstrap Editor</li>
                <li class="list-group-item">Bootstrap Templates</li>
            </ul>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Stackoverflow</h4></div>
        <div class="panel-body">
            <img src="//placehold.it/150x150" class="img-circle pull-right"> <a href="#">Keyword: Bootstrap</a>
            <div class="clearfix"></div>
            <hr>

            <p>If you're looking for help with Bootstrap code, the <code>twitter-bootstrap</code> tag at <a href="http://stackoverflow.com/questions/tagged/twitter-bootstrap">Stackoverflow</a> is a good place to find answers.</p>

            <hr>
            <form>
                <div class="input-group">
                    <div class="input-group-btn">
                        <button class="btn btn-default">+1</button><button class="btn btn-default"><i class="glyphicon glyphicon-share"></i></button>
                    </div>
                    <input type="text" class="form-control" placeholder="Add a comment..">
                </div>
            </form>

        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Top Items</h4></div>
        <div class="panel-body">
            <div class="list-group">
                <a href="http://bootply.com/tagged/bootstrap-3" class="list-group-item active">Bootstrap 3</a>
                <a href="http://bootply.com/tagged/snippets" class="list-group-item">Snippets</a>
                <a href="http://bootply.com/tagged/example" class="list-group-item">Examples</a>
            </div>
        </div>
    </div>

</div>

<jsp:include page="components/footer.jsp" flush="true"/>