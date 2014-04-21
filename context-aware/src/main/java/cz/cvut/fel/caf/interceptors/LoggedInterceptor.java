package cz.cvut.fel.caf.interceptors;

import cz.cvut.fel.caf.annotations.Logged;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Created by Tomáš on 18.4.14.
 */
@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {

    @AroundInvoke
    public Object invoke(InvocationContext ctx) throws Exception {
        Logger.getLogger(ctx.getTarget().getClass().getName()).info(ctx.getMethod().getName());
        return ctx.proceed();
    }
}
