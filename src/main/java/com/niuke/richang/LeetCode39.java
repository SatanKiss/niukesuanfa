package com.niuke.richang;

import java.util.ArrayList;
import java.util.List;

/**
 * @source LeetCode第39题
 * @resource 数据结构和算法
 * @difficulty 中等
 * @description 给你一个无重复元素的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的所有不同组合 ，并以列表形式返回。你可以按任意顺序返回这些组合。
 * candidates 中的同一个数字可以无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * @example1 输入：candidates = [2,3,6,7], target = 7
 *           输出：[[2,2,3],[7]]
 *           解释：
 *           2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 *           7 也是一个候选， 7 = 7 。
 *           仅有这两种组合。
 * @example2 输入: candidates = [2,3,5], target = 8
 *           输出: [[2,2,2,2],[2,3,3],[3,5]]
 * @note 1 <= candidates.length <= 30
 *       2 <= candidates[i] <= 40
 *       candidates 的所有元素互不相同
 *       1 <= target <= 40
 * @analysis 这题让从数组中找出一些元素，让他们的和等于 target ，问有多少个这种组合，每个元素可以使用无数次，但不能有重复组合，比如[1,2]和[2,1]实际上属于一种组合。
 * 这是一道经典的回溯算法问题，对于所有的回溯算法我们都可以把它当做一棵树来解决。对于这道题来说因为数组中没有重复的元素，为了防止出现重复的组合，当我们选择当前元素的时候，下一步就不能再选择他前面的元素，否则就会出现类似于[1,2]和[2,1]的这两种结果。我们以示例 1 为例画个图看下选择的过程。
 * 对于这棵树每一个分支的选择都会依赖于父节点的选择，比如父节点选择了 3 ，那么子节点就只能选择 3 以及他后面的元素，不能选择他前面的，否则就会出现重复的组合。
 * 这里只需要计算所有的从根节点到叶子节点路径上的节点和，判断这个路径上的和是否等于 target ，如果等于，说明他就是我们要找的，直接把它添加到结果集中。
 * 这里的关键点是怎么判断是否是叶子节点，其实很简单，如果从根节点到当前节点累加的值等于或大于 target 了，就表示到了叶子节点，因为数组中的元素都是正整数，越往下累加值就会越大，所以当累加值等于或大于 target 的时候，当前节点就是叶子节点。
 * 对于路径上的节点当往下走的时候要选择，往回走的时候要记得移除，这就是回溯算法，最后我们再来看下代码。
 */
public class LeetCode39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();// 返回的结果集
        dfs(ans, new ArrayList<>(), candidates, target, 0);
        return ans;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path,
                     int candidates[], int target, int start) {
        // 因为数组中的元素都是正数，所以这里当target <= 0的时候要终止
        if (target <= 0) {
            if (target == 0) // 如果找到一组解就把他保存下来
                res.add(new ArrayList<>(path));
            return;
        }
        // 这里要注意，因为不能有重复的组合，所以循环的起始位置不是0，
        // 否则会出现类似于[1,2]和[2,1]这种重复的组合。
        for (int i = start; i < candidates.length; i++) {
            // 递归的三步走，选择当前元素
            path.add(candidates[i]);
            // 递归，到树的下一层，这里target要减去当前元素的值
            dfs(res, path, candidates, target - candidates[i], i);
            // 递归往回走要撤销选择
            path.remove(path.size() - 1);
        }
    }
}
