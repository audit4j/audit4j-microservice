package org.audit4j.microservice.core;

import org.audit4j.core.Initializable;

public interface ContextAware<T extends Context> {

    public void setContext(T context);
}
