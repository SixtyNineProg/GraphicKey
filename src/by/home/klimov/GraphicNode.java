package by.home.klimov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphicNode {
    private final int num;
    private final int corX;
    private final int corY;

    private List<GraphicNode> allowedTransitions;
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

    public void addAllowedTransitions() {
        ArrayList<GraphicNode> allowedTransactions
                = new ArrayList<>();
        for (MapPart mapPart : map) {
            if (!mapPart.isSelected()) {
                int differenceX = mapPart.getCorX() - corX;
                int differenceY = mapPart.getCorY() - corY;

                if (differenceX == 0 && (differenceY > 1 || differenceY < -1)) {

                    MapPart tempMapPart;
                    if (differenceY < 0) tempMapPart = findInMap(corX, corY - 1);
                    else tempMapPart = findInMap(corX, corY + 1);

                    if (tempMapPart.isSelected())
                        allowedTransactions.add(new GraphicNode(
                                mapPart.getNum(),
                                mapPart.getCorX(),
                                mapPart.getCorY(),
                                getCloneMap()));
                    continue;
                }
                if (differenceY == 0 && (differenceX > 1 || differenceX < -1)) {

                    MapPart tempMapPart;
                    if (differenceX < 0) tempMapPart = findInMap(corX - 1, corY);
                    else tempMapPart = findInMap(corX + 1, corY);

                    if (tempMapPart.isSelected())
                        allowedTransactions.add(new GraphicNode(
                                mapPart.getNum(),
                                mapPart.getCorX(),
                                mapPart.getCorY(),
                                getCloneMap()));
                    continue;
                }
                if ((differenceX == 2 || differenceX == -2) && (differenceY == 2 || differenceY == -2)) {

                    MapPart tempMapPart;
                    if (differenceY == 2 && differenceX == -2) tempMapPart = findInMap(corX - 1, corY + 1);
                    else if (differenceY == -2 && differenceX == 2) tempMapPart = findInMap(corX + 1, corY - 1);
                    else if (differenceY == -2) tempMapPart = findInMap(corX - 1, corY - 1);
                    else tempMapPart = findInMap(corX + 1, corY + 1);

                    if (tempMapPart.isSelected())
                        allowedTransactions.add(new GraphicNode(
                                mapPart.getNum(),
                                mapPart.getCorX(),
                                mapPart.getCorY(),
                                getCloneMap()));
                    continue;
                }
                allowedTransactions.add(new GraphicNode(
                        mapPart.getNum(),
                        mapPart.getCorX(),
                        mapPart.getCorY(),
                        getCloneMap()));
            }
        }
        this.allowedTransitions = allowedTransactions;
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
