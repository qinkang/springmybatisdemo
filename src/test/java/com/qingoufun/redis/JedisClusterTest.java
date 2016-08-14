package com.qingoufun.redis;

import com.qingofun.redis.IJedisCluster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisCluster;

/**
 * Created by qinkang on 16/8/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class JedisClusterTest {
    @Autowired
    private IJedisCluster jedisCluster;

    @Test
    public void testJedisCluster() {
        JedisCluster jedis = jedisCluster.getJedis();

        System.out.println("Testing Start-----redis String-----");
        jedis.set("java_version", "1.7");
        System.out.println(jedis.get("java_version"));
        jedis.incr("x");
        System.out.println(jedis.get("x"));
        System.out.println("Testing End-----redis String-----");

        /**
         * A list is a series of ordered values. Some of the important commands for interacting
         * with lists are RPUSH, LPUSH, LLEN, LRANGE, LPOP, and RPOP.
         * You can immediately begin working with a key as a list,
         * as long as it doesn't already exist as a different type.
         */
        System.out.println("Testing Start-----redis List-----");
        jedis.lpush("testList", "b");
        jedis.lpush("testList", "a");
        jedis.rpush("testList", "c");
        System.out.println("jedis.lindex " + jedis.lindex("testList", 1));
        System.out.println("jedis.lrange " + jedis.lrange("testList", 1 , 2));
        jedis.incr("x");
        System.out.println(jedis.get("x"));
        System.out.println("Testing End-----redis List-----");

        /**
         * The next data structure that we'll look at is a set.
         * A set is similar to a list, except it does not have a specific order and each element
         * may only appear once.
         * Some of the important commands in working with sets are SADD, SREM, SISMEMBER, SMEMBERS and SUNION.
         */
        System.out.println("Testing Start-----redis Set-----");
        jedis.sadd("myset","a");
        jedis.sadd("myset","a");
        jedis.sadd("myset","b");
        jedis.sadd("myset","c");
        System.out.println(jedis.sismember("myset", "a"));
        System.out.println(jedis.smembers("myset"));
        System.out.println("Testing End-----redis Set-----");

        /**
         *  Simple strings, sets and sorted sets already get a lot done
         *  but there is one more data type Redis can handle: Hashes.
            Hashes are maps between string fields and string values,
            so they are the perfect data type to represent objects
            (eg: A User with a number of fields like name, surname, age, and so forth):
         */

        System.out.println("Testing Start-----redis Hash-----");
        jedis.hset("user:1002", "name", "秦");
        jedis.hset("user:1002", "sex", "female");
        System.out.println(jedis.hget("user:1002", "name"));
        System.out.println(jedis.hget("user:1002", "sex"));
        System.out.println(jedis.hgetAll("user:1002"));
        System.out.println("Testing End-----redis Hash-----");
    }

}
