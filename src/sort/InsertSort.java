package sort;

public class InsertSort{
    /*
    插入排序，复杂度O(N*N)，平均也为O(N*N)
     */
    public static void insertSort(int[] a){
        int i;
        for(int p=1;p<a.length;p++){
            int tmp=a[p];
            for(i=p;i>0&&tmp<a[i-1];i--){
                a[i]=a[i-1];
            }
            a[i]=tmp;
        }
    }

    public static void main(String[] args) {
        int[] a=new int[]{12,20,38,20,44,89,15};
        insertSort(a);
        for(int i=0;i<7;i++){
            System.out.print(a[i]+" ");
        }
    }
}