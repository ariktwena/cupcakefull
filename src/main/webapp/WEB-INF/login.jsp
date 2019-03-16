<%@ page import="modelLayer.Bottom" %>
<%@ page import="modelLayer.Top" %>
<%@ page import="facadeLayer.TopFacade" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="facadeLayer.BottomFacade" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ include file="../include/header.jsp" %>

<div class="jumbotron">

    <div class="row">

        <div class="col-md-5">
            <form method="post" action="FrontController" role="form">
                <fieldset>
                    <input type="hidden" name="source" value="login"/>
                    <p class="text-uppercase"> Log ind for at handle: </p>

                    <div class="form-group">
                        <input type="email" name="username" id="username" class="form-control input-lg"
                               placeholder="Email adresse">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" id="password" class="form-control input-lg"
                               placeholder="Kodeord">
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary btn-success  btn-md" name="action" value="signin">Log ind</button>
                    </div>

                </fieldset>
            </form>
        </div>

        <div class="col-md-2">
            <!-------null------>
        </div>

        <div class="col-md-5">
            <form role="form" method="post" action="/FrontController">
                <fieldset>
                    <input type="hidden" name="source" value="register"/>
                    <p class="text-uppercase pull-center">Opret mig som kunde:</p>
                    <div class="form-group">
                        <input type="text" name="username" id="username" class="form-control input-lg"
                               placeholder="Email adresse">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" id="password" class="form-control input-lg"
                               placeholder="Kodeord">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password2" id="password2" class="form-control input-lg"
                               placeholder="Gentag kodeord">
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="checkbox" class="form-check-input">
                            Klik for at acceptere vores handelsbetingelser
                        </label>
                    </div>
                    <br/>
                    <div>
                        <button type="submit" class="btn btn-md btn-success" name="action" value="create">Opret</button>
                    </div>
                </fieldset>
            </form>
        </div>

    </div>
</div>
</div>

</div>


<%@ include file="../include/footer.jsp" %>

