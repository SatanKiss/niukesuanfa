package com.niuke.richang;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @source LeetCode第20题
 * @resource 数据结构和算法
 * @difficulty 简单
 * @description 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * @example1 输入：s = "(]"
 * 输出：false
 * @example2 输入：s = "()[]{}"
 * 输出：true
 * @note 1 <= s.length <= 10^4
 * s 仅由括号 '()[]{}' 组成
 * @analysis 这题是让判断字符串 s 是否是有效的括号，对于括号匹配问题，我们首先想到的是使用栈。
 * 因为字符串 s 只包含 '()[]{}' ，如果遇到右括号，比如 ')'，']'，'}' ，就把与它们对应的左括号压入到栈中。
 * 如果遇到左括号，比如 '('，'['，'{' ，栈顶元素就出栈，然后判断左括号和出栈的元素是否相等，如果不相等或者栈为空，说明不是有效的括号，直接返回 false 。
 */
public class LeetCode20 {
    public boolean isValidStandand(String s) {
        Stack<Character> stk = new Stack<>();// 创建一个栈
        for (char ch : s.toCharArray()) {
            // 如果是左括号，就把他们对应的右括号压栈
            if (ch == '(') {
                stk.push(')');
            } else if (ch == '{') {
                stk.push('}');
            } else if (ch == '[') {
                stk.push(']');
            } else if (stk.isEmpty() || stk.pop() != ch) {
                return false;
            }
        }
        // 如果是有效的括号，左括号和右括号必须匹配，栈为空，否则就无效。
        return stk.isEmpty();
    }

    public boolean isValidPersonal(String s) {
        //进行规则校验
        if (s.isEmpty() || s.length() > Math.pow(10, 4)) {
            return false;
        }
        if (!isRegex(s)) {
            return false;
        }
        //算法思想 需要加强
        Stack<Character> stk = new Stack<>();// 创建一个栈
        for (char ch : s.toCharArray()) {
            // 如果是左括号，就把他们对应的右括号压栈
            if (ch == '(') {
                stk.push(')');
            } else if (ch == '{') {
                stk.push('}');
            } else if (ch == '[') {
                stk.push(']');
            } else if (stk.isEmpty() || stk.pop() != ch) {
                return false;
            }
        }
        // 如果是有效的括号，左括号和右括号必须匹配，栈为空，否则就无效。
        return stk.isEmpty();
    }

    //校验字符串内容是否合规
    public boolean isRegex(String s) {
        String regex = "^[\\[\\]\\(\\)\\{\\}]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String input = "{[()]}";
        // 使用正则表达式匹配包含()[]{}这三种括号的字符串
        String regex = "^[\\[\\]\\(\\)\\{\\}]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            System.out.println("字符串只包含()[]{}这三种括号。");
        } else {
            System.out.println("字符串包含其他字符或缺少()[]{}这三种括号。");
        }
    }

}