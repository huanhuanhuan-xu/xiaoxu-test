import java.util.Arrays;

public class Array {
    public int search(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            if (nums[mid]==target) return mid;
            if(nums[mid]> target){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return -1;
    }

    public int removeElement(int[] nums, int val) {
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=val){
                nums[count]=nums[i];
                count++;
            }
        }
        return count;
    }
    public int[] sortedSquares(int[] nums) {
      int[] newArr=new int[nums.length];
      int k=nums.length-1;
      // 平方后肯定会比当前所有的最大值大
        int left=0,right=nums.length-1;
        while (left<=right){
            int leftSquare=nums[left]*nums[left];
            int rightSquare=nums[right]*nums[right];
            if (leftSquare>rightSquare){
                newArr[k]=leftSquare;
                left++;
            }else {
                newArr[k]=rightSquare;
                right--;
            }
            k--;
        }
        return newArr;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int min=Integer.MAX_VALUE;
        int j=0;
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            while (sum>=target){
                min=Math.min(min,i-j+1);
                sum-=nums[j];
                j++;
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }

    public  static int[][] generateMatrix(int n) {
      int[][] arr=new int[n][n];
      int x=0,y=0,offset=1;
      int item=1;

      int i=0,j=0;
        for (int x1 = 0; x1 <= n/2; x1++) {
            // 先画横 (不画最后一个 最后一个留给别人去画)
            for(j=y;j<n-offset;j++){
                arr[x][j]=item++;
                System.out.println(arr[i][j]);
            }
            System.out.println("+++++++++++++");
            // 画竖
            for(i=x;i<n-offset;i++){
                arr[i][j]=item++;
                System.out.println(arr[i][j]);
            }
            System.out.println("+++++++++++++");
            // 反着横
            for(;j>x;j--){
                arr[i][j]=item++;
                System.out.println(arr[i][j]);
            }
            System.out.println("+++++++++++++");
            // 反着竖
            for(;i>y;i--){
                arr[i][j]=item++;
                System.out.println(arr[i][j]);
            }
            System.out.println("+++++++++++++");

            x++;
            y++;
            offset++;
        }
        if(n%2==1){
            arr[x][y]=item;
        }
        return arr;
    }

    public static void main(String[] args) {
      //  System.out.println(minSubArrayLen(4,new int[]{1,4,4}));
        int[][] ints = generateMatrix(4);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }
}
