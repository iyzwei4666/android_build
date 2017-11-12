package com.example.chen.atguigucode.commom.okhttp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chen on 2017/8/14.
 */

public class Trailer implements Serializable{

    private List<TrailersBean> trailers;

    public List<TrailersBean> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<TrailersBean> trailers) {
        this.trailers = trailers;
    }

    public static class TrailersBean implements Serializable {
        /**
         * id : 66916
         * movieName : "请以你的名字呼唤我"预告
         * coverImg : http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg
         * movieId : 234474
         * url : http://vfx.mtime.cn/Video/2017/08/02/mp4/170802074323236656.mp4
         * hightUrl : http://vfx.mtime.cn/Video/2017/08/02/mp4/170802074323236656.mp4
         * videoTitle : 请以你的名字呼唤我 剧场版预告
         * videoLength : 129
         * rating : -1
         * type : ["剧情","爱情"]
         * summary : 男孩与房客的暧昧故事
         */

        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private int videoLength;
        private int rating;
        private String summary;
        private List<String> type;

        public TrailersBean() {
        }

        public TrailersBean(int id, String movieName, String coverImg, int movieId, String url, String hightUrl, String videoTitle, int videoLength, int rating, String summary, List<String> type) {
            this.id = id;
            this.movieName = movieName;
            this.coverImg = coverImg;
            this.movieId = movieId;
            this.url = url;
            this.hightUrl = hightUrl;
            this.videoTitle = videoTitle;
            this.videoLength = videoLength;
            this.rating = rating;
            this.summary = summary;
            this.type = type;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public int getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "TrailersBean{" +
                    "id=" + id +
                    ", movieName='" + movieName + '\'' +
                    ", coverImg='" + coverImg + '\'' +
                    ", movieId=" + movieId +
                    ", url='" + url + '\'' +
                    ", hightUrl='" + hightUrl + '\'' +
                    ", videoTitle='" + videoTitle + '\'' +
                    ", videoLength=" + videoLength +
                    ", rating=" + rating +
                    ", summary='" + summary + '\'' +
                    ", type=" + type +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "trailers=" + trailers +
                '}';
    }
}
