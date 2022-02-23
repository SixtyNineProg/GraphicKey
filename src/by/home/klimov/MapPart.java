package by.home.klimov;

public class MapPart {
    private final int num;
    private final int corX;
    private final int corY;
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

    public int getCorY() {
        return corY;
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
}
