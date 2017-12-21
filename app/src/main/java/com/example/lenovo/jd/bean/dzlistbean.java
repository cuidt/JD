package com.example.lenovo.jd.bean;

import java.util.List;


public class dzlistbean {

    /**
     * msg : 地址列表请求成功
     * code : 0
     * data : [{"addr":"","addrid":2,"mobile":123,"name":"zyx","status":0,"uid":71},{"addr":"北京市昌平区","addrid":3,"mobile":123,"name":"边走边爱","status":1,"uid":71},{"addr":"???","addrid":4,"mobile":18612991023,"name":"??","status":0,"uid":71},{"addr":"北京市","addrid":5,"mobile":18612991023,"name":"清晨","status":0,"uid":71},{"addr":"NININ","addrid":7,"mobile":14343434,"name":"NOGNEGO","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":15,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":16,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":17,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":18,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":19,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":20,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":21,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":22,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":23,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":24,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":26,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":27,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":28,"mobile":18612991023,"name":"kson0","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":29,"mobile":18612991023,"name":"kson0","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":30,"mobile":18612991023,"name":"kson147","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":31,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":39,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":42,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":43,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":50,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":51,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":52,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":53,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":59,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":61,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":64,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":73,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区1-1-1","addrid":74,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市昌平区金域国际1-1-1","addrid":78,"mobile":18612991023,"name":"kson","status":0,"uid":71},{"addr":"北京市哈哈哈啊哈哈","addrid":83,"mobile":2222333333,"name":"哈哈哈哈","status":0,"uid":71}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr :
         * addrid : 2
         * mobile : 123
         * name : zyx
         * status : 0
         * uid : 71
         */

        private String addr;
        private int addrid;
        private int mobile;
        private String name;
        private int status;
        private int uid;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAddrid() {
            return addrid;
        }

        public void setAddrid(int addrid) {
            this.addrid = addrid;
        }

        public int getMobile() {
            return mobile;
        }

        public void setMobile(int mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
