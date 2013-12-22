// Copyright (c) 2013, Webit Team. All Rights Reserved.
package webit.script.method.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import webit.script.Context;
import webit.script.exceptions.ScriptRuntimeException;
import webit.script.method.MethodDeclare;

/**
 *
 * @author Zqq
 */
public class NativeConstructorDeclare implements MethodDeclare {

    private final Constructor constructor;
    private final int argsCount;

    public NativeConstructorDeclare(Constructor constructor) {
        this.constructor = constructor;
        this.argsCount = constructor.getParameterTypes().length;
    }

    public Object invoke(final Context context, final Object[] args) {

        final Object[] methodArgs;
        final int argsLen;
        final int myArgsCount = this.argsCount;
        if (args != null && (argsLen = args.length) != 0) {
            if (argsLen == myArgsCount) {
                methodArgs = args;
            } else {
                //Note: Warning 参数个数不一致
                System.arraycopy(args, 0, methodArgs = new Object[myArgsCount], 0, argsLen <= myArgsCount ? argsLen : myArgsCount);
            }
        } else {
            methodArgs = new Object[myArgsCount];
        }
        try {
            return constructor.newInstance(methodArgs);
        } catch (InstantiationException ex) {
            throw new ScriptRuntimeException("this a abstract class, can't create new instance: ".concat(ex.getLocalizedMessage()));
        } catch (IllegalAccessException ex) {
            throw new ScriptRuntimeException("this method is inaccessible: ".concat(ex.getLocalizedMessage()));
        } catch (IllegalArgumentException ex) {
            throw new ScriptRuntimeException("illegal argument: ".concat(ex.getLocalizedMessage()));
        } catch (InvocationTargetException ex) {
            throw new ScriptRuntimeException("this method throws an exception", ex.getTargetException());
        }
    }
}