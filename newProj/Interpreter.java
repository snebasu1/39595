import java.util.*;
import java.lang.*;

public class Interpreter{
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

  public ArrayList<Float> rstack;
  public ArrayList<Integer> fpstack;
  public int sp;
  public int fpsp;
  public int pc;
  public byte memory[];
  

  public Interpreter(byte[] memory){
    this.rstack = new ArrayList<>();
    this.fpstack = new ArrayList<>();
    this.sp = -1;
    this.fpsp = -1;
    this.pc = 0;
    this.memory = memory;
  }

  public void runCommand(String command){
    switch(command){
      case "cmpe":
        float cmpe = (rstack.get(sp) == rstack.get(sp-1)) ? 1 : 0;
        rstack.set(sp-1, cmpe);
        sp--;
        break;

      case "cmplt":
        float cmplt = (rstack.get(sp - 1) < rstack.get(sp)) ? 1 : 0;
        rstack.set(sp-1, cmplt);
        sp--;
        break;

      case "cmpgt":
        float cmpgt = (rstack.get(sp - 1) > rstack.get(sp)) ? 1 : 0;
        rstack.set(sp-1, cmpgt);
        sp--;
        break;

      case "jmp":
        pc = (int) Math.round(rstack.get(sp));
        sp--;
        break;

      case "jmpc":
        //ask about second line
        break;

      case "call":
        int call = sp - (int) Math.round(rstack.get(sp)) - 1;
        sp--;
        fpsp++;
        fpstack.add(call);
        pc = (int) Math.round(rstack.get(sp));
        sp--; 
        break;

      case "ret":
        sp = fpstack.get(fpsp);
        pc = (int) Math.round(rstack.get(sp));
        fpsp--;
        sp--;
        break;

      case "pushc":
        rstack.add((float)Byte.toUnsignedInt(memory[pc+1]));
        sp++;
        pc += 2;
        break;

      case "pushs":
        float pushs = (memory[pc+2] << 8) + (memory[pc+1] << 0);
        rstack.add(pushs);
        sp++;
        pc += 3;
        break;

      case "pushi":
        float pushi = (memory[pc+4] << 24) + (memory[pc+3] << 16) + (memory[pc+2] << 8) + (memory[pc+1] << 0);
        rstack.add(pushi);
        sp++;
        pc += 5;
        break;

      case "pushf":
        float pushf = (memory[pc+4] << 24) + (memory[pc+3] << 16) + (memory[pc+2] << 8) + (memory[pc+1] << 0);
        rstack.add(pushf);
        sp++;
        pc += 5;
        break;

      case "pushvc":
        float pushvc = rstack.get(fpstack.get(fpsp) + (int) Math.round((rstack.get(sp))) + 1);
        rstack.set(sp, pushvc);
        break;

      case "pushvs":
        float pushvs = rstack.get(fpstack.get(fpsp) + (int) Math.round((rstack.get(sp))) + 1);
        rstack.set(sp, pushvs);
        break;

      case "pushvi":
        float pushvi = rstack.get(fpstack.get(fpsp) + (int) Math.round((rstack.get(sp))) + 1);
        rstack.set(sp, pushvi);
        break;

      case "pushvf":
        float pushvf = rstack.get(fpstack.get(fpsp) + (int) Math.round((rstack.get(sp))) + 1);
        rstack.set(sp, pushvf);
        break;

      case "popm":
        sp -= rstack.get(sp) + 1;
        break;

      case "popv":
        rstack.set(fpstack.get(fpsp)+ (int) Math.round(rstack.get(sp)+ 1), rstack.get(sp-1));
        sp -= 2;
        break;

      case "popa":
        //do not understand what to do here
        break;

      case "peekc":
        int temp1 = (int) Math.round(rstack.get(fpstack(fpsp) + (int) Math.round(rstack.get(sp - 1) + 1)));
        float temp2 = rstack.get(fpstack(fpsp) + (int) Math.round(stack.get(sp) + 1));
        rstack.set(temp1, temp2);
        break;

      case "peeks":
        int temp1_short = (int) Math.round(rstack.get(fpstack(fpsp) + (int) Math.round(rstack.get(sp - 1) + 1)));
        float temp2_short = rstack.get(fpstack(fpsp) + (int) Math.round(rstack.get(sp) + 1));
        rstack.set(temp1_short, temp2_short);
        break;

      case "peeki":
        int temp1_int = (int) Math.round(rstack.get(fpstack(fpsp) + (int) Math.round(rstack.get(sp - 1) + 1)));
        float temp2_int = rstack.get(fpstack(fpsp) + (int) Math.round(rstack.get(sp) + 1));
        rstack.set(temp1_int, temp2_int);
        break;

      case "peekf":
        int temp1_float = (int) Math.round(rstack.get(fpstack(fpsp) + (int) Math.round(rstack.get(sp - 1) + 1)));
        float temp2_float = rstack.get(fpstack(fpsp) + (int) Math.round(rstack.get(sp) + 1));
        rstack.set(temp1_float, temp2_float);
        break;

      case "pokec":
      int poke1_char = (int)rstack.get(fpstack(fpsp) + (int)rstack.get(sp) + 1);
      float poke2_char  = rstack.get(fpstack(fpsp)+ (int)rstack.get(sp-1)+1);
      rstack.set(poke1_char, poke2_char);
        break;

      case "pokes":
      int poke1_short = (int)rstack.get(fpstack(fpsp) + (int)rstack.get(sp) + 1);
      float poke2_short  = rstack.get(fpstack(fpsp)+ (int)rstack.get(sp-1)+1);
      rstack.set(poke1_short, poke2_short);
        break;

      case "pokei":
      int poke1_int = (int)rstack.get(fpstack(fpsp) + (int)rstack.get(sp) + 1);
      float poke2_int  = rstack.get(fpstack(fpsp)+ (int)rstack.get(sp-1)+1);
      rstack.set(poke1_int, poke2_int);
        break;

      case "pokef":
      int poke1_float = (int)rstack.get(fpstack(fpsp) + (int)rstack.get(sp) + 1);
      float poke2_float  = rstack.get(fpstack(fpsp)+ (int)rstack.get(sp-1)+1);
      rstack.set(poke1_float, poke2_float);
        break;

      case "swp":
       
        float tmp = rstack.get(sp-1);
        rstack.set(sp-1, rstack.get(sp));
        rstack.set(sp, tmp);

        break;

    case "add":
      float add1 = rstack.get(sp-1) + rstack.get(sp);
      rstack.set(sp-1, add1);
      sp--;
        break;

      case "sub":
        float sub1 = rstack.get(sp-1) - rstack.get(sp);
      rstack.set(sp-1, sub1);
      sp--;
        break;

      case "mul":
      float mul1 = rstack.get(sp-1) * rstack.get(sp);
      rstack.set(sp-1, mul1);
      sp--;
        break;

      case "div":
        float div1 = rstack.get(sp-1) / rstack.get(sp);
        rstack.set(sp-1, div1);
        sp--;
        break;

      case "printc":
      System.out.println((char)rstack.get(sp));
      sp--;
        break;

      case "printi":
      //TODO check conversion
      System.out.println((int)rstack.get(sp));
      sp--;
        break;

      case "printf":
      System.out.println((float)rstack.get(sp));
      sp--;
        break;

      case "prints":
      System.out.println((short)rstack.get(sp));
      sp--;
        break;

      case "halt":
        if (sp == -1){
          System.out.println("Rstack: Empty");
        }
        else{
          System.out.print("Rstack: ");
          for(float i: rstack){
            System.out.print(i + ", ");
          }
        }
        System.out.println("");
        System.out.println("SP: " + sp);
        System.out.println("FPSP: "+ fpsp);
        System.out.println("PC: "+ pc);
        if (fpsp == -1){
          System.out.println("FPstack: Empty");
        }
        else{
          System.out.print("FPstack: ");
          for(int i: fpstack){
            System.out.print(i + ", ");
          }
        }
        System.out.println("");
        break;
    }
  }

  public void execute(){
    while(pc < memory.length/* && memory[pc] != 0*/){
      String command;
      int op_code = Byte.toUnsignedInt(memory[pc]);
      if(map.containsKey(op_code)){
        if(op_code != 0){
          command = map.get(op_code);
          runCommand(command);
        }
      }
      /*else{
        System.out.println("Invalid Op Code");
        System.exit(0);
      }*/
    }
  }

  public void state(){
    //retrun current state
  }
}
