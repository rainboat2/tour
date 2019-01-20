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

    /**
     * 通过info中的信息以生成一个Vertex对象
     * @param info  {name, popular, toilet, restArea, description, imagePath}
     * @throws IndexOutOfBoundsException 当传入的info数组长度不够的时候会产生该异常
     */
    public Vertex(String[] info) {
        this.name = info[0];
        setPopular(info[1]);
        setToilet(info[2]);
        setRestArea(info[3]);
        setDescription(info[4]);
        setImagePath(info[5]);
    }

    /**
     * 判断本节点的名称或描述中是否包含传入的关键字
     * @param keyWork 关键字
     * @return true  匹配到关键字
     *         false 未匹配到关键字
     */
    public boolean isContains(String keyWork) {
        return name.contains(keyWork) || description.contains(keyWork);
    }

    public String getName() {
        return name;
    }

    // 图通过名称来查找结点索引，随意修改名称会导致错误
    void setName(String name){
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
        if (imagePath.equals(""))
            imagePath = "img\\default.jpg";
        this.imagePath = imagePath;
    }

    /**
     * 将字符串转化为数字，
     * 若是不可转化的字符串，则将其设置为 0
     * @param s 需要转为数字的字符串
     * @return 转化的结果
     */
    private int getNumber(String s) {
        int number;
        try {
            number = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            number = 0;
        }
        return number;
    }

    public String toString() {
        return String.format("%s^%s^%s^%s^%s^%s", name, popular, restArea, toilet, description, imagePath);
    }
}
