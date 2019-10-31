import java.util.*;
import java.lang.*;

public class ByteCode{
  private HashMap<Integer, String> map = new HashMap<>(){{
    put(132, "cmpe");
    put(136, "cmplt");
    put(140, "cmpgt");
    put(36, "jmp");
    put(40, "jmpc");
    put(44, "call");
    put(48, "ret");
    put(68, "pushc");
    put(69, "pushs");
    put(70, "pushi");
    put(71, "pushf");
    put(72, "pushvc");
    put(73, "pushvs");
    put(74, "pushvi");
    put(75, "pushvf");
    put(76, "popm");
    put(80, "popv");
    put(77, "popa");
    put(84, "peekc");
    put(85, "peeks");
    put(86, "peeki");
    put(87, "peekf");
    put(88, "pokec");
    put(89, "pokes");
    put(90, "pokei");
    put(91, "pokef");
    put(94, "swp");
    put(100, "add");
    put(104, "sub");
    put(108, "mul");
    put(112, "div");
    put(144, "printc");
    put(145, "prints");
    put(146, "printi");
    put(147, "printf");
    put(0, "halt");
  }};
  String command;

  public ByteCode(int code){
    if(map.containsKey(code)){
      this.command = map.get(code);
    }
    else{
      System.out.println("Invalid Op Code");
      System.exit(0);
    }
  }

  public void execute(){
    switch(this.command){
      case "cmpe":
      case "cmplt":
      case "cmpgt":
      case "jmp":
      case "jmpc":
      case "call":
      case "ret":
      case "pushc":
      case "pushs":
      case "pushi":
      case "pushf":
      case "pushvc":
      case "pushvs":
      case "pushvi":
      case "pushvf":
      case "popm":
      case "popv":
      case "popa":
      case "peekc":
      case "peeks":
      case "peeki":
      case "peekf":
      case "pokec":
      case "pokes":
      case "pokei":
      case "pokef":
      case "swp":
      case "add":
      case "sub":
      case "mul":
      case "div":
      case "printc":
      case "printi":
      case "printf":
      case "prints":
    }
  }

}
