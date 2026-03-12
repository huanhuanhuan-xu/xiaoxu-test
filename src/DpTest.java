import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DpTest {
    // 0-1 背包问题

    /**
     * i表示物品i j表示当前空间背包剩余空间
     * 要么不装 要么装 重量减少 价值增加
     *  dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-weight[i]]+value[i]);
     *
     * @param weight
     * @param value
     * @param bagWeight
     * @param n
     * @return
     */
    public static int getBagMax(int[] weight,int[] value,int bagWeight,int n ){
// 如果背包的重量为0  则其他的都是0
        int[][] dp = new int[n][bagWeight+1];
        for (int i = 0; i < n; i++) {
            dp[i][0]=0;
        }
        // 如果只有第一个物品
        for (int i = weight[0]; i <= bagWeight; i++) {
                dp[0][i]=value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= bagWeight; j++) {
                // 当前物品太重了装不下了 不装了
                if(weight[i]>j){
                    dp[i][j]=dp[i-1][j];
                }
                // 能装下 选择装或者不装的最大值
                else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-weight[i]]+value[i]);
                }
            }
        }
        return dp[n-1][bagWeight];
    }
    /**
     * 完全背包
     * i表示物品i j表示当前空间背包剩余空间
     * 要么不装 要么装 重量减少 价值增加 区别就是可以 无限装 但其实最大的数量应该是k=j/w[i] 直接进行for循环 找到最大的值
     *  dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-k*weight[i]]+k*value[i]);
     *  思路1如上：
     *  思路2：经过各种公式推导 或者之前的公式是由i-1来的 但是由于可以无线装 所以对应的是i
     *  即：dp[i][j]=Math.max(dp[i-1][j],dp[i][j-weight[i]]+value[i]);  需要考虑一下初始化代码的问题
     *
     * @param weight
     * @param value
     * @param bagWeight
     * @param n
     * @return
     */
    public static int getBagMaxAll(int[] weight,int[] value,int bagWeight,int n ){
// 如果背包的重量为0  则其他的都是0
        int[][] dp = new int[n][bagWeight+1];
        for (int i = 0; i < n; i++) {
            dp[i][0]=0;
        }
        // 如果只有第一个物品
        for (int j = weight[0]; j <= bagWeight; j++) {
            dp[0][j]=dp[0][j-weight[0]]+value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= bagWeight; j++) {
                // 当前物品太重了装不下了 不装了
                if(weight[i]>j){
                    dp[i][j]=dp[i-1][j];
                }
                // 能装下 选择装或者不装的最大值
                else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-weight[i]]+value[i]);
                }
            }
        }
        return dp[n-1][bagWeight];
    }


    /**
     *
     * 322.零钱兑换  画表列一下
     * coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.sort(coins);
        if(amount==0){
            return 0;
        }
        if(amount<coins[0]){
            return -1;
        }
        for (int i = 1; i <= amount; i++) {
            int min=Integer.MAX_VALUE;
            for (int coin : coins) {
                int tempMin = Integer.MAX_VALUE;
                // 小于 给他的值置为0 说明不可用
                if (coin == i) {
                    tempMin = 1;
                } else if (coin > i) {
                    // 大于
                    tempMin = i - coin > 0 ? dp[i - coin] + 1 : 0;
                }
                if (tempMin != 0) {
                    min = Math.min(min, tempMin);
                }
            }
            dp[i]=min;
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }







    public static int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount]==amount+1?-1:dp[amount];
    }

    /**
     * 三数之和
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * @param nums
     * @return
     */
        public static List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);

            for (int i = 0; i < nums.length-2; i++) {
                // 和上一轮的i一样
                if (i >0 && nums[i] == nums[i-1]){
                    continue;
                }
                // 两数之和等于这个的相反数  在这个数的后面去找
                int target = -nums[i];
                int left = i+1;
                int right = nums.length - 1;
                while (left < right){
                    if(nums[left]+nums[right]==target){
                        result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                        left++;
                        right--;
                        // 如果left==felt-1 说明这个数已经处理过了 下次不再处理
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;

                    }else if(nums[left]+nums[right]<target){
                        left++;
                    }else {
                        right--;
                    }
                    }
                }
return result;
    }


    public static void main(String[] args) {
      /*  int max = getBagMaxAll(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4, 3);
        System.out.println(max);*/
       int[] ints = {1,2,5};
        System.out.println(coinChange1(ints, 11));

      /*  List<List<Integer>> list = threeSum(new int[]{0,0,0,0});
        System.out.println(list);*/
    }
}
