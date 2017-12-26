package org.audit4j.microservice.web;

import org.audit4j.microservice.ServerConfiguration;

import io.vertx.core.Vertx;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.FormLoginHandler;
import io.vertx.ext.web.handler.RedirectAuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.UserSessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

public class WebRouter {

    Vertx vertx;
    
    public WebRouter(Vertx vertx) {
        this.vertx = vertx;
    }
    
    public Router getRouter() {
        Router router = Router.router(vertx);

        // We need cookies, sessions and request bodies
        router.route().handler(CookieHandler.create());
        router.route().handler(BodyHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
        
        AuthProvider authProvider = CustomAuth.create(vertx, new ServerConfiguration());

        router.route().handler(UserSessionHandler.create(authProvider));

        router.route("/dashboard/*").handler(RedirectAuthHandler.create(authProvider, "/login.html"));

        router.route("/loginhandler").handler(FormLoginHandler.create(authProvider));

        
        // Implement logout
        router.route("/logout").handler(context -> {
          context.clearUser();
          // Redirect back to the index page
          context.response().putHeader("location", "/").setStatusCode(302).end();
        });

        
        return router;
    }
}
