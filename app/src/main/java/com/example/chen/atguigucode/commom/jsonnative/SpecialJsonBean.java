package com.example.chen.atguigucode.commom.jsonnative;

import java.util.List;

/**
 * Created by chen on 2017/8/14.
 * 特殊数据对应的jsonbean key 数字
 * {
 "code": 0,
 "list": {
 "0": {
 "aid": "6008965",
 "author": "哔哩哔哩番剧",
 "coins": 170,
 "copyright": "Copy",
 "create": "2016-08-25 21:34"
 },
 "1": {
 "aid": "6008938",
 "author": "哔哩哔哩番剧",
 "coins": 404,
 "copyright": "Copy",
 "create": "2016-08-25 21:33"
 }
 }
 }
 */

public class SpecialJsonBean {


    /**
     * code : 0
     * list : {"0":{"aid":"6008965","author":"哔哩哔哩番剧","coins":170,"copyright":"Copy","create":"2016-08-25 21:34"},"1":{"aid":"6008938","author":"哔哩哔哩番剧","coins":404,"copyright":"Copy","create":"2016-08-25 21:33"}}
     */

    private int code;
    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
            /**
             * aid : 6008965
             * author : 哔哩哔哩番剧
             * coins : 170
             * copyright : Copy
             * create : 2016-08-25 21:34
             */

            private String aid;
            private String author;
            private int coins;
            private String copyright;
            private String create;

        public ListBean(String aid, String author, int coins, String copyright, String create) {
            this.aid = aid;
            this.author = author;
            this.coins = coins;
            this.copyright = copyright;
            this.create = create;
        }

        public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getCoins() {
                return coins;
            }

            public void setCoins(int coins) {
                this.coins = coins;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public String getCreate() {
                return create;
            }

            public void setCreate(String create) {
                this.create = create;
            }

        @Override
        public String toString() {
            return "ListBean{" +
                    "aid='" + aid + '\'' +
                    ", author='" + author + '\'' +
                    ", coins=" + coins +
                    ", copyright='" + copyright + '\'' +
                    ", create='" + create + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SpecialJsonBean{" +
                "code=" + code +
                ", list=" + list +
                '}';
    }
}
