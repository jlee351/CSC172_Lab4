/*-
* AUTHOR: LEE, JOON HYUP
* ReadFile.java implements a method that reads in a text file of commands.
*/


public class ReadFile {
    public static void read(DNAList list, String commands) {
        String[] line = commands.split("\\s+");

        //different commands 
        switch(line[0]) {
            case "insert":
                list.insert(Integer.parseInt(line[1]), line[2], line[3]);
                break;

            case "print":
                if(line.length == 2 ){
                    list.print(Integer.parseInt(line[1]));
                }
                else{
                    list.print();
                }
                break;

            case "remove":
                list.remove(Integer.parseInt(line[1]));
                break;

            case "clip":
                list.clip(Integer.parseInt(line[1]),Integer.parseInt(line[2]), Integer.parseInt(line[3]));
                break;

            case "copy":
                list.copy(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                break;

            case "transcribe":
                list.transcribe(Integer.parseInt(line[1]));
                break;

            default:
                System.out.println("Invalid command");
        }

    }
}
