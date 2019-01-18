package graph;

public class Vertex {

    private String name;
    private String description;
    private int popular;
    private int restArea;
    private int toilet;
    private String imagePath;

    public Vertex(String name, String description, int popular, int restArea, int toilet, String imagePath) {
        this.name = name;
        this.description = description;
        this.popular = popular;
        this.restArea = restArea;
        this.toilet = toilet;
        this.imagePath = imagePath;
    }

    public Vertex(String[] info){
        setName(info[0]);
        setPopular(info[1]);
        setToilet(info[2]);
        setRestArea(info[3]);
        setDescription(info[4]);
        setImagePath(info[5]);
    }

    public boolean isContains(String pattern){
        return name.contains(pattern) || description.contains(pattern);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        if (description == null)
            return "暂无描述";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = getNumber(popular);
    }

    public int getRestArea() {
        return restArea;
    }

    public void setRestArea(String restArea) {
        this.restArea = getNumber(restArea);
    }

    public int getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = getNumber(toilet);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private int getNumber(String s){
        int number;
        try {
            number = Integer.parseInt(s);
        }catch (NumberFormatException e){
            number = 0;
        }
        return number;
    }

    public String toString(){
        return String.format("%s^%s^%s^%s^%s^%s", name, popular, restArea, toilet, description, imagePath);
    }
}
