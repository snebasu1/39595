import java.lang.*;
import java.util.*;
public class cmpe extends Interpreter {
    public void execute(){
        rstack[sp-1] = rstack[sp-1] == rstack[sp];
        sp--;
    }
}