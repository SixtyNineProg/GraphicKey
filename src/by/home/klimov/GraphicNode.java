package by.home.klimov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphicNode {
    private final int num;
    private final int corX;
    private final int corY;

    private final List<GraphicNode> allowedTransitions = new ArrayList<>();
    private List<MapPart> map;
    private List<Integer> history = new ArrayList<>();

    public GraphicNode(int num, int corY, int corX) {
        this.num = num;
        this.corX = corX;
        this.corY = corY;
    }

    public GraphicNode(int num, int corY, int corX, List<MapPart> map, List<Integer> history) {
        this.num = num;
        this.corX = corX;
        this.corY = corY;
        this.map = map;
        this.history = history;
    }

    public void updateMap() {
        map.stream()
                .filter(mapPart -> num == mapPart.getNum() && corX == mapPart.getCorX() && corY == mapPart.getCorY())
                .findAny().ifPresent(tempMapPart -> tempMapPart.setSelected(true));

    }

    public MapPart findInMap(int corX, int corY) {
        return map.stream()
                .filter(mapPart -> corX == mapPart.getCorX() && corY == mapPart.getCorY())
                .findAny()
                .orElse(null);
    }

    public void addAllowedTransitions() {
        for (MapPart mapPart : map) {
            if (!mapPart.isSelected()) {
                int differenceX = mapPart.getCorX() - corX;
                int differenceY = mapPart.getCorY() - corY;

                if (differenceX == 0 && (differenceY > 1 || differenceY < -1)) {

                    MapPart tempMapPart;
                    if (differenceY < 0) tempMapPart = findInMap(corX, corY - 1);
                    else tempMapPart = findInMap(corX, corY + 1);

                    if (tempMapPart.isSelected()) {
                        addAllowedTransition(mapPart);
                    }
                    continue;
                }
                if (differenceY == 0 && (differenceX > 1 || differenceX < -1)) {

                    MapPart tempMapPart;
                    if (differenceX < 0) tempMapPart = findInMap(corX - 1, corY);
                    else tempMapPart = findInMap(corX + 1, corY);

                    if (tempMapPart.isSelected())
                        addAllowedTransition(mapPart);
                    continue;
                }
                if ((differenceX == 2 || differenceX == -2) && (differenceY == 2 || differenceY == -2)) {

                    MapPart tempMapPart;
                    if (differenceY == 2 && differenceX == -2) tempMapPart = findInMap(corX - 1, corY + 1);
                    else if (differenceY == -2 && differenceX == 2) tempMapPart = findInMap(corX + 1, corY - 1);
                    else if (differenceY == -2) tempMapPart = findInMap(corX - 1, corY - 1);
                    else tempMapPart = findInMap(corX + 1, corY + 1);

                    if (tempMapPart.isSelected())
                        addAllowedTransition(mapPart);
                    continue;
                }
                addAllowedTransition(mapPart);
            }
        }
    }

    private void addAllowedTransition(MapPart mapPart) {
        GraphicNode graphicNode = new GraphicNode(
                mapPart.getNum(),
                mapPart.getCorY(),
                mapPart.getCorX(),
                getCloneMap(),
                new ArrayList<>(history));
        allowedTransitions.add(graphicNode);
    }

    public void printMap() {
        int i = 1;
        System.out.println();
        for (MapPart mapPart : map) {
            System.out.print(mapPart.isSelected() ? 1 : 0);
            if (i % 3 == 0) System.out.println();
            i++;
        }
    }

    public List<GraphicNode> getAllowedTransitions() {
        return allowedTransitions;
    }

    public void setMap(List<MapPart> map) {
        this.map = map;
    }

    public List<Integer> getHistory() {
        return history;
    }

    public int getNum() {
        return num;
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
