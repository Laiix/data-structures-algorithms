package com.eussi.data._09;

import java.util.Stack;

import static com.eussi.util.Func.repeatPrint;
import static com.eussi.util.Func.repeatPrintln;
import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

/**
 * 手写红黑树，仅包含插入逻辑
 */
public class RBTree<T extends Comparable<T>> {
    private RBTreeNode<T> root;
    public RBTree() {
    }

    public void insert(T... data) {
        for (T t : data) {
            insert(t);
        }
    }

    /**
     * 插入逻辑：
     *  在顺着树向下查找插入点时,只要发现一个黑色节点有两个红色的子节点(违反规则2)就执
     *  行一次颜色变换。有时颜色变换会造成红-红颜色冲突(违反规则3)。称红色子节点为Ⅹ,红色父
     *  节点为P。只用一次或者两次旋转就可以解决这个冲突,旋转次数由X是G的外侧子孙节点还是内
     *  侧子孙节点来决定。执行了颜色变换和旋转之后,继续向下查找插入点,并且插入新的数据项。
     *  在完成新节点X的插入之后,如果P是黑色的,只需要连接这个新的红色节点。如果P是红色
     *  的,则有两种可能性:Ⅹ是G的外侧子孙节点,或者Ⅹ是G的内侧子孙节点。需要两次改变节点
     *  颜色(稍后将看到如何改变)。如果X是外侧节点,则执行一次旋转,而如果X是内侧节点,则执
     *  行两次旋转。这能使树达到平衡状态
     * @param data
     */
    public void insert(T data) {
        RBTreeNode<T> newNode = new RBTreeNode<>(data);//默认红色
        if(root==null) {
            newNode.setRed(false);
            root = newNode;
        } else {
            RBTreeNode<T> pGNode = null;//祖父的父节点
            RBTreeNode<T> gNode = null;//祖父节点
            RBTreeNode<T> pNode = null;//父节点
            RBTreeNode<T> xNode = root;//当前节点

            while (true) {
                //检查是否需要颜色变化 即一黑两红
                while(!xNode.isRed()
                        && xNode.getLeftChild()!=null
                        && xNode.getRightChild()!=null
                        && xNode.getLeftChild().isRed()
                        && xNode.getRightChild().isRed()) {
                    //颜色变换
                    if(root!=xNode) {
                        xNode.changeColor();
                    }
                    xNode.getLeftChild().changeColor();
                    xNode.getRightChild().changeColor();

                    //变色导致两红，需要旋转 此时，父节点和祖父节点必须不为空，否则不会出现这种情况
                    if(gNode!=null
                            && pNode!=null
                            && pNode.isRed()
                            && xNode.isRed()) {
                        changeColorAndRotate(pGNode, gNode, pNode, xNode);
                    }
                }

                //向下遍历，比那个插入
                pGNode = gNode;
                gNode = pNode;
                pNode = xNode;

                //从上到下遍历
                if(xNode.getData().compareTo(data)>0) {//假设无重复节点
                    if(xNode.getLeftChild()==null) {
                        xNode.setLeftChild(newNode);
                    } else {
                        xNode = xNode.getLeftChild();
                        continue;//如果还不到插入的地方，继续循环
                    }
                } else {
                    if(xNode.getRightChild()==null) {
                        //插入
                        xNode.setRightChild(newNode);
                    } else {
                        xNode = xNode.getRightChild();
                        continue;//如果还不到插入的地方，继续循环
                    }
                }
                xNode = newNode;
                if (gNode == null || !pNode.isRed()) {//插入到第二层 或者 p是黑色的，已完成插入
                    break;
                }

                //插入之后，检验是否符合条件，否则改变颜色和旋转
                changeColorAndRotate(pGNode, gNode, pNode, xNode);
                break;
            }
        }
    }

    /**
     * 改变颜色和旋转
     * @param pGNode
     * @param gNode
     * @param pNode
     * @param xNode
     */
    private void changeColorAndRotate(RBTreeNode<T> pGNode, RBTreeNode<T> gNode, RBTreeNode<T> pNode, RBTreeNode<T> xNode) {
        //均在左外侧节点
        if(gNode.getLeftChild()== pNode
                && pNode.getLeftChild()== xNode) {
            //变色
            gNode.changeColor();
            pNode.changeColor();

            //旋转
            gNode.setLeftChild(pNode.getRightChild());
            pNode.setRightChild(gNode);
            if(root== gNode) {
                root = pNode;
            } else {
                if(pGNode.getLeftChild()== gNode) {
                    pGNode.setLeftChild(pNode);
                } else {
                    pGNode.setRightChild(pNode);
                }
            }
        //均在右外侧节点
        } else if(gNode.getRightChild()== pNode
                && pNode.getRightChild()== xNode) {
            //变色
            gNode.changeColor();
            pNode.changeColor();

            //旋转
            gNode.setRightChild(pNode.getLeftChild());
            pNode.setLeftChild(gNode);
            if(root== gNode) {
                root = pNode;
            } else {
                if(pGNode.getLeftChild()== gNode) {
                    pGNode.setLeftChild(pNode);
                } else {
                    pGNode.setRightChild(pNode);
                }
            }
        //内侧子孙节点
        } else if(gNode.getLeftChild()== pNode
                && pNode.getRightChild()== xNode) {
            //变色
            gNode.changeColor();
            xNode.changeColor();
            //第一次旋转
            pNode.setRightChild(xNode.getLeftChild());
            xNode.setLeftChild(pNode);
            gNode.setLeftChild(xNode);
            //第二次旋转
            gNode.setLeftChild(xNode.getRightChild());
            xNode.setRightChild(gNode);
            if(root== gNode) {
                root = xNode;
            } else {
                if(pGNode.getLeftChild()== gNode) {
                    pGNode.setLeftChild(xNode);
                } else {
                    pGNode.setRightChild(xNode);
                }
            }
            //内侧子孙节点
        } else if(gNode.getRightChild()== pNode
                && pNode.getLeftChild()== xNode) {
            //变色
            gNode.changeColor();
            xNode.changeColor();
            //第一次旋转
            pNode.setLeftChild(xNode.getRightChild());
            xNode.setRightChild(pNode);
            gNode.setRightChild(xNode);
            //第二次旋转
            gNode.setRightChild(xNode.getLeftChild());
            xNode.setLeftChild(gNode);
            if(root== gNode) {
                root = xNode;
            } else {
                if(pGNode.getLeftChild()== gNode) {
                    pGNode.setLeftChild(xNode);
                } else {
                    pGNode.setRightChild(xNode);
                }
            }
        }
    }

    public void display() {
        int dotWidth = 64;
        repeatPrintln(dotWidth, ".");

        Stack<RBTreeNode<T>> outerStack = new Stack<>();
        outerStack.push(root);

        boolean hasNextRow = true;//记录下一层是否还有数据
        int nBlanks = dotWidth/2;
        while (hasNextRow) {
            Stack<RBTreeNode<T>> innerStack = new Stack<>();
            hasNextRow = false;
            repeatPrint(nBlanks, " ");
            //取出一层的数据，进行打印，然后下一层如果有，把下一层入栈
            while (!outerStack.isEmpty()) {
                RBTreeNode<T> temp = outerStack.pop();
                if (temp != null) {
                    print(temp);
                    innerStack.push(temp.getLeftChild());
                    innerStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null || temp.getRightChild() != null) {
                        hasNextRow = true;    //任何一个包含子节点就会将此值设置为false
                    }
                } else {
                    print("----");
                    innerStack.push(null);//特殊数据，用来打印空值
                    innerStack.push(null);
                }
                repeatPrint(nBlanks * 2 - 4, " ");
            }
            println();
            nBlanks /= 2;
            while (!innerStack.isEmpty()) {
                outerStack.push(innerStack.pop());
            }
        }
        repeatPrintln(dotWidth, ".");
    }

}
