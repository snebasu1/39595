import java.util.*;
import java.lang.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;


class Main{

  public static void main(String[] args){
    Path path = Paths.get("interpreter_input.smp");

    try{
      byte[] content = Files.readAllBytes(path);
      int memory[] = new int[content.length];
      for(int i = 0; i < memory.length; i++){
        memory[i] = Byte.toUnsignedInt(content[i]);
      }

      Interpreter interpreter = new Interpreter(memory);
      interpreter.execute();
    }
    catch(Exception e){
      System.out.println("Error in reading binary file");
      System.exit(0);
    }

    return;
  }
}
