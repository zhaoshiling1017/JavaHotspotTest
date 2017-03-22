package com.ducetech.pool;

import org.apache.commons.pool.PoolableObjectFactory;

public class TestPoolableFactory implements PoolableObjectFactory<BaseObject> {
	//重新初始化实例返回池  
    @Override  
    public void activateObject(BaseObject arg0) throws Exception {  
        arg0.setActive(true);  
    }  
  
    //销毁被破坏的实例  
    @Override  
    public void destroyObject(BaseObject arg0) throws Exception {  
        arg0 = null;  
    }  
  
    //创建一个实例到对象池  
    @Override  
    public BaseObject makeObject() throws Exception {  
        BaseObject bo = new BaseObject();  
        return bo;  
    }  
  
    //取消初始化实例返回到空闲对象池  
    @Override  
    public void passivateObject(BaseObject arg0) throws Exception {  
        arg0.setActive(false);  
    }  
  
    //验证该实例是否安全  
    @Override  
    public boolean validateObject(BaseObject arg0) {  
        if(arg0.isActive())  
            return true;  
        else  
            return false;  
    }
}
