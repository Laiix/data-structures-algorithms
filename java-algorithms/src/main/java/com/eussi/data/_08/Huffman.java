package com.eussi.data._08;
import java.util.*;

import static com.eussi.util.PrintUtil.println;

/**
 * @author wangxueming
 * @create 2020-01-23 14:13
 * @description
 */
public class Huffman {
    public Map<Character, String> map_char_code = new HashMap<Character, String>(); // 编码用代码表
    public Map<String, Character> map_code_char = new HashMap<String, Character>(); // 解码用代码表

    // 编码分为四步
    // 1.统计字符频率
    // 2.生成Huffman树
    // 3.生成编解码用代码表
    // 4.编码字符串
    public String encode(String str) {
        char[] cchar = str.toCharArray();

        // 1.统计字符频率
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < cchar.length; i++) {
            if (map.containsKey(cchar[i])) {
                map.put(cchar[i], map.get(cchar[i]) + 1);
            } else {
                map.put(cchar[i], 1);
            }
        }

        // 2.生成Huffman树
        // 先由所有字符生成单节点树的森林
        // 然后根据优先级合成单节点树为一棵树
        Iterator<Map.Entry<Character, Integer>> it = map.entrySet().iterator();
        Queue<TreeWeight<Character>> forest = new PriorityQueue<>();//优先级对列
        while (it.hasNext()) { // 生成单节点树
            Map.Entry<Character, Integer> entry = it.next();
            TreeWeight<Character> temp = new TreeWeight<>();        //实现compare接口
            temp.root = new TreeNode<>(entry.getKey());
            temp.weight = entry.getValue();
            forest.add(temp);
        }

        while (forest.size() > 1) { // 把单节点树合并为一棵树立
            TreeWeight<Character> t1 = forest.remove();
            TreeWeight<Character> t2 = forest.remove();
            TreeWeight<Character> t3 = new TreeWeight<>();
            t3.root = new TreeNode<>('+');
            t3.weight = t1.weight + t2.weight;
            t3.root.leftChild = t1.root;
            t3.root.rightChild = t2.root;
            forest.add(t3);
        }
        TreeWeight<Character> t = forest.remove(); // 最后一棵树

        t.displayTree();

        // 3.生成编解码用map
        preOrder(t.root, "", map_char_code, map_code_char);

        println("frequency map:");
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        println("encode map:");
        for (Map.Entry<Character, String> entry : map_char_code.entrySet()) {
            println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        println("encode map:");
        for (Map.Entry<String, Character> entry : map_code_char.entrySet()) {
            println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }


        // 4.编码字符串
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < cchar.length; i++) {
            output.append(map_char_code.get(cchar[i]));
        }
        return output.toString();
    }

    // 遍历Huffman树生成编解码代码表
    private void preOrder(TreeNode<Character> localRoot,
                          String code,
                          Map<Character, String> map_char_code,
                          Map<String, Character> map_code_char) {
        if (localRoot != null) {
            if (localRoot.leftChild==null && localRoot.rightChild==null) { //也可以判断叶子结点
                map_char_code.put(localRoot.data, code);
                map_code_char.put(code, localRoot.data);
            }
            preOrder(localRoot.leftChild, code + "0", map_char_code,
                    map_code_char);
            preOrder(localRoot.rightChild, code + "1", map_char_code,
                    map_code_char);
        }
    }

    // 解码
    // 根据确码代码表还原信息，也可以使用BinaryTree中描述的沿着树的路径来解码
    public String decode(String str) {
        StringBuilder result = new StringBuilder();
        StringBuffer charCode = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            charCode.append(str.charAt(i));
            if (map_code_char.get(charCode.toString()) != null) {
                result.append(map_code_char.get(charCode.toString()));
                charCode = new StringBuffer();
            }
        }
        return result.toString();
    }
}
