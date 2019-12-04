
public class RedBlackTree {
    
    // Classe interna privada
    private static final class Node {
        public Integer element;
        public Node father;
        public Node left;
        public Node right;
        public boolean color;
        //false => black
        //true => red
        public Node(Integer element) {
            father = null;
            left = nill;
            right = nill;
            color = true;
            this.element = element;
        }
        private Node() {
            father = null;
            color = false;
            this.element = null;
        }

        private boolean isRed(){
            return this.color;
        }
    
        private void setRed(){
            this.color = true;
        }
    
        private void setBlack(){
            this.color = false;
        }
    }

    
    // Atributos        
    private int count; //contador do numero de nodos
    private Node root; //referencia para o nodo raiz
    private final static Node nill = new Node(); //Nodo null

    

    // Metodos
    public RedBlackTree() {
        count = 0;
        root = null;
    }

    public void clear() {
        count = 0;
        root = null;      
    }
    
    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public Node get(Integer element){
        return get(element, root);
    }

    private Node get(Integer element, Node target) {

        if (element == null || target == null) {
            return null;
        }
        if(element == target.element){
            return target;
        }else if (element < target.element) {
            return get(element, target.left);
        } else {
            return get(element, target.right);
        }
    }      


    public boolean contains(Node n) {
        Node nAux = get(n.element, root);
        return (nAux == n);
    }

    
    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger list = new LinkedListOfInteger();
        positionsPreAux(root, list);
        return list;
    }
    private void positionsPreAux(Node n, LinkedListOfInteger list) {
        if (n != null) {
            list.add(n.element);
            positionsPreAux(n.left, list); 
            positionsPreAux(n.right, list); 
        }
    }
    
    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger list = new LinkedListOfInteger();
        positionsCentralAux(root, list);
        return list;
    }
    private void positionsCentralAux(Node n, LinkedListOfInteger list) {
        if (n != null) {
            positionsCentralAux(n.left, list); 
            list.add(n.element); 
            positionsCentralAux(n.right, list); 
        }
    }

    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger list = new LinkedListOfInteger();
        positionsPosAux(root, list);
        return list;
    }
    private void positionsPosAux(Node n, LinkedListOfInteger list) {
        if (n != null) {
            positionsPosAux(n.left, list);
            positionsPosAux(n.right, list); 
            list.add(n.element);
        }
    }

   
    public void add(Integer element) {
        if(isEmpty()) {
            root = new Node(element);
            root.setBlack();
        }else{
            add(root, element, null);
        }
        count++;
        recalculate(root, false);
    }
    private void add(Node n, Integer e, Node father) {
        if (n == nill) {
            Node aux = new Node(e);
            aux.father = father;
            n = aux;
        }
        if (n.element < e) {
            add(n.right, e, n);
        } else {
            add(n.left, e, n);
        }
    }    


    private void recalculate(Node n, boolean verify){
        if(n == nill) return;
        
        //case 0 - root always black
        if(root.isRed()) {
            root.setBlack();
        }
        
        //set uncle
        Node uncle = null;
        if (n.father.father.left == n.father) {
            uncle = n.father.father.right;
        }else{
            uncle = n.father.father.left;
        }
        
        
        //case 1 - uncle red  
        if (uncle.isRed()) {
            chengeColors(n, uncle, n.father.father); 
            verify = true;      
        }

        //case 2 - uncle black => triangle
        if(!uncle.isRed() && uncle == n.father.father.left && n == n.father.left && !verify){
            rotateRigth(n);
        }else if (!uncle.isRed() && uncle == n.father.father.right && n == n.father.right && !verify) {
            rotateLeft(n);
        }
        
        //case 3 - uncle black => line
        if(!uncle.isRed() && uncle == n.father.father.left && n == n.father.right && !verify){
            rotateLeft(n.father);
            chengeColors(n, uncle, n.father.left);
        }else if (!uncle.isRed() && uncle == n.father.father.right && n == n.father.left && !verify) {
            rotateRigth(n.father);
            chengeColors(n, uncle, n.father.right);
            
        }


        if (verify) {
            recalculate(root, false);
        }else{
            recalculate(n.left, false);
            recalculate(n.right, false);
        }
        
    }

    private void chengeColors(Node n,  Node uncle, Node grandparent){
        n.father.setBlack();
        uncle.setBlack();
        grandparent.setRed();
    }


    //n become parent
    private void rotateLeft(Node n){
        n.father.father.left = n;
        Node aux = n.left;
        n.left = n.father;
        n.father = n.father.father;
        n.left.right = aux;
        n.left.father = n;

        if (n.right == root) {
            root = n;
            root.father = nill;
        }        
    }

    //n become parent
    private void rotateRigth(Node n){
            n.father.father.right = n;
            Node aux = n.right;
            n.right = n.father;
            n.father = n.father.father;
            n.right.left = aux;
            n.right.father = n;

            if (n.right == root) {
                root = n;
                root.father = nill;
            }
    }


    public int height() {        
        return height(root, 0); 
    }
    private int height(Node n, int num) {        
        if(n.left == null && n.right == null){
         heightReturn(n.father);
        }else{
            if (n.left !=null)height(n.left, 0);
            if (n.right !=null)height(n.right, 0);
            
        }
        return 0;
    }
    private int heightReturn(Node n) {        
        
        return height(root, 0); 
    }
    
       
    public LinkedListOfInteger positionsWidth() {
        Queue<Node> fila = new Queue<>();
        LinkedListOfInteger res = new LinkedListOfInteger();
        // Implemente este metodo 
        return res;
    }    

    
    public Node getParent(Node n) {
        if (this.isEmpty()) {
            return null;       
        }
        return n.father;     
    }
    
    

    
    
    
    @Override
    public RedBlackTree clone() {
        RedBlackTree tree = new RedBlackTree();
        clone(root ,tree);
        return tree;
    }
    private void clone(Node n, RedBlackTree tree){
        if(n == nill)
            return;

        tree.add(n.element);
        clone(n.left, tree);
        clone(n.right, tree);
    }    
}
