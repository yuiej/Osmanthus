package solution;

import util.SortUtil;

public class ProblemHandler {



    public static Integer findSpecificNum(int[] arr, int k){
        if (SortUtil.isEmpty(arr))
            return null;

        return arr[findSpecificNum_r(arr,0,arr.length - 1,k)];
    }

    public static int findSpecificNum_r(int[] arr, int p, int r, int k){
        int i = SortUnit.partition(arr,p, r);
        int[] tem ;
        if (k > i + 1){

            return findSpecificNum_r(arr,i+1, r,k);
        } else if (k < i + 1){
            return findSpecificNum_r(arr,p, i,k);
        } else {
            return i;
        }

    }





}
