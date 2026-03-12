import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

// 滑动窗口
public class WindowTest {


    public  static String minWindow(String s, String t) {
       // 记录t里面都包含哪些元素和个数 方便进行对比是否都包含
        Map<Character,Integer> map=new HashMap<>(16);
        Map<Character,Integer> windows=new HashMap<>(16);

        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)+1);
        }
        int l=0,r=0,length=Integer.MAX_VALUE;
        // 表示符合条件的数字数量
        int valid=0;
        // start 表示最终记录的数组的起始位置
        int start=0;
        while (r<s.length()){
            // 移入窗口的元素
            char tempChar = s.charAt(r);
            // 扩大窗口   左开右闭  计算总和的时候就不用+1了
            r++;
            if(map.containsKey(tempChar)) {
                // 窗口里面包含了当前元素
                windows.put(tempChar, windows.getOrDefault(tempChar, 0) + 1);
                // 说明当前这个元素已经集齐了
                if (Objects.equals(windows.get(tempChar), map.get(tempChar))) {
                    valid++;
                }
            }
               // 说明当前元素都集齐了  判断能否缩小窗口了
               while (valid==map.size()){
                   // 左闭右开 所有这里不需要+1
                   if(r-l<length){
                       length=r-l;
                       start=l;
                   }
                  // 缩小窗口
                   char leftChar = s.charAt(l);
                   l++;
                   if(windows.containsKey(leftChar)){
                       if(Objects.equals(windows.get(leftChar), map.get(leftChar))){
                           valid--;
                       }
                       windows.put(leftChar,windows.get(leftChar)-1);
                   }
               }
        }
        return length==Integer.MAX_VALUE?"":s.substring(start,start+length);
    }

    // s2是否包含s1的排列
    public  static boolean checkInclusion(String s1, String s2) {
      Map<Character,Integer> needs=new HashMap<>();
      Map<Character,Integer> windows=new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            needs.put(s1.charAt(i),needs.getOrDefault(s1.charAt(i),0)+1);
        }
        char[] charArray = s1.toCharArray();
        Arrays.sort(charArray);
        String sortS1 = new String(charArray);

        int l=0,r=0;
        while(r<s2.length()){
          //  char tempChar = s2.charAt(r);
            r++;
            // 长度够了 比较值够不够
            while (r-l>=s1.length()){
                String substring = s2.substring(l, r);
                char[] charArray1 = substring.toCharArray();
                Arrays.sort(charArray1);
                String sortArray1 = new String(charArray1);
                if(sortS1.equals(sortArray1)){
                    return true;
                }
                l++;
            }
        }
        return false;
    }
    // 438 找到字符串中所有字母异位词

    public List<Integer> test1(String s, String p) {
        List<Integer> list=new ArrayList<>();
        char[] charArray = p.toCharArray();
        Arrays.sort(charArray);
        String sortS1 = new String(charArray);

        int l=0,r=0;
        while (r<s.length()){
            r++;
            while (r-l>=p.length()){
                // 开始对比是否相同
                String substring = s.substring(l, r);
                char[] subCharArray = substring.toCharArray();
                Arrays.sort(subCharArray);
                String sortStr = new String(subCharArray);
                if(Objects.equals(sortStr,sortS1)){
                    list.add(l);
                }
                l++;
            }
        }
        return list;
    }
// 3. 无重复字符的最长子串
    public static int test2(String s) {
      int l=0,r=0,length=0;
      Set<Character> set=new HashSet<>();
      while (r<s.length()){
          Character c = s.charAt(r);
          if(!set.contains(c)){
              set.add(c);
              r++;
          }else {
              // 说明里面有重复的 让l去找到不重复的地方
              while (l<r&&set.contains(c)){
                  char c1 = s.charAt(l);
                  set.remove(c1);
                  l++;
              }
          }
          if(r-l>length){
              length=r-l;
          }
      }

      return length;
    }
// removeDuplicates  数组为排序数组
    public static int test3(int[] nums) {
           int slow=0,fast=0;
        while (fast<nums.length){
            if(nums[slow]!=nums[fast]){
                slow++;
                nums[slow]=nums[fast];

            }
            fast++;
        }
        System.out.println(Arrays.toString(nums));
        return slow;
    }
    // 1658. 将 x 减到 0 的最小操作数 minOperations
    public static int minOperations(int[] nums, int x) {
            // 其实就是找sum-x的剩余nums中子数组最长的
            // 先计算sum
            int sum=0;
            for (int num : nums) {
                sum+=num;
            }
            int target=sum-x;
            // 说明全部需要挪出去
            if(target==0){
                return nums.length;
            }

            int l=0,r=0,length=-1,current=0;
            while (r<nums.length){
                int num = nums[r];
                current+=num;
                r++;
                while (l<r&&current>=target){
                    if(current==target){
                        length=Math.max(length,r-l);
                    }
                    int  num1= nums[l];
                    // l继续找下一位
                    l++;
                    current-=num1;
                }
            }
            return length==-1?-1:nums.length-length;
        }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int l=0,r=0,sum=1;
        int count=0;
        while(r<nums.length){
            int c=nums[r];
            r++;
            sum*=c;
            while(l<r&&sum>=k){
                sum/=nums[l];
                l++;
            }
            // 表示从l-r的这几个都符合条件
            if(sum<k){
                count+=r-l;
            }
        }
        return count;
    }
// 替换后最长重复字符串
    public int characterReplacement(String s, int k) {
        // 可以理解为：维护一个滑动窗口  找出里面相同数字最多的一个元素 然后用窗口内的总数-最多的元素 即这些数是需要被替换掉的 如果要替换的小于k说明窗口还能继续扩  大于k了说明需要缩小左边的窗口了
        int l = 0, r = 0, length = 0;
        int[] windowCharCount = new int[26];
        int windowSameCountMax = 0;

        while (r < s.length()) {
            int i = s.charAt(r) - 'A';
            windowCharCount[i]++;
            // 开始找里面数量最大的一个元素
            windowSameCountMax = Math.max(windowSameCountMax, windowCharCount[i]);
            r++;
            //>k说明要替代的太多了 替代不完 需要缩短l了
            while (l < r && r - l - windowSameCountMax > k) {
                int temp = s.charAt(l) - 'A';
                windowCharCount[temp]--;
                l++;
            }
            if (r - l - windowSameCountMax <= k) {
                length = Math.max(r - l, length);
            }
        }
        return length;
    }

    /**
     * 滴雨水问题  双指针
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int sum=0;
        // 左边最高的柱子 和右边最高的柱子 中最小的一个 - 当前柱子的高度
        // 使用双指针 只先计算最小的数/
        int left=0,right=height.length-1,lMax=0,rMax=0;
        while(left<right){
            //先计算能确定最小的数
            lMax=Math.max(lMax,height[left]);
            rMax=Math.max(rMax,height[right]);
            // 先计算右边的数
            if(lMax>rMax){
                sum+=rMax-height[right];
                right--;
            }else{
                sum+=lMax-height[left];
                left++;
            }
        }
        return sum;
    }
// 使用双指针 11. 盛最多水的容器
    public static int maxArea(int[] height) {
       int l=0,r=height.length-1;
      // 公式=min(l,r)*(r-l)
       int sum=0;
       while (l<r){
         int curSum=Math.min(height[l],height[r])*(r-l);
         sum=Math.max(sum,curSum);
         // 移动高度较低的一边  下一个可能高度更高
           if(height[l]<height[r]){
               // 找到下一个比l更大的 才去移动他
               int k=l;
               while (k<r){
                   if(height[k]>height[l]){

                       break;
                   }else {
                       k++;
                   }
               }
               l=k;
          //     l++;
           }else {
           //    r--;
               // 找到下一个比r更大的 才去移动他
               int k=r;
               while (k>l){
                   if(height[k]>height[r]){
                       break;
                   }else {
                       k--;
                   }
               }
               r=k;
           }
       }
       return sum;

    }
// 80. 删除有序数组中的重复项  2可以替换为任意k

    public static int removeDuplicates(int[] nums) {
        if(nums.length<=2){
            return nums.length;
        }
        // 处理下标  可以直接从2开始 前面两个都是没有问题的
        int index=2;
        // 比如说最多只能有两个相同的
        // 用来记录实际赋值的下标
        int fast=2;
        while (fast<nums.length){
            // 相同了 说明这个fast对应的值不能用 fast要往前走  此时index还没有走 因为他的值还没有确定 先等fast找到不相同的值再走
           if(nums[index-2]==nums[fast]){
               fast++;
           }else {
               nums[index++]=nums[fast++];
           }
        }
        System.out.println(Arrays.toString(nums));
        return index-1;
    }
    public static String longestCommonPrefix(String[] strs) {
        String str = "";
        int index = 0;
        // 拿第一个做比较
        String firstStr = strs[0];
        if (strs.length == 1) {
            return firstStr;
        }

        // 找出长度最短的字符串
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }
        // 最多比较minlength的长度
        for (int k = 0; k < minLength; k++) {
            boolean flag=true;
            for (int i = 1; i < strs.length; i++) {
                String str1 = strs[i];
                char c = str1.charAt(index);
                if (firstStr.charAt(index) != c) {
                     flag=false;
                     break;
                }
            }
            if(flag){
                str+=strs[0].charAt(index++);
            }
        }
        return str;
    }
    // 滑动窗口去找
    public static int minSubArrayLen(int target, int[] nums) {
       int left=0,right=0,length=Integer.MAX_VALUE,sum=0;
       while (right<nums.length){
           int num = nums[right];
           right++;
           sum+=num;
           while (left<right&&sum>=target){
               if(sum==target){
                   length=Math.min(length,right-left);
               }
               int num1 = nums[left];
               sum-=num1;
               left++;
           }
       }
       return length==Integer.MAX_VALUE?0:length;
    }

    public static void main(String[] args) {
      /*  boolean s = test1("abc", "cbdabbbdsss");
        System.out.println(s);*/
        int i = minSubArrayLen(11,new int[]{1,2,3,4,5});
        System.out.println(i);
    }

}
