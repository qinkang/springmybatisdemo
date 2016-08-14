package com.qingofun.redis;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * redis集群接口
 * 
 */
public interface IJedisCluster {

	/**
	 * 获取redis集群对象
	 * 
	 * @return
	 */
	JedisCluster getJedis();
}
