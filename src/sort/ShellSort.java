package sort;

public class ShellSort {
    /*
    希尔排序，最坏情形为O（N*N）
    增量序列
    （1）ht=N/2,hk=h(k+1)/2
    (2)1,3,5,7..
    一般做法为，把hk到N-1上每一个位置的i放到i,i-hk,i-2hk...的正确位置
     */
    public static void shellSort(int[] a){
        int i;
        for(int gap=a.length/2;gap>0;gap/=2){
            for(int j=gap;j<a.length;j++){
                int tmp=a[j];
                for(i=j;i>=gap&&tmp<a[i-gap];i-=gap){
                    a[i]=a[i-gap];
                }
                a[i]=tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a={12,10,28,20,44,89,15};
        shellSort(a);
        for(int i=0;i<7;i++){
            System.out.print(a[i]+" ");
        }
    }
}
