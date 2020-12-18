package io.kimmking.rpcfx.client.netty.service.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.kimmking.rpcfx.client.service.nettyclient.NettyClient;
import io.kimmking.rpcfx.client.service.nettyclient.pool.NettyClientPool;
import io.kimmking.rpcfx.client.service.nettyclient.pool.NettyClientPoolCacheByHostAndPort;
import io.kimmking.rpcfx.client.service.nettyclient.vo.HostAndPortConfig;
import io.kimmking.rpcfx.client.service.nettyclient.vo.NettyHttpResponse;
import io.kimmking.rpcfx.client.utils.ModuleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

public class NettyHttpClientUtils {
    public static NettyHttpResponse doPost(String url, Object body) {
        if (body instanceof String) {
            String s = (String) body;
            boolean bMaybeJsonObjStr = s.startsWith("{") && s.endsWith("}");
            if (bMaybeJsonObjStr) {
                log.error("we will convert it to json,thanks ");
            }
        }

        StopWatch timer = new StopWatch();
        timer.start();
        HostAndPortConfig config = new HostAndPortConfig(ModuleUtils.extractIp(url),
                Integer.valueOf(ModuleUtils.extractPort(url)));
        NettyClientPool nettyClientPool = NettyClientPoolCacheByHostAndPort.getOrCreateNettyClientPoolIfNecessay(config);
        NettyClient resource = nettyClientPool.getResource();
        if (resource == null) {
            throw new RuntimeException("获取连接失败");
        }
        timer.stop();
        log.info("spent {} to get netty client",timer.getTime());

        try {
            timer.reset();
            timer.start();
            NettyHttpResponse httpResponse = resource.doPost(url, JSONObject.toJSONString(body, SerializerFeature.PrettyFormat));
            if (httpResponse == null) {
                throw new RuntimeException("调用超时");
            }
            return httpResponse;
        }finally {
            timer.stop();
            log.info("spent {} to invoke rpc",timer.getTime());
            nettyClientPool.returnResourceObject(resource);
        }
    }


    public static Object doGet(String url) {
        log.info("url:{}");
        HostAndPortConfig config = new HostAndPortConfig(ModuleUtils.extractIp(url),
                Integer.valueOf(ModuleUtils.extractPort(url)));
        NettyClientPool nettyClientPool = NettyClientPoolCacheByHostAndPort.getOrCreateNettyClientPoolIfNecessay(config);

        NettyClient resource = nettyClientPool.getResource();

        if (resource == null) {
            throw new RuntimeException("获取连接失败");
        }

        try {
            NettyHttpResponse httpResponse = resource.doGet(url);
            if (httpResponse == null) {
                throw new RuntimeException("调用超时");
            }
            return httpResponse;
        }finally {
            nettyClientPool.returnResourceObject(resource);
        }

    }
}
