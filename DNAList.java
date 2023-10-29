/*-
* AUTHOR: LEE, JOON HYUP
* DNAList.java
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DNAList{
    DNA[] seq;
    Type[] type;
    int size;

    //constructor
    public DNAList(int size){
        this.size = size;
        seq = new DNA[size];
        type = new Type[size];
        for(int i = 0; i < size; i++){
            seq[i] = null;
            type[i] = Type.NONE;
        }
    }

    public static void main(String[] args) {
        DNAList list = new DNAList(Integer.parseInt(args[0]));
        try {
            File f = new File(args[1]);
            Scanner scnr = new Scanner(f);
            while (scnr.hasNextLine()) {
                String l = scnr.nextLine();
                ReadFile.read(list, l);
            }
            scnr.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    //checks if it is DNA
    public boolean checkDNA(String a){
        for(int i = 0; i < a.length(); i++) {
            String temp = a.charAt(i) + "";
            switch(temp) {
                case "A": case "C":
                case "G": case "T":
                    break;

                default:
                return false;
            }
        }
        return true;
    }

    //checks if it is RNA
    public boolean checkRNA(String a){
        for(int i = 0; i < a.length(); i++) {
            String temp = a.charAt(i) + "";
            switch(temp) {
                case "A": case "C":
                case "G": case "U":
                    break;

                default:
                    return false;
            }
        }
        return true;
    }

    //insert sequence to position pos in the sequence array.
    public void insert(int pos, String type, String sequence){
        if(pos < size){
            switch(type) {
                case "RNA":
                    if(checkRNA(sequence)) {
                        seq[pos] = new DNA(sequence);
                        this.type[pos] = Type.RNA;
                    }
                    else {
                        System.out.println("Error occured while inserting");
                    }
                    break;
                case "DNA":
                    if(checkDNA(sequence)) {
                        seq[pos] = new DNA(sequence);
                        this.type[pos] = Type.DNA;
                    }
                    else{
                        System.out.println("Error occured while inserting");
                    }
                    break;
                default:
                    System.out.println("Error occured while inserting");
            }
        }
        else{
            System.out.println("Error occured while inserting");
        }
    }

    //Remove the sequence at position pos in the sequence array.
    public DNA remove(int pos){
        if(type[pos] == Type.NONE || pos >= size) {
            System.out.println("No sequence to remove at specified position");
            return null;
        }
        else{
            DNA temp = seq[pos];
            seq[pos] = null;
            type[pos] = Type.NONE;
            return temp;
        }
    }

    //Print the sequence and type.
    public void print(){
        for(int i = 0; i<size; i++){
            if(type[i] != Type.NONE){
                System.out.printf("%d\t%s\t%s", i, type[i], seq[i].getSeq());
                System.out.println();
            }
        }

    }

    //Print the sequence and type at position pos in the sequence array
    public void print(int pos){
        if(type[pos] == Type.NONE){
            System.out.println("No sequence to print at specified position");
        }
        else if(pos >= size){
            System.out.println("No sequence to print at specified position");
        }
        else{
            System.out.printf("%s\t%s",type[pos], seq[pos].getSeq());
            System.out.println();
        }

    }

    //Replace the sequence at position pos with a clipped version of the sequence.
    public void clip(int pos, int start, int end){
        if(pos >= size || type[pos] == Type.NONE){
            System.out.println("No sequence to clip at specified position");
        }
        else if(start < 0){
            System.out.println("Start index is out of bounds");
        }
        else if(end > seq[pos].getSeq().length()) {
            System.out.println("End index is out of bounds");
        }
        else if(start > end){
            seq[pos].setEmpty();
        }
        else {
            seq[pos] = new DNA(seq[pos].getSeq().substring(start, end+1));
        }
    }

    //Copy the sequence in position pos1 to pos2.
    public void copy(int pos1, int pos2){
        if(pos1 >= size || pos2 >= size || type[pos1] == Type.NONE){
            System.out.println("No sequence to copy at specified position");
        }
        else{
            seq[pos2] = new DNA(seq[pos1].getSeq());
            type[pos2] = type[pos1];
        }

    }

    //Transcription converts a DNA sequence in pos1 to an RNA sequence.
    public void transcribe(int pos1){
        if(type[pos1] == Type.RNA) {
            System.out.println("Cannot transcribe RNA");
        }
        else if(type[pos1] == Type.NONE) {
            System.out.println("No sequence to transcribe at specified position");
        }
        else{
            String old = seq[pos1].getSeq();
            String temp = "";
            for(int i = 0; i < old.length(); i++){
                if(old.charAt(i) == 'A'){
                    temp = "U" + temp;
                }
                else if(old.charAt(i) == 'C'){
                    temp = "G" + temp;
                }
                else if(old.charAt(i) == 'G'){
                    temp = "C" + temp;
                }
                else if(old.charAt(i) == 'T'){
                    temp = "A" + temp;
                }
                else{
                    continue;
                }
            }
            type[pos1] = Type.RNA;
            String temp2 = "";
            char ch;
            for(int i = 0; i < temp.length(); i++){
                ch = temp.charAt(i);
                temp2 = ch + temp2;
            }
            seq[pos1] = new DNA(temp2);

        }

    }

}
