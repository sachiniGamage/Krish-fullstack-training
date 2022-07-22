import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
public class palindrome{
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(2);
        list.add(1);

        System.out.println(palindrom(list));

    }

    public static boolean palindrom(List<Integer> list){
        int value =0 ;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<list.size() ; i++){ //1,2
            value = list.get(i);  
            stack.push(value);
        }   

        for (int j = 0; j<stack.size(); j++){
            if(stack.pop().equals(list.get(j))){ //1,2
                continue;
                // return true;
            }else{
                return false;
            }
            
        }
        return true;
        
    }
}