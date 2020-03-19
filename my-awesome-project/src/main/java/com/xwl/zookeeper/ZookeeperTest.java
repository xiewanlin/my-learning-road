package com.xwl.zookeeper;

import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @Author: xiewanlin
 * @Date: 2020/3/19
 */
public class ZookeeperTest implements Watcher {

  private static CountDownLatch countDownLatch = new CountDownLatch(1);
  private static ZooKeeper zooKeeper = null;
  private static Stat stat = new Stat();

  public static void main(String[] args) throws Exception {
    String path = "/username";
    zooKeeper = new ZooKeeper("192.168.248.129:2181", 5000, new ZookeeperTest());
    countDownLatch.await();
    System.out.println(new String(zooKeeper.getData(path, true, stat)));

    Thread.sleep(Integer.MAX_VALUE);
  }

  public void process(WatchedEvent event) {
    if (KeeperState.SyncConnected == event.getState()) { //zk连接成功通知事件
      if (EventType.None == event.getType() && event.getPath() == null) {
        countDownLatch.countDown();
      } else if (event.getType() == EventType.NodeDataChanged) { //zk目录节点数据变化通知事件
        try {
          System.out.println("配置更新：" + new String(zooKeeper.getData(event.getPath(), true, stat)));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
