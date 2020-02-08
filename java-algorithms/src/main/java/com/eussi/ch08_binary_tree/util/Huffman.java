package com.eussi.ch08_binary_tree.util;
import java.util.*;

/**
 * @author wangxueming
 * @create 2020-01-23 14:13
 * @description
 */
public class Huffman {
    public static Map<Character, String> map_char_code = new HashMap<Character, String>(); // 编码用代码表
    public static Map<String, Character> map_code_char = new HashMap<String, Character>(); // 解码用代码表

    // 编码分为四步
    // 1.统计字符频率
    // 2.生成Huffman树
    // 3.生成编解码用代码表
    // 4.编码字符串
    public static String encode(String str) {
        char[] cchar = str.toCharArray();

        // 1.统计字符频率
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        for (int i = 0; i < cchar.length; i++) {
            if (map.containsKey(cchar[i])) {
                map.put(cchar[i], map.get(cchar[i]).intValue() + 1);
            } else {
                map.put(cchar[i], 1);
            }
        }

        // 2.生成Huffman树
        // 先由所有字符生成单节点树的森林
        // 然后根据优先级合成单节点树为一棵树
        Queue<CharTree> forest = new PriorityQueue<CharTree>();
        Set<Map.Entry<Character, Integer>> set = map.entrySet();
        Iterator<Map.Entry<Character, Integer>> it = set.iterator();
        while (it.hasNext()) { // 生成单节点树
            Map.Entry<Character, Integer> en = it.next();
            CharTree temp = new CharTree();        //实现compare接口
            temp.root = new CharNode(en.getKey());
            temp.weight = en.getValue();
            forest.add(temp);
        }
        while (forest.size() > 1) { // 把单节点树合并为一棵树立
            CharTree t1 = forest.remove();
            CharTree t2 = forest.remove();
            CharTree t3 = new CharTree();
            t3.root = new CharNode();
            t3.weight = t1.weight + t2.weight;
            t3.root.leftChild = t1.root;
            t3.root.rightChild = t2.root;
            forest.add(t3);
        }
        CharTree t = forest.remove(); // 最后一棵树

        // 3.生成编解码用map
        preOrder(t.root, "", map_char_code, map_code_char);

        // 4.编码字符串
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < cchar.length; i++) {
            output.append(map_char_code.get(cchar[i]));
        }
        return output.toString();
    }

    // 遍历Huffman树生成编解码代码表
    private static void preOrder(CharNode localRoot, String code,
                                 Map<Character, String> map_char_code,
                                 Map<String, Character> map_code_char) {
        if (localRoot != null) {
//            if (localRoot.cchar != '\0') { //也可以判断叶子结点
              if (localRoot.leftChild==null && localRoot.rightChild==null) { //也可以判断叶子结点
                map_char_code.put(localRoot.cchar, code);
                map_code_char.put(code, localRoot.cchar);
            }
            preOrder(localRoot.leftChild, code + "0", map_char_code,
                    map_code_char);
            preOrder(localRoot.rightChild, code + "1", map_char_code,
                    map_code_char);
        }
    }

    // 解码
    // 根据确码代码表还原信息，也可以使用BinaryTree中描述的沿着树的路径来解码
    public static String decode(String str) {
        StringBuffer result = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            if (map_code_char.get(sb.toString()) != null) {
                result.append(map_code_char.get(sb.toString()));
                sb = new StringBuffer();
            }
        }
        return result.toString();
    }
}
