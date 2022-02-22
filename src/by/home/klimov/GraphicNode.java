package by.home.klimov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphicNode {
    private int num;
    private int corX;
    private int corY;

    private List<MapPart> allowedTransitions;
    private List<MapPart> map;

    public GraphicNode(int num, int corX, int corY) {
        this.num = num;
        this.corX = corX;
        this.corY = corY;
    }

    public GraphicNode(int num, int corX, int corY, List<MapPart> map) {
        this.num = num;
        this.corX = corX;
        this.corY = corY;
        this.map = map;
    }

    public void updateMap() {
        Objects.requireNonNull(map.stream()
                        .filter(mapPart -> num == mapPart.getNum() && corX == mapPart.getCorX() && corY == mapPart.getCorY())
                        .findAny()
                        .orElse(null))
                .setSelected(true);
    }

    public MapPart findInMap(int corX, int corY) {
        return map.stream()
                .filter(mapPart -> corX == mapPart.getCorX() && corY == mapPart.getCorY())
                .findAny()
                .orElse(null);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public List<MapPart> getAllowedTransitions() {
        return allowedTransitions;
    }

    public void setAllowedTransitions(ArrayList<MapPart> allowedЕTransitions) {
        this.allowedTransitions = allowedЕTransitions;
    }

    public List<MapPart> getMap() {
        return map;
    }

    public void setMap(ArrayList<MapPart> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphicNode that = (GraphicNode) o;
        return num == that.num && corX == that.corX && corY == that.corY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, corX, corY);
    }

    public List<MapPart> getCloneMap() {
        List<MapPart> clonedMap = new ArrayList<>(map.size());
        for (MapPart mapPart : map) clonedMap.add(new MapPart(mapPart));
        return clonedMap;
    }
}
