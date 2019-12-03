
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
    private final static Node nill = new Node();

    

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

    
    /**
     * Adiciona o elemento passado por parametro na arvore. 
     * @param element elemento a ser adicionado na arvore.
     */
    public void add(Integer element) {
        if(isEmpty()) {
            root = new Node(element);
            root.setBlack();
        }else{
            Node n = add(root, element, null);
        }
        count++;
        recalculate();
    }
    private Node add(Node n, Integer e, Node father) {
        if (n == nill) {
            Node aux = new Node(e);
            aux.father = father;
            n = aux;
        }
        if (n.element < e) {
            n.right = add(n.right, e, n);
        } else {
            n.left = add(n.left, e, n);
        }
        return n;
    }     
    private void recalculate(){
        recalculate(root, false);
    }

    private void recalculate(Node n, boolean verify){
        Node uncle = null;

        if(n== nill) return;

        if (n.father.father.left == n.father) {
            uncle = n.father.father.right;
        }else{
            uncle = n.father.father.left;
        }
        if (uncle.isRed()) {
            chengeColors(n, uncle); 
            verify = true;      
        }


        


        if (verify) {
            recalculate(root, false);
        }else{
            recalculate(n.left, verify);
            recalculate(n.right, verify);
        }
        
    }

    private void chengeColors(Node n,  Node uncle){
        n.father.setBlack();
        uncle.setBlack();
        n.father.father.setRed();
    }


    //if n.father.father.left true
    private void rotateLeft(Node n){
        Node auxBrother = n.father.left;
        n.father.left = n.father.father;
        auxBrother.father = n.father.left;
        n.father.left.right = auxBrother;
        n.father.left.father = n.father;
    }

    //if n.father.father.right true
    private void rotateRigth(Node n){
        Node auxBrother = n.right;
        n.right = n.father;
        auxBrother.father = n.right;
        n.right.left = auxBrother;
        n.right.father = n;
    }



    /*
    1 z=root - black
    2 z.uncle = RED - recolor - father and uncle tunr into black end grnadparent become red
    3 z.uncle = black (triangle) - rotate z.parent z is red and uncle is black
    4 z.uncle = black(line) rotate z.grnadparent and recolor rotate to rigth, father become red and those childs red
     
    */
    
    
    
    ////////////////////////////////////////////////////
    // Implemente os métodos abaixo
    ////////////////////////////////////////////////////
    
    
    
    /**
     * Retorna a altura da arvore. Deve chamar um metodo auxiliar recursivo.
     * @return altura da arvore
     */    
    public int height() {        
        //Implemente este metodo (de preferencia de forma recursiva)
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
        //Implemente este metodo (de preferencia de forma recursiva)
        return height(root, 0); 
    }
    
    /** 
     * Retorna uma lista com todos os elementos da arvore na ordem de 
     * caminhamento em largura. 
     * @return LinkedListOfInteger lista com os elementos da arvore
     */     
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
        if(n == null)
            return;

        tree.add(n.element);
        clone(n.left, tree);
        clone(n.right, tree);
    }    
}
