import java.util.Collection;
import java.util.HashSet;

public class Node {
  private String name;
  private int weight;
  private Collection<Node> children;
  private Node parent;

  public Node(String nameArg, int weightArg) {
    name = nameArg;
    weight = weightArg;
    children = new HashSet<Node>();
    parent = null;
  }

  public String getName() {
    return name;
  }

  public int getWeight() {
    return weight;
  }

  public Collection<Node> getChildren() {
    return children;
  }

  public void addChildren(Collection<Node> nodesToAdd) {
    for(Node n : nodesToAdd) {
      children.add(n);
    }
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parentNode) {
    parent = parentNode;
  }

  public boolean hasChildren() {
    return !children.isEmpty();
  }

  public String toString() {
    StringBuilder toReturn = new StringBuilder();
    toReturn.append(name);
    toReturn.append("\n");
    toReturn.append(Integer.toString(weight));
    toReturn.append("\n");
    for(Node child : children) {
      toReturn.append(child.getName());
      toReturn.append("\n");
    }
    return toReturn.toString();
  }
}
