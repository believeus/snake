package cn.togogo.main;

import java.util.ArrayList;

/**Begin Name:wuqiwei QQ:1058633117 2017-5-24 使用生产者和消费者模式去创建蛋蛋*/
public class EggFactory {

	private ArrayList<Egg> items = new ArrayList<Egg>();

	public synchronized void buildEgg() {
		try {
			if (items.size() == 1 ) {
				wait();
			}
			items.add(new Egg());
			notify();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public synchronized Egg getEgg() {
		try {
			if (items.size() == 0) {
				wait();
			}
			Egg egg = items.get(0);
			if (!egg.isLive) {
				items.clear();// 清空盘子
				notify();
			}
			return egg;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

}
/**End Name:wuqiwei QQ:1058633117 2017-5-24 使用生产者和消费者模式去创建蛋蛋*/