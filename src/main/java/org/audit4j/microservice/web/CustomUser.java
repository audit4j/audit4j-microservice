package org.audit4j.microservice.web;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;

public class CustomUser implements User{
private String username;

    public CustomUser(String username) {
        this.username = username;
    }
    @Override
    public User clearCache() {
        return this;
    }

    @Override
    public User isAuthorised(String authority, Handler<AsyncResult<Boolean>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(false));
        return this;
    }

    @Override
    public JsonObject principal() {
        return new JsonObject()
                .put("username", username);
    }

    @Override
    public void setAuthProvider(AuthProvider arg0) {        
    }

}
