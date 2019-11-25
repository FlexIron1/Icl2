package com.example;

import com.example.methods.CopyFromALL;
import com.example.methods.CopyFromID;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

class InterfaceApp {

    static void main() throws IOException, SQLException {

        CopyFromALL copyFromALL = new CopyFromALL();
        CopyFromID copyFromID = new CopyFromID();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Первый и последний пункт:)");
        System.out.println("1.Напишите ALL чтобы скопировать всю таблицу,а если хотите скопировать по айди,напишите цифру.");

        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            copyFromID.id(number);
            System.out.println("Данные успешно скопированы по ID:" + number);
            scanner.close();
        } else if (scanner.next().equals("ALL")) {
            copyFromALL.all();
            System.out.println("Данные успешно скопированы");
        } else {
            System.out.println("Ошибка,прочтите 1 пункт.");
            main();
        }


    }
}
