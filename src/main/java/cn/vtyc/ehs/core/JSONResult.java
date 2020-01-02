package cn.vtyc.ehs.core;

/**
 * Created by fonlin on 2017/9/8.
 * 可以装填对象返回的Result
 */
public class JSONResult extends Result {

    private Object obj;
    private Object obj2;
    private Object obj3;

    public JSONResult() {
        super();
    }

    public JSONResult(int code, String msg) {
        super(code, msg);
    }

    public JSONResult(int code, String msg, Object obj) {
        super(code, msg);
        this.obj = obj;
    }

    public JSONResult(ErrorCode httpCode, Object obj) {
        super(httpCode);
        this.obj = obj;
    }


    public JSONResult(Object obj) {
        super(ErrorCode.OK);
        this.obj = obj;
    }
    public JSONResult(Object obj,Object obj2) {
        super(ErrorCode.OK);
        this.obj = obj;
        this.obj2 = obj2;
    }

    public JSONResult(Object obj,Object obj2,Object obj3) {
        super(ErrorCode.OK);
        this.obj = obj;
        this.obj2 = obj2;
        this.obj3 = obj3;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getObj2() {
        return obj2;
    }

    public void setObj2(Object obj2) {
        this.obj2 = obj2;
    }

    public Object getObj3() {
        return obj3;
    }

    public void setObj3(Object obj3) {
        this.obj3 = obj3;
    }
}
