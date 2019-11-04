import java.util.*;

import javax.swing.text.Style;

import java.lang.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
        int cmpe ;
        if (rstack.get(sp-1).equals(rstack.get(sp))){
          cmpe = 1;
        }
        else{
          cmpe = 0;
        }
        
        rstack.set(sp-1, (float)cmpe);
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "cmplt":
        float cmplt = (rstack.get(sp - 1) < rstack.get(sp)) ? 1 : 0;
        rstack.set(sp-1, cmplt);
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "cmpgt":
        float cmpgt = (rstack.get(sp - 1) > rstack.get(sp)) ? 1 : 0;
        rstack.set(sp-1, cmpgt);
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "jmp":
        pc = (int) Math.round(rstack.get(sp));
        rstack.remove(sp);
        sp--;
        //pc++;
        break;

      case "jmpc":
        if(rstack.get(sp-1).equals((float)1)){
          pc = (int) Math.round(rstack.get(sp));
        }
        else{
          pc++;
        }
        rstack.remove(sp);
        sp--;
        rstack.remove(sp);
        sp--;
        //pc++; //confirm this
        break;

      case "call":
        int call = sp - (int) Math.round(rstack.get(sp)) - 1;
        rstack.remove(sp);
        sp--;
        fpstack.add(call);
        fpsp++;
        pc = (int) Math.round(rstack.get(sp));
        rstack.remove(sp);
        sp--; 
        break;

      case "ret":
        sp = fpstack.get(fpsp);
        pc = (int) Math.round(rstack.get(sp));
        fpstack.remove(fpsp);
        fpsp--;
        rstack.remove(sp);
        sp--;
        break;

      case "pushc":
        rstack.add((float)Byte.toUnsignedInt(memory[pc+1]));
        sp++;
        pc += 2;
        break;

      case "pushs":
        byte[] bytes_s = {memory[pc+1], memory[pc+2]};
        short[] shorts = new short[1];
        ByteBuffer.wrap(bytes_s).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
        rstack.add((float)shorts[0]);
        sp++;
        pc += 3;
        break;

      case "pushi":
        byte[] bytes_i = {memory[pc+1], memory[pc+2], memory[pc+3], memory[pc+4]};
        int pushi = ByteBuffer.wrap(bytes_i).order(ByteOrder.LITTLE_ENDIAN).getInt();
        //float pushi = (memory[pc+4] << 24) + (memory[pc+3] << 16) + (memory[pc+2] << 8) + (memory[pc+1] << 0);
        rstack.add((float)pushi);
        sp++;
        pc += 5;
        //System.out.println("Pushed Value " + pushi);
        break;

      case "pushf":
        byte[] bytes_f = {memory[pc+1], memory[pc+2], memory[pc+3], memory[pc+4]};
        float pushf = ByteBuffer.wrap(bytes_f).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        //float pushf = Float.intBitsToFloat(memory[pc+4] | memory[pc+3]<<8 | memory[pc+2]<<16 | memory[pc+1]<<24);
        rstack.add(pushf);
        sp++;
        pc += 5;
        break;

      case "pushvc":
        float pushvc = rstack.get(fpstack.get(fpsp) + (int) Math.round((rstack.get(sp))) + 1);
        rstack.set(sp, pushvc);
        pc++;
        break;

      case "pushvs":
        float pushvs = rstack.get(fpstack.get(fpsp) + (int) Math.round((rstack.get(sp))) + 1);
        rstack.set(sp, pushvs);
        pc++;
        break;

      case "pushvi":
        float pushvi = rstack.get(fpstack.get(fpsp) + (int) Math.round((rstack.get(sp))) + 1);
        rstack.set(sp, pushvi);
        pc++;
        break;

      case "pushvf":
        float pushvf = rstack.get(fpstack.get(fpsp) + (int) Math.round((rstack.get(sp))) + 1);
        rstack.set(sp, pushvf);
        pc++;
        break;

      case "popm":
        int count = (int) Math.round(rstack.get(sp) + 1);
      
        for(int i = 0; i < count; i++){
          rstack.remove(sp);
          sp--;
        }
        //sp -= rstack.get(sp) + 1;
        pc++;
        break;

      case "popv":
        rstack.set(fpstack.get(fpsp)+ (int) Math.round(rstack.get(sp)+ 1), rstack.get(sp-1));
        rstack.remove(sp);
        sp--;
        rstack.remove(sp);
        sp--;
        //sp -= 2;
        pc++;
        break;

      case "popa":
        int p = (int) Math.round(rstack.get(sp));
        rstack.remove(sp);
        sp--;
        //System.out.println("Elements to keep: " + p);

        float temp[] = new float[p];

        if(p != 0){
          
          for(int i = p; i > 0; i++){
            temp[i-p] = rstack.get(sp);
            rstack.remove(sp);
            sp--;
          }
        }

        while(sp != fpstack.get(fpsp)){
          rstack.remove(sp);
          sp--;
        }

        if(p != 0){
          for(int i = 0; i < p; i++){
            rstack.add(temp[i]);
            sp++;
          }
        }

        pc++;
        break;

      case "peekc":
        int temp_peekc_1 = fpstack.get(fpsp);
        float temp_peekc_2 = rstack.get(sp-1);
        float temp_peekc_3 = rstack.get(sp);
        
        rstack.remove(sp);
        sp--;
        rstack.remove(sp);
        sp--;

        int set_peekc = temp_peekc_1 + (int)Math.round(temp_peekc_2) + 1;
        int set2_peekc = temp_peekc_1 + (int)Math.round(temp_peekc_3) + 1;

        rstack.set(set_peekc, rstack.get(set2_peekc));
        pc++;
        break;

      case "peeks":
        int temp_peeks_1 = fpstack.get(fpsp);
        float temp_peeks_2 = rstack.get(sp-1);
        float temp_peeks_3 = rstack.get(sp);
        
        rstack.remove(sp);
        sp--;
        rstack.remove(sp);
        sp--;

        int set_peeks = temp_peeks_1 + (int)Math.round(temp_peeks_2) + 1;
        int set2_peeks = temp_peeks_1 + (int)Math.round(temp_peeks_3) + 1;

        rstack.set(set_peeks, rstack.get(set2_peeks));
        pc++;
        break;

      case "peeki":
        int temp_peeki_1 = fpstack.get(fpsp);
        float temp_peeki_2 = rstack.get(sp-1);
        float temp_peeki_3 = rstack.get(sp);
        
        rstack.remove(sp);
        sp--;
        rstack.remove(sp);
        sp--;

        int set_peeki = temp_peeki_1 + (int)Math.round(temp_peeki_2) + 1;
        int set2_peeki = temp_peeki_1 + (int)Math.round(temp_peeki_3) + 1;

        rstack.set(set_peeki, rstack.get(set2_peeki));
        pc++;
        break;

      case "peekf":
        int temp_peekf_1 = fpstack.get(fpsp);
        float temp_peekf_2 = rstack.get(sp-1);
        float temp_peekf_3 = rstack.get(sp);
        
        rstack.remove(sp);
        sp--;
        rstack.remove(sp);
        sp--;

        int set_peekf = temp_peekf_1 + (int)Math.round(temp_peekf_2) + 1;
        int set2_peekf = temp_peekf_1 + (int)Math.round(temp_peekf_3) + 1;

        rstack.set(set_peekf, rstack.get(set2_peekf));
        pc++;
        break;

      case "pokec":
        int temp_pokec_1 = fpstack.get(fpsp);
        float temp_pokec_2 = rstack.get(sp-1);
        float temp_pokec_3 = rstack.get(sp);
        
        rstack.remove(sp);
        sp--;
        rstack.remove(sp);
        sp--;

        int set_pokec = temp_pokec_1 + (int)Math.round(temp_pokec_3) + 1;
        int set2_pokec = temp_pokec_1 + (int)Math.round(temp_pokec_2) + 1;

        rstack.set(set_pokec, rstack.get(set2_pokec));
        pc++;
        break;

      case "pokes":
      int temp_pokes_1 = fpstack.get(fpsp);
      float temp_pokes_2 = rstack.get(sp-1);
      float temp_pokes_3 = rstack.get(sp);
      
      rstack.remove(sp);
      sp--;
      rstack.remove(sp);
      sp--;

      int set_pokes = temp_pokes_1 + (int)Math.round(temp_pokes_3) + 1;
      int set2_pokes = temp_pokes_1 + (int)Math.round(temp_pokes_2) + 1;

      rstack.set(set_pokes, rstack.get(set2_pokes));
      pc++;
      break;

      case "pokei":
        int temp_pokei_1 = fpstack.get(fpsp);
        float temp_pokei_2 = rstack.get(sp-1);
        float temp_pokei_3 = rstack.get(sp);
        
        rstack.remove(sp);
        sp--;
        rstack.remove(sp);
        sp--;

        int set_pokei = temp_pokei_1 + (int)Math.round(temp_pokei_3) + 1;
        int set2_pokei = temp_pokei_1 + (int)Math.round(temp_pokei_2) + 1;

        rstack.set(set_pokei, rstack.get(set2_pokei));
        pc++;
        break;

      case "pokef":
        int temp_pokef_1 = fpstack.get(fpsp);
        float temp_pokef_2 = rstack.get(sp-1);
        float temp_pokef_3 = rstack.get(sp);
        
        rstack.remove(sp);
        sp--;
        rstack.remove(sp);
        sp--;

        int set_pokef = temp_pokef_1 + (int)Math.round(temp_pokef_3) + 1;
        int set2_pokef = temp_pokef_1 + (int)Math.round(temp_pokef_2) + 1;

        rstack.set(set_pokef, rstack.get(set2_pokef));
        pc++;
        break;

      case "swp":
        float tmp = rstack.get(sp-1);
        rstack.set(sp-1, rstack.get(sp));
        rstack.set(sp, tmp);
        pc++; 
        break;

    case "add":
        float add1 = rstack.get(sp-1) + rstack.get(sp);
        rstack.set(sp-1, add1);
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "sub":
        float sub1 = rstack.get(sp-1) - rstack.get(sp);
        rstack.set(sp-1, sub1);
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "mul":
        float mul1 = rstack.get(sp-1) * rstack.get(sp);
        rstack.set(sp-1, mul1);
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "div":
        float div1 = rstack.get(sp-1) / rstack.get(sp);
        rstack.set(sp-1, div1);
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "printc":
        //System.out.println("PRINTC");
        System.out.println(Math.round(rstack.get(sp)));
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "printi":
      //TODO check conversion
        System.out.println((int)Math.round(rstack.get(sp)));
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "printf":
        System.out.println(rstack.get(sp));
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "prints":
        System.out.println((short)Math.round(rstack.get(sp)));
        rstack.remove(sp);
        sp--;
        pc++;
        break;

      case "halt":
        System.out.println("\nCompile values:");
        System.out.println("PC: "+ pc);
        System.out.println("SP: " + sp);
        if (sp == -1){
          System.out.print("Rstack: Empty");
        }
        else{
          System.out.print("Rstack: ");
          for(float i: rstack){
            System.out.print(i + ", ");
          }
        }
        System.out.println("");
        
        System.out.println("FPSP: "+ fpsp);
        
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
        System.exit(0);
        break;
    }
  }

  public void printState(int op_code, String command){
    System.out.println(op_code + ", " + command);
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
    System.out.println("\n");
  }

  public void execute(){
    int op_code;
    do{
      String command;
      op_code = Byte.toUnsignedInt(memory[pc]);
      if(map.containsKey(op_code)){
        command = map.get(op_code);
        runCommand(command);
        //printState(op_code,command);      

        /*if(op_code == 86){
          printState(op_code,command);      
        }*/
      }
    }while(true);
  }

}
