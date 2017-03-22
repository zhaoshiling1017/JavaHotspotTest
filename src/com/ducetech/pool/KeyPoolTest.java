package com.ducetech.pool;

import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;

public class KeyPoolTest {
	public static void main(String[] args) {  
        
        BaseObject bo = null;  
        BaseObject bo1 = null;  
        BaseObject bo2 = null;  
          
        KeyedPoolableObjectFactory<String, BaseObject> keyFactory = new TestKeyPoolableFactory();  
        GenericKeyedObjectPool<String, BaseObject> keyPool = new GenericKeyedObjectPool<String, BaseObject>(keyFactory);  
        //keyPool.setLifo(false);  
        try {  
            //这里添加池对象，只需要传入key就会默认调用makeObject()方法创建一个对象  
            keyPool.addObject("一级");  
            keyPool.addObject("二级");  
            //这里注释掉，不初始创建这个键的池对象  
            //keyPool.addObject("三级");  
            System.out.println("池中处于闲置状态的实例pool.getNumIdle()："+keyPool.getNumIdle());  
            for (int i = 0; i < 5; i++) {  
                //从池里取对象  
                bo = keyPool.borrowObject("一级");  
                bo.setNum(bo.getNum()+1);  
                System.out.println("一级"+i+"-------"+bo+"-------"+bo.getNum());  
                  
                bo1 = keyPool.borrowObject("二级");  
                bo1.setNum(bo1.getNum()+1);  
                System.out.println("二级"+i+"-------"+bo1+"-------"+bo1.getNum());  
                //上边注释掉的那行代码，这里取对象的时候如果没有闲置对象，也会默认去创建一个key="三级"的池对象  
                bo2 = keyPool.borrowObject("三级");  
                bo2.setNum(bo2.getNum()+1);  
                System.out.println("三级"+i+"-------"+bo2+"-------"+bo2.getNum());  
                  
                if(i<3) {  
                    //用完之后归还对象  
                    keyPool.returnObject("一级", bo);  
                    keyPool.returnObject("二级", bo1);  
                    keyPool.returnObject("三级", bo2);  
                    System.out.println("归还对象！！！");  
                }  
            }  
            //当前池里的实例数量  
            System.out.println("池中所有在用实例pool.getNumActive()："+keyPool.getNumActive());  
            //当前池里的处于闲置状态的实例  
            System.out.println("池中处于闲置状态的实例pool.getNumIdle()："+keyPool.getNumIdle());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        //这里就不写finally了，偷懒了，这里应该关闭池的  
    }  
}
