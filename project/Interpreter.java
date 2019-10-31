import java.util.*;
import java.lang.*;

public class Interpreter{
  public Stack<Rstack_obj> rstack;
  public Stack<Integer> fpstack;
  public int sp;
  public int fpsp;
  public int pc;
  public int memory[];

  public Interpreter(int[] memory){
    this.rstack = new Stack<>();
    this.fpstack = new Stack<>();
    this.sp = -1;
    this.fpsp = -1;
    this.pc = 0;
    this.memory = memory;
    System.out.println("No errors yet");
  }

  /*public void execute(){
    while(pc < memory.length && memory[pc]){
      System.out.println(memory[pc]);
      pc++;
    }
  }*/

  public void state(){
    //retrun current state
  }
}
