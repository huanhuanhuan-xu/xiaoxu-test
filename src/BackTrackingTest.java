
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BackTrackingTest {
    /**
     * 46. 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * @param nums
     * @return
     */


    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        backTracking(nums, list, new ArrayList<>());
        return list;
    }

    private static void backTracking(int[] nums, List<List<Integer>> lists, List<Integer> tempList) {
        if (tempList.size() == nums.length) {
            lists.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!tempList.contains(nums[i])) {
                tempList.add(nums[i]);
                backTracking(nums, lists, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的
     * 子集
     * （幂集）。
     * <p>
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backTracking1(nums, list, new ArrayList<>(), 0);

        return list;
    }

    private static void backTracking1(int[] nums, List<List<Integer>> lists, List<Integer> tempList, int index) {
        lists.add(new ArrayList<>(tempList));
        for (int i = index; i < nums.length; i++) {
            tempList.add(nums[i]);
            backTracking1(nums, lists, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }


    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();

        backTrackingCombine(n, lists, new ArrayList<>(), k, 1);
        return lists;
    }

    private static void backTrackingCombine(int n, List<List<Integer>> lists, List<Integer> tempList, int k, int startIndex) {
        if (k == tempList.size()) {
            lists.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = startIndex; i <= n - (k - tempList.size()) + 1; i++) {
            tempList.add(i);
            backTrackingCombine(n, lists, tempList, k, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * 216. 组合总和 III
     *
     * @param k
     * @param n
     * @return
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists = new ArrayList<>();

        backTrackingCombine3(n, lists, new ArrayList<>(), k, 1, 0);
        return lists;
    }

    private static void backTrackingCombine3(int n, List<List<Integer>> lists, List<Integer> tempList, int k, int startIndex, int sum) {
        // 不需要继续了 直接结束
        if (sum > n) {
            return;
        }
        if (k == tempList.size()) {
            if (sum == n) {
                lists.add(new ArrayList<>(tempList));
            }
            return;
        }
        for (int i = startIndex; i <= 9 - (k - tempList.size()) + 1; i++) {
            tempList.add(i);
            backTrackingCombine3(n, lists, tempList, k, i + 1, sum + i);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 17. 电话号码的字母组合
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> list = new ArrayList<>();
        backTracking2(digits, mapping, list, "", digits.length());

        return list;
    }

    private static void backTracking2(String digits, String[] mapping, List<String> list, String tempStr, int k) {
        if (tempStr.length() == k) {
            list.add(tempStr);
            return;
        }
        // tempStr.length()表示层数
        String tempMapping = mapping[digits.charAt(tempStr.length()) - '0'];
        for (int i = 0; i < tempMapping.length(); i++) {
            tempStr += tempMapping.charAt(i);
            backTracking2(digits, mapping, list, tempStr, k);
            tempStr = tempStr.substring(0, tempStr.length() - 1);
        }
    }

    // 39. 组合总和
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        tracking(candidates, target, lists, new ArrayList<>(), 0, 0);
        return lists;
    }

    private static void tracking(int[] candidates, int target, List<List<Integer>> lists, List<Integer> tempList, int sum, int startIndex) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            lists.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            tempList.add(candidates[i]);
            tracking(candidates, target, lists, tempList, sum + candidates[i], i);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 总数求和2
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(candidates);
        tracking2(candidates, target, lists, new ArrayList<>(), 0, 0);
        return lists;
    }

    private static void tracking2(int[] candidates, int target, List<List<Integer>> lists, List<Integer> tempList, int sum, int startIndex) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            lists.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            tempList.add(candidates[i]);
            tracking2(candidates, target, lists, tempList, sum + candidates[i], i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 131. 分割回文串
    public static List<List<String>> partition(String s) {
        List<List<String>> lists = new ArrayList<>();
        backTracking1(s, lists, new ArrayList<>(), 0);
        return lists;
    }


    private static void backTracking1(String s, List<List<String>> lists, List<String> tempList, int startIndex) {
        if (startIndex == s.length()) {
            lists.add(new ArrayList<>(tempList));
            return;
        }
        // startIndex表示切割的起始位置
        for (int i = startIndex; i < s.length(); i++) {
            String tempStr = s.substring(startIndex, i + 1);
            if (!judgeHuiwen(tempStr)) {
                continue;
            }
            tempList.add(tempStr);
            backTracking1(s, lists, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    private static boolean judgeHuiwen(String s) {
        for (int i = 0; i <= s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static List<String> restoreIpAddresses(String s) {
        List<List<String>> list = new ArrayList<>();
        back(s, list, new ArrayList<>(), 0);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> tempList = list.get(i);
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < tempList.size(); j++) {
                str.append(tempList.get(j)).append(".");
            }
            result.add(str.substring(0, str.length() - 1));
        }
        return result;
    }

    private static void back(String s, List<List<String>> list, List<String> tempStr, int startIndex) {
        if (startIndex == s.length() && tempStr.size() == 4 && validIpArr(tempStr)) {
            list.add(new ArrayList<>(tempStr));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String tempSubstring = s.substring(startIndex, i + 1);
            if (!validIpStr(tempSubstring)) {
                continue;
            }

            List<String> judge = new ArrayList<>(tempStr);
            judge.add(tempSubstring);
            if (judge.size() > 4 || !validIpArr(tempStr)) {
                continue;
            }
            tempStr.add(tempSubstring);
            back(s, list, tempStr, i + 1);
            tempStr.remove(tempStr.size() - 1);
        }

    }


    private static boolean validIpStr(String str) {
        int value;
        try {
            value = Integer.parseInt(str);
        } catch (Exception e) {
            // 格式转换错误
            return false;
        }
        if (value < 0 || value > 255) {
            return false;
        }
        if (value != 0 && str.charAt(0) == '0') {
            return false;
        }
        if (value == 0 && str.length() != 1) {
            return false;
        }
        // 以0 开头 但是值却不是0
        return true;
    }

    private static boolean validIpArr(List<String> split) {
        for (String s : split) {
            int value;
            try {
                value = Integer.parseInt(s);
            } catch (Exception e) {
                // 格式转换错误
                return false;
            }
            if (value < 0 || value > 255) {
                return false;
            }

            if (value != 0 && s.charAt(0) == '0') {
                return false;
            }
            if (value == 0 && s.length() != 1) {
                return false;
            }
        }
        return true;
    }

    // 78 子集
    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        Arrays.sort(nums);
        backTracking111(lists, tempList, nums, 0);
        return lists;
    }

    private static void backTracking111(List<List<Integer>> lists, List<Integer> tempList, int[] nums, int startIndex) {
        lists.add(new ArrayList<>(tempList));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            tempList.add(nums[i]);
            backTracking111(lists, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 491. 非递减子序列
    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        back(lists, tempList, nums, 0);
        return lists;
    }

    private static void back(List<List<Integer>> lists, List<Integer> tempList, int[] nums, int startIndex) {
        if (tempList.size() > 1) {
            lists.add(new ArrayList<>(tempList));
        }

        Set<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            // 列表长度为0 或者最后一位<=当前数字 才会放进去 否则直接跳出当前循环
            if (!tempList.isEmpty() && tempList.get(tempList.size() - 1) > nums[i]) {
                continue;
            }
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            tempList.add(nums[i]);
            back(lists, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 47. 全排列 II  给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        Arrays.sort(nums);
        back1(lists, tempList, nums, new ArrayList<>());
        return lists;
    }

    private static void back1(List<List<Integer>> lists, List<Integer> tempList, int[] nums, List<Integer> indexList) {
        if (tempList.size() == nums.length) {
            lists.add(new ArrayList<>(tempList));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexList.contains(i)) {
                continue;
            }
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            indexList.add(i);
            tempList.add(nums[i]);
            back1(lists, tempList, nums, indexList);
            tempList.remove(tempList.size() - 1);
            indexList.remove(indexList.size() - 1);
        }
    }

    // 51 n皇后
    public static List<List<String>> solveNQueens(int n) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        int[] nums = new int[n];
        backQueue(lists, tempList, nums);

        List<List<String>> strLists = new ArrayList<>();
        for (List<Integer> integerList : lists) {
            List<String> strTempList = new ArrayList<>();
            for (Integer integer : integerList) {
                strTempList.add(getStr(integer, n));
            }
            strLists.add(strTempList);
        }
        return strLists;
    }

    private static String getStr(int i, int n) {
        StringBuilder str = new StringBuilder();
        for (int k = 0; k < n; k++) {
            str.append(k == i ? "Q" : ".");
        }
        return str.toString();
    }

    private static void backQueue(List<List<Integer>> lists, List<Integer> tempList, int[] nums) {
        // 先记录下标吧
        if (tempList.size() == nums.length) {
            System.out.println(tempList);
            lists.add(new ArrayList<>(tempList));
            return;
        }
        // 首先一人只能放一行 只需要记录j（列）的位置即可
        for (int j = 0; j < nums.length; j++) {
            // 先校验 能不能放在当前位置 可以就放
            if (!isValid(tempList, tempList.size(), j)) {
                continue;
            }
            tempList.add(j);
            backQueue(lists, tempList, nums);
            tempList.remove(tempList.size() - 1);
        }
    }

    private static boolean isValid(List<Integer> tempList, int i, int j) {
        // 每个值表示纵坐标
        for (int k = 0; k < tempList.size(); k++) {
            // 列
            int colun = tempList.get(k);
            // 行
            int row = k;
            if (colun == j) {
                return false;
            }
            // 说明在同一条斜线上
            if (Math.abs(colun - j) == Math.abs(row - i)) {
                //   System.out.println(colun + ":" + j + ":" + row + ":" + i);
                return false;
            }
        }
        return true;
    }

    // 37. 解数独
    public static void solveSudoku(char[][] board) {
        backSudoku(board);
    }

    private static boolean backSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 直接跳过数字
                if (board[i][j] != '.') {
                    continue;
                }
                for (char x = '1'; x <= '9'; x++) {
                    if (isValidSudoku(i, j, x, board)) {
                        board[i][j] = x;
                        if (backSudoku(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 判断棋盘是否合法有如下三个维度:
     * 同行是否重复
     * 同列是否重复
     * 9宫格里是否重复
     */
    private static boolean isValidSudoku(int row, int col, char val, char[][] board) {
        // 同行是否重复
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < 9; j++) {
            if (board[j][col] == val) {
                return false;
            }
        }
        // 9宫格里是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

/*    @Data
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }


        public static List<String> binaryTreePaths(TreeNode root) {
            List<String> list = new ArrayList<>();
            backTracking(root, list, "");
            return list;
        }

        private static void backTracking(TreeNode root, List<String> list, String tempStr) {
            if (root != null) {
                if (!tempStr.isEmpty()) {
                    tempStr += "->" + root.val;
                } else {
                    tempStr = root.val + "";
                }
                if (root.left == null && root.right == null) {
                    list.add(tempStr);
                }
                backTracking(root.left, list, tempStr);
                backTracking(root.right, list, tempStr);
            }
        }

        public static int maxProfit2(int[] prices) {
            // i表示最开始的买入时间 j表示卖出时间
            int[][] dp = new int[prices.length][prices.length];
            // i==j值就是0 可以同一天买入买出
         *//*   for (int i = 0; i < prices.length; i++) {
                for (int j = i; j < prices.length; j++) {
                    dp[i][j] = prices[j] - prices[i];
                }
            }*//*
            int maxProfit = 0;
       *//*     for (int i = 0; i < dp.length; i++) {
                for (int j = i; j < dp[i].length; j++) {
                    if (j - i == 1) {
                        dp[i][j] = prices[j] - prices[i];
                    }
                }
            }*//*

            // 选择怎么买 选择盈利的买
            for (int i = 0; i < dp.length; i++) {
                for (int j = i + 1; j < dp[i].length; j++) {
                 *//*   if (j - i == 1) {
                        //  dp[i][j] = prices[j] - prices[i];
                        continue;
                    }*//*
                    if (i + 1 < dp.length && j - 1 >= 0) {
                        dp[i][j] = Math.max(prices[j] - prices[i], Math.max(Math.max(dp[i + 1][j] + dp[i][j - 1], dp[i + 1][j]), dp[i][j - 1]));
                    } else if (i + 1 > dp.length) {
                        dp[i][j] = Math.max(prices[j] - prices[i], dp[i][j - 1]);
                    }
                }
            }

            for (int[] ints : dp) {
                System.out.println(Arrays.toString(ints));
            }

            return 0;
        }

        // 只能买卖一次
        public static int maxProfit(int[] prices) {
            int maxProfit = 0;
            int minPrice = Integer.MAX_VALUE;
            for (int price : prices) {
                if (price < minPrice) {
                    minPrice = price;
                }
                if (price - minPrice > maxProfit) {
                    maxProfit = price - minPrice;
                }
            }
            return maxProfit;
        }

        // dp[i][0] 表示第i天持有股票所得现金。
        //dp[i][1] 表示第i天不持有股票所得最多现金
        // 只能买卖一次
        public static int maxProfitWithDp(int[] prices) {
            int maxProfit = 0;
            int[][] dp = new int[prices.length][2];
            for (int i = 0; i < prices.length; i++) {
                if (i == 0) {
                    dp[i][0] = -prices[i];
                    dp[i][1] = 0;
                    continue;
                }
                // 有可能上一天就持有 也有可能是今天买的(上一天没有)
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
                // 上一天就没有 或者是今天卖的
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            }
            return dp[prices.length - 1][1];
        }

        public static int maxProfitWithTanxi(int[] prices) {
            int maxSize = 0;
            for (int i = 1; i < prices.length; i++) {
                maxSize += Math.max(prices[i] - prices[i - 1], 0);
            }
            return maxSize;
        }

        public static boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }
            String str = x + "";
            StringBuilder newStr = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                newStr.append(str.charAt(i));
            }
            return newStr.toString().equals(str);
        }

        public static void main(String[] args) {
            boolean i = isPalindrome(121);
            System.out.println(i);
           *//* TreeNode treeNode = new TreeNode(1);
            treeNode.left = new TreeNode(2);
            treeNode.right = new TreeNode(3);
            treeNode.left.right = new TreeNode(5);
            List<String> list = binaryTreePaths(treeNode);
            System.out.println(list);*//*

      *//*  List<List<Integer>> lists = subsets1(new int[]{1, 2, 2});
        System.out.println(lists);*//*

  *//*      String[][] strings = {{"5", "3", ".", ".", "7", ".", ".", ".", "."}, {"6", ".", ".", "1", "9", "5", ".", ".", "."}, {".", "9", "8", ".", ".", ".", ".", "6", "."}, {"8", ".", ".", ".", "6", ".", ".", ".", "3"}, {"4", ".", ".", "8", ".", "3", ".", ".", "1"}, {"7", ".", ".", ".", "2", ".", ".", ".", "6"}, {".", "6", ".", ".", ".", ".", "2", "8", "."}, {".", ".", ".", "4", "1", "9", ".", ".", "5"}, {".", ".", ".", ".", "8", ".", ".", "7", "9"}};

        char[][] chars = new char[strings.length][strings[0].length];
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                chars[i][j] = strings[i][j].charAt(0);
            }
        }
        solveSudoku(chars);
        System.out.println(Arrays.deepToString(chars));*//*


        }
    }*/
}
