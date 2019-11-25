package com.example;

import com.example.methods.CopyALL;
import com.example.methods.CopyByID;

import java.sql.SQLException;
import java.util.Scanner;

class InterfaceApp {

    static void main() throws  SQLException {

        CopyALL copyALL = new CopyALL();
        CopyByID copyByID = new CopyByID();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Первый и последний пункт:)");
        System.out.println("1.Напишите ALL чтобы скопировать всю таблицу," +
                "а если хотите скопировать по айди,напишите цифру.");

        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            copyByID.id(number);
            System.out.println("Данные успешно скопированы по ID:" + number);
            scanner.close();
        } else if (scanner.next().equals("ALL")) {
            copyALL.all();
            System.out.println("Данные успешно скопированы");

        }
        else {
            System.out.println("Ошибка,прочтите 1 пункт.");
            main();
        }
    }
}
