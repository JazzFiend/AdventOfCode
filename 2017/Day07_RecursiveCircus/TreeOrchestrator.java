import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class TreeOrchestrator {
  private static List<String> parseTreeFile(String filename) {
    ArrayList<String> treeSetup = new ArrayList<String>();
    try {
      String line;
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) {
        treeSetup.add(line);
      }
    } catch(IOException ex) {
      System.out.println(ex);
    }
    return treeSetup;
  }

  private static Collection<Node> buildTree(Collection<String> nodeStrings) {
    HashSet<Node> nodeCollection = new HashSet<Node>();
    for(String nodeString : nodeStrings) {
      String[] nodeStringTokenized = nodeString.split(" ");
      String nodeName = nodeStringTokenized[0];
      int weight = Integer.parseInt(nodeStringTokenized[1].substring(1, nodeStringTokenized[1].length() - 1));
      nodeCollection.add(new Node(nodeName, weight));
    }

    for(String nodeString : nodeStrings) {
      String[] nodeStringTokenized = nodeString.split(" ");
      Node currentNode = findNode(nodeCollection, nodeStringTokenized[0]);
      Collection<Node> childNodes = new HashSet<Node>();
      for(int i = 3; i < nodeStringTokenized.length; i++) {
        Node child = findNode(nodeCollection, nodeStringTokenized[i].replace(",", ""));
        childNodes.add(child);
        child.setParent(currentNode);
      }
      if(!childNodes.isEmpty()) {
        currentNode.addChildren(childNodes);
      }
    }
    return nodeCollection;
  }

  private static Node findNode(Collection<Node> nodeCollection, String nodeName) {
    for(Node n : nodeCollection) {
      if(n.getName().equals(nodeName)) {
        return n;
      }
    }
    return null;
  }

  private static Node findRoot(Collection<Node> tree) {
    for(Node n : tree) {
      if(n.getParent() == null) {
        return n;
      }
    }
    return null;
  }

  private static boolean allElementsEqual(List<Integer> list) {
    int value = list.get(0);
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i) != value) {
        return false;
      }
    }
    return true;
  }

  private static int getTreeWeight(Node rootNode) {
    if(!rootNode.hasChildren()) {
      return rootNode.getWeight();
    }
    int totalWeight = 0;
    ArrayList<Integer> weightList = new ArrayList<Integer>();
    for(Node child : rootNode.getChildren()) {
      int treeWeight = getTreeWeight(child);
      weightList.add(treeWeight);
      totalWeight += treeWeight;
    }
    if(!allElementsEqual(weightList)) {
      for(Node child : rootNode.getChildren()) {
        System.out.print(String.format("%s -> %d\n", child.getName(), getTreeWeight(child)));
      }
      System.out.print("\n");
    }

    totalWeight += rootNode.getWeight();
    return totalWeight;
  }

  public static void main(String[] args) {
    final String treeSetupFilename = "input.txt";
    List<String> treeSetup = parseTreeFile(treeSetupFilename);
    Collection<Node> tree = buildTree(treeSetup);
    getTreeWeight(findRoot(tree));
  }
}
