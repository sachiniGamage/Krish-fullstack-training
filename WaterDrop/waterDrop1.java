
public class waterDrop1 
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
        //  /\
           if (index - 1 >= 0 && index + 1 < arr.length) {
               return merge(splitt(arr, depth+1, index-1, water*0.5),
            		   splitt(arr, depth+1, index+1, water*0.5) );
           } else if (index - 1 < 0) { //  |\
               return splitt(arr, depth+1, index+1, water*0.5);
           } else if (index + 1 >= arr.length) {//  /|
               return splitt(arr, depth+1, index-1, water*0.5);
           } else {
               // unreachable
               return new double[arr[0].length];
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
}
