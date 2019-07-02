package com.kgc.entity;

public class HouseCondition {
    //要查询条件
    private String title;//标题
    private Integer startPrice;//起始价格
    private Integer endPrice;//结束价格
    private Integer did;//区域
    private Integer sid;//街道
    private Integer tid;//类型
    private String floorage;//面积  把面积转换成startFloorage和enfFloorage
    private Integer startFloorage;//起始面积
    private Integer endFloorage;//结束面积
    //分页
    private Integer page;
    private Integer pageSize=5;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Integer endPrice) {
        this.endPrice = endPrice;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getFloorage() {
        return floorage;
    }

    public void setFloorage(String floorage) {
        this.floorage = floorage;
        if(floorage!=null && !floorage.equals("")){
            String [] arry=floorage.split("-");
            this.setStartFloorage(Integer.parseInt(arry[0]));
            this.setEndFloorage(Integer.parseInt(arry[1]));
        }
    }

    public Integer getStartFloorage() {
        return startFloorage;
    }

    public void setStartFloorage(Integer startFloorage) {
        this.startFloorage = startFloorage;
    }

    public Integer getEndFloorage() {
        return endFloorage;
    }

    public void setEndFloorage(Integer endFloorage) {
        this.endFloorage = endFloorage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "HouseCondition{" +
                "title='" + title + '\'' +
                ", startPrice=" + startPrice +
                ", endPrice=" + endPrice +
                ", did=" + did +
                ", sid=" + sid +
                ", tid=" + tid +
                ", floorage='" + floorage + '\'' +
                ", startFloorage=" + startFloorage +
                ", endFloorage=" + endFloorage +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
