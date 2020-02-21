import java.util.*;
import java.lang.*;

public class Rstack_obj{
  private String type;
  private int int_value;
  private char char_value;
  private float float_value;
  private short short_value;

  public Rstack_obj(String type, int int_value, char char_value, float float_value, short short_value){
    this.type = type;

    if(type == "int"){
      this.int_value = int_value;
    }
    else if(type == "char"){
      this.char_value = char_value;
    }
    else if(type == "float"){
      this.float_value = float_value;
    }
    else if(type == "short"){
      this.short_value = short_value;
    }
  }

  public int getInt(){
    if(type != "int"){
      System.out.println("Type Error");
      System.exit(0);
    }
    else{
      return this.int_value;
    }
  }

  public int getChar(){
    if(type != "char"){
      System.out.println("Type Error");
      System.exit(0);
    }
    else{
      return this.char_value;
    }
  }

  public int getFloat(){
    if(type != "float"){
      System.out.println("Type Error");
      System.exit(0);
    }
    else{
      return this.float_value;
    }
  }

  public int getShort(){
    if(type != "short"){
      System.out.println("Type Error");
      System.exit(0);
    }
    else{
      return this.short_value;
    }
  }
}
