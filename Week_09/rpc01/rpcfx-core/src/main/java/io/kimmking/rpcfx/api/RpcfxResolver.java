package io.kimmking.rpcfx.api;

public interface RpcfxResolver {

    Object resolve(String serviceClass);

    <T> T resolveGe(Class<T> klass);

}
