import java.util.LinkedList;


public class App {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.add(15);
        tree.add(23);
        tree.add(9);
        LinkedListOfInteger list = tree.positionsCentral();      
        System.out.println("tree :"+list.toString());
        System.out.println("tree- size :"+tree.size());
        
        String a ="";
        for(int i = 0; i < list.size(); i++){
            a += list.get(i)+ "\n";
        }
        System.out.println(a);
        /*
        RedBlackTree tree2 = tree.clone();

        list = tree2.positionsCentral();
        System.out.println("tree2:"+list.toString());
        System.out.println("tree2 - size :"+tree.size());
        */
    }   
}
