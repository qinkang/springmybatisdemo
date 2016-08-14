package com.qingofun.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * redis集群对象
 * 
 */
public class NSJedisCluster implements IJedisCluster {

	private static final Logger log = LoggerFactory.getLogger(NSJedisCluster.class);

	/**
	 * 默认超时时间
	 */
	public static final int DEFALT_TIMEOUT = 6000;

	/**
	 * 默认最大重定向数
	 */
	public static final int DEFALT_MAX_REDIRECTIONS = 5;

	/**
	 * 默认最大连接数
	 */
	public static final int DEFALT_MAX_TOTAL = 5;

	/**
	 * 默认最大空闲数
	 */
	public static final int DEFALT_MAX_IDLE = 5;

	/**
	 * 默认最小空闲数
	 */
	public static final int DEFALT_MIN_IDLE = 5;

	/**
	 * 主机关键字
	 */
	public static final String KEY_HOST = "host";

	/**
	 * 端口关键字
	 */
	public static final String KEY_PORT = "port";

	/**
	 * 超时时间
	 */
	private int timeout;

	/**
	 * 最大重定向数
	 */
	private int maxRedirections;

	/**
	 * 集群节点集合
	 */
	private List<Map<String, String>> jedisClusterNode;

	/**
	 * 线程池总数
	 */
	private int poolMaxTotal;

	/**
	 * 最大空闲数
	 */
	private int poolMaxIdle;

	/**
	 * 最小空闲数
	 */
	private int poolMinIdle;

	/**
	 * jedis集群对象
	 */
	private JedisCluster jedis;

	public NSJedisCluster() {
	}

	/**
	 * 初始化
	 */
	public void init() {

		log.debug("--开始初始化redis集群--");
		setDefalt();
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(this.poolMaxIdle);
		poolConfig.setMinIdle(this.poolMinIdle);
		poolConfig.setMaxTotal(this.poolMaxTotal);
		List<Map<String, String>> nodes = this.jedisClusterNode;
		Set<HostAndPort> clusterNode = new HashSet<HostAndPort>();
		for (Map<String, String> map : nodes) {
			clusterNode.add(new HostAndPort(map.get(KEY_HOST), Integer.parseInt(map.get(KEY_PORT))));
		}
		this.jedis = new JedisCluster(clusterNode, this.timeout, this.maxRedirections, poolConfig);
		log.debug("--结束初始化redis集群--");
	}

	/**
	 * 获取redis集群对象
	 */
	private void setDefalt() {

		if (this.poolMaxIdle == 0) {
			this.poolMaxIdle = DEFALT_MAX_IDLE;
		}
		if (this.maxRedirections == 0) {
			this.maxRedirections = DEFALT_MAX_REDIRECTIONS;
		}
		if (this.poolMaxTotal == 0) {
			this.poolMaxTotal = DEFALT_MAX_TOTAL;
		}
		if (this.poolMinIdle == 0) {
			this.poolMinIdle = DEFALT_MIN_IDLE;
		}
	}

	/**
	 * 获取redis集群对象
	 */
	@Override
	public JedisCluster getJedis() {

		return this.jedis;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxRedirections() {
		return maxRedirections;
	}

	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public int getPoolMaxTotal() {
		return poolMaxTotal;
	}

	public void setPoolMaxTotal(int poolMaxTotal) {
		this.poolMaxTotal = poolMaxTotal;
	}

	public int getPoolMaxIdle() {
		return poolMaxIdle;
	}

	public void setPoolMaxIdle(int poolMaxIdle) {
		this.poolMaxIdle = poolMaxIdle;
	}

	public int getPoolMinIdle() {
		return poolMinIdle;
	}

	public void setPoolMinIdle(int poolMinIdle) {
		this.poolMinIdle = poolMinIdle;
	}

	public List<Map<String, String>> getJedisClusterNode() {
		return jedisClusterNode;
	}

	public void setJedisClusterNode(List<Map<String, String>> jedisClusterNode) {
		this.jedisClusterNode = jedisClusterNode;
	}

}
