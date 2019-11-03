package package1;
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
      /*case "cmpe":
        cmpe obj = new cmpe();
        obj.execute();
        break;

      case "cmplt":
        cmplt obj = new cmplt();
        obj.execute();
        break;

      case "cmpgt":
        cmpgt obj = new cmpgt();
        obj.execute();
        break;

      case "jmp":
        jmp obj = new jmp();
        obj.execute();
        break;

      case "jmpc":
        jmpc obj = new jmpc();
        obj.execute();
        break;

      case "call":
        call obj = new call();
        obj.execute();
        break;

      case "ret":
        ret obj = new ret();
        obj.execute();
        break;

      case "pushc":
        pushc obj = new pushc();
        obj.execute();
        break;

      case "pushs":
        pushs obj = new pushs();
        obj.execute();
        break;*/

      case "pushi":
        System.out.println("here");
        pushi obj = new pushi();
        //obj.execute();
        break;

      /*case "pushf":
        pushf obj = new pushf();
        obj.execute();
        break;

      case "pushvc":
        pushvc obj = new pushvc();
        obj.execute();
        break;

      case "pushvs":
        pushvs obj = new pushvs();
        obj.execute();
        break;

      case "pushvi":
        pushvi obj = new pushvi();
        obj.execute();
        break;

      case "pushvf":
        pushvf obj = new pushvf();
        obj.execute();
        break;

      case "popm":
        popm obj = new popm();
        obj.execute();
        break;

      case "popv":
        popv obj = new popv();
        obj.execute();
        break;

      case "popa":
        popa obj = new popa();
        obj.execute();
        break;

      case "peekc":
        peekc obj = new peekc();
        obj.execute();
        break;

      case "peeks":
        peeks obj = new peeks();
        obj.execute();
        break;

      case "peeki":
        peeki obj = new peeki();
        obj.execute();
        break;

      case "peekf":
        peekf obj = new peekf();
        obj.execute();
        break;

      case "pokec":
        pokec obj = new pokec();
        obj.execute();
        break;

      case "pokes":
        pokes obj = new pokes();
        obj.execute();
        break;

      case "pokei":
        pokei obj = new pokei();
        obj.execute();
        break;

      case "pokef":
        pokef obj = new pokef();
        obj.execute();
        break;

      case "swp":
        swp obj = new swp();
        obj.execute();
        break;

      case "add":
        add obj = new add();
        obj.execute();
        break;

      case "sub":
        sub obj = new sub();
        obj.execute();
        break;

      case "mul":
        mul obj = new mul();
        obj.execute();
        break;

      case "div":
        div obj = new div();
        obj.execute();
        break;

      case "printc":
        printc obj = new printc();
        obj.execute();
        break;

      case "printi":
        printi obj = new printi();
        obj.execute();
        break;

      case "printf":
        printf obj = new printf();
        obj.execute();
        break;

      case "prints":
        prints obj = new prints();
        obj.execute();
        break;

      case "halt":
        halt obj = new halt();
        obj.execute();
        break;*/
    }
  }

}
