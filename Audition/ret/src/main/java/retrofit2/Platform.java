package retrofit2;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * 1 findPlatform
 * 2 Java8 Andrid platform
 * 3 callbackExecutor  callAdapterFactory 默认
 */

class Platform {

    private static final Platform PLATFORM = findPlatform();

    static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("java.util.Optional");
            return new Java8();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new Platform();
    }


    @Nullable
    Executor defaultCallbackExecutor() {
        return null;
    }

    CallAdapter.Factory defaultCallAdapterFactory(@Nullable Executor callbackExecutor) {
        if (callbackExecutor != null) {
            return new ExecutorCallAdapterFactory(callbackExecutor);
        }
        return DefaultCallAdapterFactory.INSTANCE;
    }

    boolean isDefaultMethod(Method method) {
        return false;
    }

    @Nullable
    Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object, @Nullable Object... args)
        throws Throwable {
        throw new UnsupportedOperationException();
    }


//    @IgnoreJRERequirement // Only classloaded and used on Java 8.
    static class Java8 extends Platform {

        @Override
        boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        @Nullable
        @Override
        Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object, @Nullable Object... args) throws Throwable {
            // Because the service interface might not be public, we need to use a MethodHandle lookup
            // that ignores the visibility of the declaringClass.
            Constructor<Lookup> constructor = Lookup.class.getDeclaredConstructor(Class.class, int.class);
            constructor.setAccessible(true);
            return constructor.newInstance(declaringClass, -1)
                    .unreflectSpecial(method, declaringClass)
                    .bindTo(object)
                    .invokeWithArguments(args);
        }
    }



    static class Android extends Platform {

        @Override
        @Nullable
        Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        @Override
        CallAdapter.Factory defaultCallAdapterFactory(@Nullable Executor callbackExecutor) {
            if (callbackExecutor == null) {
                throw new AssertionError();
            }
            return new ExecutorCallAdapterFactory(callbackExecutor);
        }

        static class MainThreadExecutor implements Executor {

            private final Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void execute(@NonNull Runnable runnable) {
                handler.post(runnable);
            }
        }
    }
}
