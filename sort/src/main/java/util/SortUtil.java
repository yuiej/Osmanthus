package util;

public class SortUtil {

    public static int[] genneratesArray(int len, int max){
        int[] arr=new int[len];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*max);
        }
        return arr;
    }

    public static boolean isEmpty(int[] obj) {
        return obj == null || obj.length <= 1;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public static void printArray(int[] array){
        printArray(array,null);
    }

    public static void printArray(int[] array, String note){
        if(isEmpty(array)){
            return;
        }
        StringBuilder str = new StringBuilder();
        if (!isEmpty(note)){
            str.append(note);
        }
        str.append("[");
        for (int i = 0; i < array.length; i++) {
            str.append(array[i]);
            if(i != array.length-1){
                str.append(",");
            }
        }
        str.append("]");
        System.out.println(str.toString());
    }
}
