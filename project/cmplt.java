import java.lang.*;
import java.util.*;
public class cmplt{
    public void execute(){
        rstack[sp-1] = rstack[sp-1] < rstack[sp];
        sp--;
    }
}