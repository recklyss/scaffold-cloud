/**
 * @Title: ShutdownHook.java
 * @Package com.gjj.p2p.common.mq
 *
 * @author jiaheng
 * @date 2017-7-7
 */
package com.cms.scaffold.code.mq.core;

import com.aliyun.openservices.ons.api.Consumer;

/**
 * @author zhangjiaheng
 */
public class ShutdownHook extends Thread{

	Consumer consumer;

	/**
	 * @param consumer
	 */
	public ShutdownHook(Consumer consumer) {
		super();
		this.consumer = consumer;
	}

	/**
	 *
	 * @author jiaheng
	 * @date 2017-7-7
	 */
	@Override
	public void run() {
		consumer.shutdown();
	}


}
