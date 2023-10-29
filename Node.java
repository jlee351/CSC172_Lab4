/*-
* AUTHOR: LEE, JOON HYUP
* Node.java is the class of nodes that represent each letter in a sequence.
*/

public class Node {
    public String letter;
    public Node temp;

    //constructor
    public Node(String letter, Node temp) {
        this.letter = letter;
        this.temp = temp;
    }

    //retrieves node
    public Node getNext() {
        return temp;
    }

    //sets node
    public Node setNext(Node temp){
        return this.temp = temp;
    }

    //retrieves letter
    public String getLetter(){
        return letter;
    }

    //sets letter
    public String setLetter(String letter){
        return this.letter = letter;
    }
}
