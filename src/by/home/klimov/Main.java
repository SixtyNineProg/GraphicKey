package by.home.klimov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<MapPart> map = new ArrayList<>(Arrays.asList(
                new MapPart(1, 0, 0), new MapPart(2, 0, 1), new MapPart(3, 0, 2),
                new MapPart(4, 1, 0), new MapPart(5, 1, 1), new MapPart(6, 1, 2),
                new MapPart(7, 2, 0), new MapPart(8, 2, 1), new MapPart(9, 2, 2)));

        GraphicNode rootNode = new GraphicNode(1, 0, 0);

        rootNode.setMap(map);
        rootNode.updateMap();
        rootNode.addAllowedTransitions();

        doStep(rootNode);

        System.out.println("Hello");
    }

    public static void doStep(GraphicNode rootNode) {
        List<GraphicNode> rootAllowedTransaction = rootNode.getAllowedTransitions();
        if (rootAllowedTransaction.size() == 0) return;
        for (GraphicNode tempGraphicNode : rootAllowedTransaction) {
            tempGraphicNode.updateMap();
            tempGraphicNode.addAllowedTransitions();
            tempGraphicNode.printMap();
            if (tempGraphicNode.getAllowedTransitions().size() != 0) {
                doStep(tempGraphicNode);
            } else return;
        }
    }
}

//    List<TreeNode<GraphicNode>> children = rootNode.getChildren();
//        for (TreeNode<GraphicNode> treeNode : children) {
//        GraphicNode childGraphicNode = treeNode.getData();
//        List<MapPart> allowedTransaction = childGraphicNode.getAllowedTransitions();
//        for (MapPart mapPart : allowedTransaction) {
//        GraphicNode tempGraphicNode =
//        new GraphicNode(
//        mapPart.getNum(),
//        mapPart.getCorX(),
//        mapPart.getCorY(),
//        treeNode.getData().getCloneMap());
//        tempGraphicNode.updateMap();
//        tempGraphicNode.addAllowedTransitions();
//
//        tempGraphicNode.printMap();
//
//        treeNode.addChild(tempGraphicNode);
//
//        if (tempGraphicNode.getAllowedTransitions().size() != 0) {
//        doStep();
//        }
//        }
//        }
