package com.revature.pens.util.custom_scanner;

import java.util.Scanner;

public class QuitScanner {
    private static QuitScanner quitScanner = null;
    private Scanner scanner;
    private QuitScanner(){
        scanner = new Scanner(System.in);
    }

    public static QuitScanner getInstance(){
        if(quitScanner == null){
            quitScanner = new QuitScanner();
        }
        return quitScanner;
    }

    public String nextLine(){
        String out = scanner.nextLine();
        if(out.equals(":q!")) System.exit(0);
        return out;
    }
}
