import java.util.*;
import java.lang.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;


class Main{

  public static void main(String[] args){
    //Path path = Paths.get("interpreter_input.smp");
    Path path = Paths.get(args[0]);

    try{
      byte[] memory = Files.readAllBytes(path);

      /*for(int i = 0; i < memory.length; i++){
        System.out.println(Byte.toUnsignedInt(memory[i]));
      }
      
      int memory[] = new int[content.length];
      for(int i = 0; i < memory.length; i++){
        memory[i] = Byte.toUnsignedInt(content[i]);
      }*/

      Interpreter interpreter = new Interpreter(memory);
      interpreter.execute();
    }
    catch(Exception e){
      System.out.println(e);
      System.exit(0);
    }

    return;
  }
}
