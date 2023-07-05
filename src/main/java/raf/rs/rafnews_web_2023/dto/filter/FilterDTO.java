package raf.rs.rafnews_web_2023.dto.filter;


public class FilterDTO {

    private String dateOrder;
    private boolean trending;
    private int category;
    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }
    public FilterDTO() {

    }

    public void setTrending(boolean trending) {
        this.trending = trending;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
