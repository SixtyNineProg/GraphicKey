package by.home.klimov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int counter = 0;

    public static void main(String[] args) {
        List<MapPart> map = new ArrayList<>(Arrays.asList(
                new MapPart(1, 0, 0), new MapPart(2, 0, 1), new MapPart(3, 0, 2),
                new MapPart(4, 1, 0), new MapPart(5, 1, 1), new MapPart(6, 1, 2),
                new MapPart(7, 2, 0), new MapPart(8, 2, 1), new MapPart(9, 2, 2)));

        List<GraphicNode> startNodes = new ArrayList<>(Arrays.asList(
                new GraphicNode(1, 0, 0), new GraphicNode(2, 0, 1), new GraphicNode(3, 0, 2),
                new GraphicNode(4, 1, 0), new GraphicNode(5, 1, 1), new GraphicNode(6, 1, 2),
                new GraphicNode(7, 2, 0), new GraphicNode(8, 2, 1), new GraphicNode(9, 2, 2)));

        for (GraphicNode graphicNode : startNodes) {

            List<MapPart> clonedMap = new ArrayList<>(map.size());
            for (MapPart mapPart : map) clonedMap.add(new MapPart(mapPart));

            graphicNode.setMap(clonedMap);
            graphicNode.getHistory().add(graphicNode.getNum());
            graphicNode.updateMap();
            graphicNode.addAllowedTransitions();

            doStep(graphicNode);
        }

        System.out.println(counter);
    }

    public static void doStep(GraphicNode rootNode) {
        List<GraphicNode> rootAllowedTransaction = rootNode.getAllowedTransitions();
        if (rootAllowedTransaction.size() == 0) return;
        for (GraphicNode tempGraphicNode : rootAllowedTransaction) {
            tempGraphicNode.updateMap();
            tempGraphicNode.getHistory().add(tempGraphicNode.getNum());
            tempGraphicNode.addAllowedTransitions();

            List<Integer> history = tempGraphicNode.getHistory();
            if (history.size() >= 4) {
                CustomFileWriter.writeToFile("out.txt", history.toString());
                counter++;
            }

            if (tempGraphicNode.getAllowedTransitions().size() != 0) {
                doStep(tempGraphicNode);
            } else return;
        }
    }
}
