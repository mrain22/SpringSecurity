package cn.mrain22.security.mySecurity.Properties;

public class ImageCodeProperties {

//    仅以这几个属性做实例。
    private String color = "red";
    private String width = "120";
    private String height = "30";
    private String length = "6";
//    url
    private String url;


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
