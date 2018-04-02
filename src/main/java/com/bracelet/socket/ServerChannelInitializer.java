package com.bracelet.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

@Service
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Autowired
	BaseChannelHandler baseChannelHandler;
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new StringEncoder());
		pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(300));
		pipeline.addLast("handler", baseChannelHandler);
	}
}

//"http://minigps.net/cw?p=1&needaddress=0&mt=0&x=1cc-0-247a-ef6-8c-247a-1181-84-247a-1217-84-247a-1219-7b-247b-de1-7b-247a-1178-7a-247a-11bd-73"