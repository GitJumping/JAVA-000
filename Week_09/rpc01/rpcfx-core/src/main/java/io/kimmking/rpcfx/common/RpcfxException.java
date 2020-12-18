package io.kimmking.rpcfx.common;

public class RpcfxException extends RuntimeException{
    public RpcfxException(String msg){
        super(msg);
    }

    public RpcfxException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public RpcfxException(Throwable throwable) {
        super(throwable);
    }
}
