package cz.cvut.fel.aui.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * Created by Tomáš on 4. 5. 2014.
 */
public class ValidationInterceptor implements Serializable {

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        return ctx.proceed();
    }
}
