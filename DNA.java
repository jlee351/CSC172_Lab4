/*-
* AUTHOR: LEE, JOON HYUP
* DNA.java is a class of the DNA sequence and has the method of retrieving it.
*/

public class DNA{
    Node first;
    Node last;
    String seq;

    //constructor
    public DNA(String seq){
        this.seq = seq;
        first = new Node(String.valueOf(seq.charAt(0)), null);
        Node temp = first;
        for(int i = 1; i < seq.length(); i++) {
            temp.setNext(new Node(String.valueOf(seq.charAt(i)), null));
            temp = temp.getNext();
        }
        last = temp;
    }

    //retrieves sequence
    public String getSeq() {
        Node temp = first;
        String seq = "";
        while(temp != null) {
            seq += temp.getLetter();
            temp = temp.getNext();
        }
        return seq;
    }

    //prints
    public void print() {
        Node temp = first;
        int i = 1;
        while(temp != null) {
            System.out.print(temp.getLetter());
            i++;
            temp = temp.getNext();
        }
        System.out.println();
    }

    //sets an empty
    public void setEmpty() {
        first = null;
        last = null;
    }
}
