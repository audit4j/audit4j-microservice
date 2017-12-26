package org.audit4j.microservice.web;

import java.util.HashMap;
import java.util.Map;

import org.audit4j.microservice.ServerConfiguration;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;

public class CustomAuthImpl implements CustomAuth {

    Map<String, String> users = new HashMap<>();

    public CustomAuthImpl(Vertx vertx, ServerConfiguration config) {
        users.put("test", "123");
    }

    @Override
    public void authenticate(JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {
        String username = authInfo.getString("username");

        // Null or empty username is invalid
        if (username == null || username.length() == 0) {
            resultHandler.handle((Future.failedFuture("Username must be set for authentication.")));
            return;
        }

        if (!users.containsKey(username)) {
            resultHandler.handle((Future.failedFuture("Unknown username.")));
            return;
        }
        final String password = users.get(username);

        if (authInfo.getString("password").equals(password)) {
            resultHandler.handle(Future.succeededFuture(new CustomUser(username)));
        } else {
            resultHandler.handle(Future.failedFuture("Unknown password"));
        }

    }

}
