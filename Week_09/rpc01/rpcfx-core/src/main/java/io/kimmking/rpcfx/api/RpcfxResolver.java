package io.kimmking.rpcfx.api;

public interface RpcfxResolver {

    Object resolve(String serviceClass);

    /**
     * don't want override before, just create a new func
     * @param klass
     * @param <T>
     * @return
     */
    <T> T resolveGe(Class<T> klass);

}
