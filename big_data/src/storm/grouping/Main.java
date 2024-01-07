package com.sxt.storm.grouping;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("spout", new MySpout(), 1);

		// shuffleGrouping其实就是随机往下游去发,不自觉的做到了负载均衡
//		builder.setBolt("bolt", new MyBolt(), 2).shuffleGrouping("spout");

		// fieldsGrouping其实就是MapReduce里面理解的Shuffle,根据fields求hash来取模
//		builder.setBolt("bolt", new MyBolt(), 2).fieldsGrouping("spout", new Fields("session_id"));

		// 只往一个里面发,往taskId小的那个里面去发送
//		builder.setBolt("bolt", new MyBolt(), 2).globalGrouping("spout");

		// 等于shuffleGrouping
//		builder.setBolt("bolt", new MyBolt(), 2).noneGrouping("spout");

		// 广播
		builder.setBolt("bolt", new MyBolt(), 2).allGrouping("spout");

		// Map conf = new HashMap();
		// conf.put(Config.TOPOLOGY_WORKERS, 4);
		Config conf = new Config();
		conf.setDebug(false);
		conf.setMessageTimeoutSecs(30);

		if (args.length > 0) {
			try {
				StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
			} catch (AlreadyAliveException e) {
				e.printStackTrace();
			} catch (InvalidTopologyException e) {
				e.printStackTrace();
			}
		} else {
			LocalCluster localCluster = new LocalCluster();
			localCluster.submitTopology("mytopology", conf, builder.createTopology());
		}

	}

}
