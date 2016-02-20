package org.audit4j.microservice;

import org.audit4j.core.Initializable;

public interface ContextAware<T extends Context> {

    public void setContext(T context);
}
