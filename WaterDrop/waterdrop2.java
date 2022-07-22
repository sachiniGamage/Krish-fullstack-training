
public class waterdrop2 
{
   public static void main( String[] args ) {
    	
    	int arr[][] = {
    			{0,0,0,0,0,0,0},
    			{1,0,0,0,0,0,0},
    			{0,0,1,1,1,0,0},
    			{0,0,0,0,0,0,0},
    			{1,1,1,0,0,1,0},
    			{0,0,0,0,0,0,1},
    			{0,0,0,0,0,0,0}
    	};
    	
    	double[] array = splitt(arr, 0, 5, 100);
    	for(int i=0; i<array.length; i++) {
    		System.out.println(array[i]);
    	}
    	
    }
    
   public static double[] splitt(int arr[][], int depth, int index, double water) {

	   //last depth
	   if(depth == arr.length) {
		   double[] array = new double[arr.length]; //7
		   array[index] = water;
		   return array;
	   }
	   
	   if(arr[depth][index] == 0) { 
		   return splitt(arr, depth+1, index, water); //i=1
	   }else {

        int left = lhs(arr[depth], index);
        int right = rhs(arr[depth], index);

        if(left != -1 && right != -1){
        //  /\
        //if(left >= 0 && right < arr.length){
        //    if (index - 1 >= 0 && index + 1 < arr.length) {
               return merge(splitt(arr, depth+1, left, water*0.5),
            		   splitt(arr, depth+1, right, water*0.5) );
           } else if (right != -1) { //  |\
               return splitt(arr, depth+1, right, water*0.5);
           } else if (left !=-1) {//  /|
               return splitt(arr, depth+1, left, water*0.5);
           } else {
               // unreachable
               return new double[arr.length];
           }
	   }
   }
   // 25 0 25
   //      25 0 25
   //25 0  50 0 25
   public static double[] merge(double[] arr1, double[] arr2) {
	   double[] result = new double[arr1.length];
	   for (int i =0;i< arr1.length; i++) {
		   result[i] = arr1[i] + arr2[i];
	   }
	   return result;
   }
//      0
//      0
// 0 1  1 
   public static int lhs(int arr[], int index){
    if(index < 0){
            return -1;
        }
    if(arr[index] == 0){
        return index;
    }else{
        
        return lhs(arr,index-1);
    }
   }
//0
//0
//1 1 0
    public static int rhs(int arr[], int index){
        if(index == arr.length){
            return -1;
        }
        if(arr[index] == 0){
            return index;
        }else{
            return rhs(arr,index+1);
        }
   }
}
