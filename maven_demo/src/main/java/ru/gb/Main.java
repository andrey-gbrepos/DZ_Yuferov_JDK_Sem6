// В качестве задачи предлагаю вам реализовать код для демонстрации парадокса 
// Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности парадокса
// (запустить игру в цикле на 1000 и вывести итоговый счет).
// Необходимо:
// Создать свой Java Maven или Gradle проект;
// Подключить зависимость lombok.
// Можно подумать о подключении какой-нибудь математической библиотеки 
// для работы со статистикой
// Самостоятельно реализовать прикладную задачу;
// Сохранить результат в HashMap<шаг теста, результат>
// Вывести на экран статистику по победам и поражениям
package ru.gb;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static  void  main(String[]args){
        MontyHoll montyHoll = new MontyHoll();
        int countChangeChoice = Collections.frequency(new ArrayList<Boolean>
                                    (montyHoll.firstMontyHollVariant ().values()), true);
        int countWithoutChangeChoice = Collections.frequency(new ArrayList<Boolean>
                                    (montyHoll.secondMontyHollVariant().values()), true);
        
        System.out.println("Результат розыгрыша игры 'Парадокс Монти-Холла': " + "\n");
        System.out.println("При количестве розыгрышей равном " + montyHoll.ITERAT_COUNT + "\n"
                            + " в случае смены первоначального выбора количество выигрышей равно "
                            + countChangeChoice);

        
        System.out.println("При количестве розыгрышей равном " + montyHoll.ITERAT_COUNT + "\n"
                            + " в случае сохранения первоначального выбора количество выигрышей равно "
                            + countWithoutChangeChoice);
    }
}
                
                
