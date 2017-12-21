package com.example.lenovo.jd.bean;

import java.util.List;



public class CarBean {
    /**
     * msg : 请求成功
     * code : 0
     * data : [{"list":[{"bargainPrice":6666,"createtime":"2017-10-10T16:01:31","detailUrl":"https://item.m.jd.com/product/5089273.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t8284/363/1326459580/71585/6d3e8013/59b857f2N6ca75622.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t9346/182/1406837243/282106/68af5b54/59b8480aNe8af7f5c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t8434/54/1359766007/56140/579509d9/59b85801Nfea207db.jpg!q70.jpg","num":2,"pid":46,"price":234,"pscid":39,"selected":0,"sellerid":2,"subhead":"【iPhone新品上市】新一代iPhone，让智能看起来更不一样","title":"Apple iPhone 8 Plus (A1864) 64GB 金色 移动联通电信4G手机"},{"bargainPrice":399,"createtime":"2017-10-02T15:20:02","detailUrl":"https://item.m.jd.com/product/1439822107.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t5887/201/859509257/69994/6bde9bf6/59224c24Ne854e14c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg","num":7,"pid":81,"price":699,"pscid":85,"selected":0,"sellerid":2,"subhead":"满2件，总价打6.50折","title":"Gap男装 休闲舒适简约水洗五袋直筒长裤紧身牛仔裤941825 深灰色 33/32(175/84A)"}],"sellerName":"商家2","sellerid":"2"},{"list":[{"bargainPrice":399,"createtime":"2017-10-14T21:38:26","detailUrl":"https://item.m.jd.com/product/1439822107.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t5887/201/859509257/69994/6bde9bf6/59224c24Ne854e14c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg","num":1,"pid":83,"price":444,"pscid":85,"selected":0,"sellerid":4,"subhead":"满2件，总价打6.50折","title":"Gap男装 休闲舒适简约水洗五袋直筒长裤紧身牛仔裤941825 深灰色 33/32(175/84A)"}],"sellerName":"商家4","sellerid":"4"},{"list":[{"bargainPrice":399,"createtime":"2017-10-03T23:53:28","detailUrl":"https://item.m.jd.com/product/1439822107.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t5887/201/859509257/69994/6bde9bf6/59224c24Ne854e14c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg","num":1,"pid":87,"price":888,"pscid":85,"selected":0,"sellerid":8,"subhead":"满2件，总价打6.50折","title":"Gap男装 休闲舒适简约水洗五袋直筒长裤紧身牛仔裤941825 深灰色 33/32(175/84A)"}],"sellerName":"商家8","sellerid":"8"},{"list":[{"bargainPrice":111.99,"createtime":"2017-10-14T21:39:05","detailUrl":"https://item.m.jd.com/product/4719303.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t9004/210/1160833155/647627/ad6be059/59b4f4e1N9a2b1532.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7504/338/63721388/491286/f5957f53/598e95f1N7f2adb87.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7441/10/64242474/419246/adb30a7d/598e95fbNd989ba0a.jpg!q70.jpg","num":6,"pid":1,"price":118,"pscid":1,"selected":0,"sellerid":17,"subhead":"每个中秋都不能简单，无论身在何处，你总需要一块饼让生活更圆满，京东月饼让爱更圆满京东自营，闪电配送，更多惊喜，快用手指戳一下","title":"北京稻香村 稻香村中秋节月饼 老北京月饼礼盒655g"}],"sellerName":"商家17","sellerid":"17"}]
     */
    private boolean isChoosed;   //商品是否在购物车中被选中
    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

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
         * list : [{"bargainPrice":6666,"createtime":"2017-10-10T16:01:31","detailUrl":"https://item.m.jd.com/product/5089273.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t8284/363/1326459580/71585/6d3e8013/59b857f2N6ca75622.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t9346/182/1406837243/282106/68af5b54/59b8480aNe8af7f5c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t8434/54/1359766007/56140/579509d9/59b85801Nfea207db.jpg!q70.jpg","num":2,"pid":46,"price":234,"pscid":39,"selected":0,"sellerid":2,"subhead":"【iPhone新品上市】新一代iPhone，让智能看起来更不一样","title":"Apple iPhone 8 Plus (A1864) 64GB 金色 移动联通电信4G手机"},{"bargainPrice":399,"createtime":"2017-10-02T15:20:02","detailUrl":"https://item.m.jd.com/product/1439822107.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t5887/201/859509257/69994/6bde9bf6/59224c24Ne854e14c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg","num":7,"pid":81,"price":699,"pscid":85,"selected":0,"sellerid":2,"subhead":"满2件，总价打6.50折","title":"Gap男装 休闲舒适简约水洗五袋直筒长裤紧身牛仔裤941825 深灰色 33/32(175/84A)"}]
         * sellerName : 商家2
         * sellerid : 2
         */

        private String sellerName;
        private String sellerid;
        private List<ListBean> list;

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * bargainPrice : 6666.0
             * createtime : 2017-10-10T16:01:31
             * detailUrl : https://item.m.jd.com/product/5089273.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends
             * images : https://m.360buyimg.com/n0/jfs/t8284/363/1326459580/71585/6d3e8013/59b857f2N6ca75622.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t9346/182/1406837243/282106/68af5b54/59b8480aNe8af7f5c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t8434/54/1359766007/56140/579509d9/59b85801Nfea207db.jpg!q70.jpg
             * num : 2
             * pid : 46
             * price : 234.0
             * pscid : 39
             * selected : 0
             * sellerid : 2
             * subhead : 【iPhone新品上市】新一代iPhone，让智能看起来更不一样
             * title : Apple iPhone 8 Plus (A1864) 64GB 金色 移动联通电信4G手机
             */

            private double bargainPrice;
            private String createtime;
            private String detailUrl;
            private String images;
            private int num;
            private int pid;
            private double price;
            private int pscid;
            private int selected;
            private int sellerid;
            private String subhead;
            private String title;

            public double getBargainPrice() {
                return bargainPrice;
            }

            public void setBargainPrice(double bargainPrice) {
                this.bargainPrice = bargainPrice;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(String detailUrl) {
                this.detailUrl = detailUrl;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getPscid() {
                return pscid;
            }

            public void setPscid(int pscid) {
                this.pscid = pscid;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }

            public int getSellerid() {
                return sellerid;
            }

            public void setSellerid(int sellerid) {
                this.sellerid = sellerid;
            }

            public String getSubhead() {
                return subhead;
            }

            public void setSubhead(String subhead) {
                this.subhead = subhead;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
