package hot100;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Objects;

/**
 * 如何通过子节点找到父节点呢？
 * 通过从头遍历到尾
 * 解法1：通过哈希表找出父节点
 * 解法2：递归。从root遍历，q、p可能在相同子树，可能在不同子树
 * 递归终止条件：当前节点为空或者 当前节点为q或者p，返回当前节点
 */
public class LowestCommonAncestor_236 {
    HashMap<TreeNode, TreeNode> childMapFather = new HashMap<>();

    // 非递归写法
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        ArrayDeque<Integer> stack1 = new ArrayDeque<>();
        ArrayDeque<Integer> stack2 = new ArrayDeque<>();
        traverse(root.left, root);
        traverse(root.right, root);

        TreeNode tmp1 = p;
        while (childMapFather.containsKey(tmp1)) {
            TreeNode father = childMapFather.get(tmp1);
            stack1.push(tmp1.val);
            tmp1 = father;
        }
        stack1.push(tmp1.val);
        TreeNode tmp2 = q;
        while (childMapFather.containsKey(tmp2)) {
            TreeNode father = childMapFather.get(tmp2);
            stack2.push(tmp2.val);
            tmp2 = father;
        }
        stack2.push(tmp2.val);
        int result = 0;
        while (Objects.equals(stack1.peek(), stack2.peek())) {
            result = stack1.pop();
            stack2.pop();

        }
        return new TreeNode(result);
    }

    // 找到父子关系map
    public void traverse(TreeNode node, TreeNode father) {
        if (node == null) {
            return;
        }
        childMapFather.put(node, father);
        traverse(node.left, node);
        traverse(node.right, node);
    }

    // 递归写法
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}
