package com.revature.pens.ui;

import java.util.List;

public interface IMenu {
    void start();


    //todo remove these default lines and fix login menu and Main drivers call to login menu
    default void header(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= s.length() + 3; i++) {
            if (i == 0 || i == s.length() + 3) sb.append('+');
            else sb.append('-');
        }

        for (int j = 0; j < 3; j++) {
            if(j != 1) {
                System.out.print(sb);
            }else System.out.print("| " + s + " |");
            System.out.println();
        }
    }

    default void comLineList(List<String> choices){
        for (int i = 0; i < choices.size(); i++) {
            System.out.println("[" + i + "] " + choices.get(i));
        }
        System.out.println("[x] Back");
    }
}
