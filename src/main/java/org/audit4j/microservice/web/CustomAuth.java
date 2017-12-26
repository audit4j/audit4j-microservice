package org.audit4j.microservice.web;

import org.audit4j.microservice.ServerConfiguration;

import io.vertx.core.Vertx;
import io.vertx.ext.auth.AuthProvider;

public interface CustomAuth extends AuthProvider{

    static CustomAuth create(Vertx vertx, ServerConfiguration config) {
        return new CustomAuthImpl(vertx, config);
      }
}
