package com.xmr.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by xmr on 2019/7/31.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        channels.add(ctx.channel());//加入ChannelGroup
        System.out.println(ctx.channel().id()+" come into the chattingroom,"+"Online: "+channels.size());
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext context){
        System.out.println(context.channel().id()+" left the chattingroom,"+"Online: "+channels.size());
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive----->");
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server channelRead......");
        System.out.println(ctx.channel().remoteAddress()+"----->Server :"+ msg.toString());
        //将客户端的信息直接返回写入ctx
        for (Channel channel:channels){
            channel.writeAndFlush(msg.toString());
        }
//        ctx.write("server say :"+msg);
//        //刷新缓存区
//        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}