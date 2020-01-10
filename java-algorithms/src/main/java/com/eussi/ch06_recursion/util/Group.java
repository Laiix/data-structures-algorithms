package com.eussi.ch06_recursion.util;

/**
 * @author wangxueming
 * @create 2020-01-10 16:56
 * @description
 */
public class Group {
    private char[] persons; // 组中所有可供选择的成员
    private boolean[] selects; // 标记成员选中与否

    public Group(char[] persons) {
        this.persons = persons;
        selects = new boolean[persons.length];
    }

    public void showTeams(int teamNumber) {
        showTeams(persons.length, teamNumber);
    }

    // =============================================
    // 找所有可能的解
    // totalNuber 总共有多少人供选择
    // teamNuber 需要选择多少人
    // =============================================
    // 从个n人中取出k个人的所可能方案，表示为方法参数(n,k)
    // 求(n,k)的所有解
    // 当k=0时得一个解，n<k时无解
    // 否则(n,k)-->(n-1,k-1)+(n-1,k)
    // =============================================
    // 列如 ：从3个人中选2个人，此时参数为(3,2)
    // (3,2)-->(2,1)+(2,2)-->(1,0)+(1,1)+(1,1)+(1,2)
    // (1,0)得到一个解,(1,2)无解
    // (1,0)+(1,1)+(1,1)+(1,2)-->(1,1)+(1,1)
    // (1,1)+(1,1)-->(0,0)+(0,1)+(0,0)+(0,1)
    // (0,0)得到一个解,(0,1)无解
    // 所以(3,2)一共有 3个解
    // 列如 ：从3个人中选3个人，此时参数为(3,3)
    // (3,3)-->(2,2)+(2,3)-->(2,2)-->(1,1)+(1,2)-->(1,1)-->(0,0)+(0,1)-->(0,0)
    // 即(3,3)有一个解
    // =============================================
    private void showTeams(int totalNumber, int teamNumber) {
        if (teamNumber == 0) { // teamNumber=0时, 找到一个解
            for (int i = 0; i < selects.length; i++) {
                if (selects[i] == true) {
                    System.out.print(persons[i] + " ");
                }
            }
            System.out.println();
            return;
        }
        if (totalNumber < teamNumber) { // totalNuber< teamNumber,无解
            return;
        }
        //选择了一个人
        selects[persons.length - totalNumber] = true;
        showTeams(totalNumber - 1, teamNumber - 1);

        //未选择一个人
        selects[persons.length - totalNumber] = false;
        showTeams(totalNumber - 1, teamNumber);
    }

}
