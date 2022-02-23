package by.home.klimov;

public class MapPart {
    private int num;
    private int corX;
    private int corY;
    private boolean selected = false;

    public MapPart(int num, int corY, int corX) {
        this.num = num;
        this.corX = corX;
        this.corY = corY;
    }

    public MapPart(MapPart mapPart) {
        this.num = mapPart.getNum();
        this.corY = mapPart.getCorY();
        this.corX = mapPart.getCorX();
        this.selected = mapPart.isSelected();
    }

    public int getCorX() {
        return corX;
    }

    public void setCorX(int corX) {
        this.corX = corX;
    }

    public int getCorY() {
        return corY;
    }

    public void setCorY(int corY) {
        this.corY = corY;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
