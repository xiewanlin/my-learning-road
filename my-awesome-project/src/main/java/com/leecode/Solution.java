package com.leecode;

/**
 * @Author: xiewanlin
 * @Date: 2020/10/10
 */

public class Solution {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode result = null;
    int sum,a=0;
    while (l1!=null || l2!=null) {
      sum = l1.val+l2.val+a;
      a=sum/10;
    }

    return result;
  }
}

//public class Solution {
//  public String convert(String s, int numRows) {
//    if (numRows<2){
//      return s;
//    }
//    StringBuffer []result = new StringBuffer[numRows];
//    int i=0;
//    int flag=-1;
//    for (int j=0;j<s.length();j++){
//      result[i].append(s.charAt(j));
//      if (i==0 || i==numRows-1){
//        flag=-flag;
//      }
//      i+=flag;
//    }
//    StringBuffer res = new StringBuffer();
//    for (StringBuffer buffer:result)
//      res.append(buffer.toString());
//    return res.toString();
//  }
//}

/**
 * 二叉搜索树最小绝对差
 * */
//class TreeNode {
//
//  int val;
//  TreeNode left;
//  TreeNode right;
//
//  TreeNode(int x) {
//    val = x;
//  }
//}
//
//public class Solution {
//  private int pre;
//  private int result;
//  public int getMinimumDifference(TreeNode root) {
//    result=Integer.MAX_VALUE;
//    pre=-1;
//    dfs(root);
//    return result;
//  }
//  private void dfs(TreeNode root){
//    if (root==null){
//      return;
//    }
//    dfs(root.left);
//    getMinData(root);
//    dfs(root.right);
//  }
//  private void getMinData(TreeNode node) {
//    if (pre==-1){
//      pre=node.val;
//    } else {
//      result = Math.min(result,node.val-pre);
//      pre = node.val;
//    }
//  }
//}
/**
 * 最长回文子串
 * */
//public class Solution {
//
//  public static void main(String[] args) {
//    System.out.println("0123456".substring(2, 4));
//  }
//
//  public String longestPalindrome(String s) {
////    return force(s);
////    return dp(s);
//    return center(s);
//  }
//
//  private String center(String s) {
//    if (s.length() < 2) {
//      return s;
//    }
//    int max = 1;
//    String result = s.substring(0, 1);
//    for (int i = 0; i < s.length(); i++) {
//      String odd = centerSpread(s, i, i);
//      String even = centerSpread(s, i, i + 1);
//      String str = odd.length() > even.length() ? odd : even;
//      if (max < str.length()) {
//        result = str;
//        max = str.length();
//      }
//    }
//    return result;
//  }
//
//  private String centerSpread(String s, int left, int right) {
//    while (left >= 0 && right < s.length()) {
//      if (s.charAt(left) == s.charAt(right)) {
//        left--;
//        right++;
//      } else {
//        break;
//      }
//    }
//    return s.substring(left + 1, right);
//  }
//
//  private String dp(String s) {
//    if (s.length() < 2) {
//      return s;
//    }
//    int max = 1, begin = 0;
//    char[] charArray = s.toCharArray();
//    boolean[][] dp = new boolean[s.length()][s.length()];
//    for (int i = 0; i < s.length(); i++) {
//      dp[i][i] = true;
//    }
//    for (int j = 1; j < s.length(); j++) {
//      for (int i = 0; i < j; i++) {
//        if (charArray[i] != charArray[j]) {
//          dp[i][j] = false;
//        } else {
//          if (j - i < 3) {
//            dp[i][j] = true;
//          } else {
//            dp[i][j] = dp[i + 1][j - 1];
//          }
//        }
//        if (dp[i][j] && j - i + 1 > max) {
//          max = j - i + 1;
//          begin = i;
//        }
//      }
//    }
//    return s.substring(begin, begin + max);
//  }
//
//  private String force(String s) {
//    if (s.length() < 2) {
//      return s;
//    }
//    int max = 1, begin = 0;
//    char[] charArray = s.toCharArray();
//    for (int i = 0; i < charArray.length - 1; i++) {
//      for (int j = i + 1; j < charArray.length; j++) {
//        if (j - i + 1 > max && checkPalindrome(charArray, i, j)) {
//          max = j - i + 1;
//          begin = i;
//        }
//      }
//    }
//    return s.substring(begin, begin + max);
//  }
//
//  private boolean checkPalindrome(char[] charArray, int min, int max) {
//    while (min < max) {
//      if (charArray[min] != charArray[max]) {
//        return false;
//      }
//      max--;
//      min++;
//    }
//    return true;
//  }
//}

/**
 * 链表两数相加
 * */
//class ListNode {
//
//  int val;
//  ListNode next;
//
//  ListNode() {
//  }
//
//  ListNode(int val) {
//    this.val = val;
//  }
//
//  ListNode(int val, ListNode next) {
//    this.val = val;
//    this.next = next;
//  }
//}
/*
public class Solution {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode result = null, node = null;
    int sum, a = 0;
    while (l1 != null || l2 != null) {
      int x = l1 == null ? 0 : l1.val;
      int y = l2 == null ? 0 : l2.val;
      sum = x + y + a;
      a = sum / 10;
      if (result == null) {
        node = new ListNode(sum % 10);
        result = node;
      } else {
        node.next = new ListNode(sum % 10);
        node = node.next;
      }
      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
    }
    if (a > 0) {
      node.next = new ListNode(a);
    }
    return result;
  }
}*/

/**
 * 链表找环入口
 * */
/*class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}
public class Solution {

  public ListNode detectCycle(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode slow = head, fast = head, t = head;
    while (fast != null) {
      slow = slow.next;
      if (fast.next != null) {
        fast = fast.next.next;
      } else {
        return null;
      }
      if (slow == fast) {
        while (slow != t) {
          slow = slow.next;
          t = t.next;
        }
        return t;
      }
    }
    return null;
  }
}*/
