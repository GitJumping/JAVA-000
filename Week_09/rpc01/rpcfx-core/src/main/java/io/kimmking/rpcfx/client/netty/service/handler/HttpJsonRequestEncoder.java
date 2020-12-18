package io.kimmking.rpcfx.client.netty.service.handler;

import io.kimmking.rpcfx.client.netty.service.vo.NettyHttpRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;
import java.util.List;

public class HttpJsonRequestEncoder extends MessageToMessageEncoder<NettyHttpRequest> {
    final static String CHARSET_NAME = "UTF-8";

    final static Charset UTF_8 = Charset.forName(CHARSET_NAME);


    @Override
    protected void encode(ChannelHandlerContext ctx, NettyHttpRequest nettyHttpRequest,
                          List<Object> out) {
        FullHttpRequest request = null;
        if (nettyHttpRequest.getHttpMethod() == HttpMethod.POST) {
            ByteBuf encodeBuf = Unpooled.copiedBuffer((CharSequence) nettyHttpRequest.getBody(), UTF_8);
            request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                    HttpMethod.POST, nettyHttpRequest.getUri(), encodeBuf);

            HttpUtil.setContentLength(request, encodeBuf.readableBytes());
        } else if (nettyHttpRequest.getHttpMethod() == HttpMethod.GET) {
            request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                    HttpMethod.GET, nettyHttpRequest.getUri());
        } else {
            throw new RuntimeException();
        }

        populateHeaders(ctx, request);

        out.add(request);
    }

    private void populateHeaders(ChannelHandlerContext ctx, FullHttpRequest request) {

        HttpHeaders headers = request.headers();
        headers.set(HttpHeaderNames.HOST, ctx.channel().remoteAddress().toString().substring(1));
        headers.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        headers.set(HttpHeaderNames.CONTENT_TYPE,
                "application/json");

        headers.set(HttpHeaderNames.ACCEPT_ENCODING,
                HttpHeaderValues.GZIP.toString() + ','
                        + HttpHeaderValues.DEFLATE.toString());

        headers.set(HttpHeaderNames.ACCEPT_CHARSET,
                "utf-8,ISO-8859-1;q=0.7,*;q=0.7");
        headers.set(HttpHeaderNames.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.set(HttpHeaderNames.ACCEPT, "*/*");

        headers.set(HttpHeaderNames.USER_AGENT,
                "Netty xml Http Client side");
    }
}
