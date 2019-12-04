import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.add(15);
        tree.add(23);
        tree.add(9);
        tree.add(11);
        tree.add(2);
        tree.add(20);
        tree.add(38);
        LinkedListOfInteger list = tree.positionsCentral();      
        System.out.println(list.toString());
        RedBlackTree tree2 = tree.clone();
        list = tree2.positionsCentral();
        System.out.println(list.toString());
    }   
}
