package by.home.klimov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        int[][] table = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        };
//
//        GraphicNode[][] table2 = {
//                {new GraphicNode(1, 0, 0), new GraphicNode(2, 0, 1), new GraphicNode(3, 0, 2)},
//                {new GraphicNode(4, 1, 0), new GraphicNode(5, 1, 1), new GraphicNode(6, 1, 2)},
//                {new GraphicNode(7, 2, 0), new GraphicNode(8, 2, 1), new GraphicNode(9, 2, 2)}
//        };

//        ArrayList<GraphicNode> allowedЕTransitions = new ArrayList<>(Arrays.asList(
//                new GraphicNode(1, 0, 0), new GraphicNode(2, 0, 1), new GraphicNode(3, 0, 2),
//                new GraphicNode(4, 1, 0), new GraphicNode(5, 1, 1), new GraphicNode(6, 1, 2),
//                new GraphicNode(7, 2, 0), new GraphicNode(8, 2, 1), new GraphicNode(9, 2, 2)));

        ArrayList<MapPart> map = new ArrayList<>(Arrays.asList(
                new MapPart(1, 0, 0), new MapPart(2, 0, 1), new MapPart(3, 0, 2),
                new MapPart(4, 1, 0), new MapPart(5, 1, 1), new MapPart(6, 1, 2),
                new MapPart(7, 2, 0), new MapPart(8, 2, 1), new MapPart(9, 2, 2)));

        GraphicNode rootNode = new GraphicNode(1, 0, 0);
        rootNode.setMap(map);
        rootNode.updateMap();
        addAllowedTransitions(rootNode);
        rootNode.getAllowedTransitions().forEach(mapPart -> System.out.println(mapPart.getNum()));

        TreeNode<GraphicNode> treeNode = new TreeNode<>(rootNode);
        List<MapPart> allowedTransaction = rootNode.getAllowedTransitions();
        allowedTransaction.forEach(mapPart -> {
                    GraphicNode tempGraphicNode =
                            new GraphicNode(
                                    mapPart.getNum(),
                                    mapPart.getCorX(),
                                    mapPart.getCorY(),
                                    treeNode.getData().getCloneMap());
                    tempGraphicNode.updateMap();
                    treeNode.addChild(tempGraphicNode);
                }
        );


        System.out.println("Hello");

    }

    private static void addAllowedTransitions(GraphicNode node) {
        ArrayList<MapPart> allowedTransaction = new ArrayList<>();
        for (MapPart mapPart : node.getMap()) {
            int differenceX = mapPart.getCorX() - node.getCorX();
            int differenceY = mapPart.getCorY() - node.getCorY();

            if (differenceX == 0 && differenceY == 0) continue;

            if (differenceX == 0 && differenceY > 1) {
                MapPart tempMapPart = node.findInMap(node.getCorX(), node.getCorY() + 1);
                if (tempMapPart.isSelected())
                    allowedTransaction.add(mapPart);
                continue;
            }
            if (differenceY == 0 && differenceX > 1) {
                MapPart tempMapPart = node.findInMap(node.getCorX() + 1, node.getCorY());
                if (tempMapPart.isSelected())
                    allowedTransaction.add(mapPart);
                continue;
            }
            if (differenceX > 1 && differenceY > 1) {
                MapPart tempMapPart = node.findInMap(node.getCorX() + 1, node.getCorY() + 1);
                if (tempMapPart.isSelected())
                    allowedTransaction.add(mapPart);
                continue;
            }

            allowedTransaction.add(mapPart);
        }
        node.setAllowedTransitions(allowedTransaction);
    }


}
