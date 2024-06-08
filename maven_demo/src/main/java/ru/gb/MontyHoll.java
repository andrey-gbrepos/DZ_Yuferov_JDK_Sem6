package ru.gb;

import java.util.HashMap;
import java.util.Random;

public class MontyHoll {
    public static Integer ITERAT_COUNT = 100000;

    HashMap <Integer, Boolean> playStatistic = new HashMap<>();
    Random random = new Random();
    Integer prizDoor;
    
    /**
     * 1-й вариант: "Игрок меняет решение."  
     * @return
     */
    public HashMap <Integer, Boolean> firstMontyHollVariant (){
        for (int i = 0; i < ITERAT_COUNT; i++) {
            prizDoor = definedDoors()[2];
            if(definedDoors()[0] == definedDoors()[2]){ // При смене варианта игрок не угадает
                this.playStatistic.put(i, false);
            } else {
                this.playStatistic.put(i, true);
            }
        }
        return playStatistic;
    }
    /**
    * 2-й вариант "Игрок не меняет решение"
    * @return
    */
    public HashMap <Integer, Boolean> secondMontyHollVariant (){
        for (int i = 0; i < ITERAT_COUNT; i++) {
            prizDoor = definedDoors()[2];
            if(definedDoors()[0] == definedDoors()[2]){ // Игрок не меняет решение и выигрывает
                this.playStatistic.put(i, true);
            } else {
                this.playStatistic.put(i, false);
            }
        }
        return playStatistic;
    }

        public Integer[] definedDoors (){

            Integer[] doors = new Integer[3]; // 0 - выбор игрока, 1 - открытая ведущим, 2 - дверь с призом
            
            Integer prizDoor  = random.nextInt(3); // за этой дверью автмобиль;
            Integer pleerChoice = random.nextInt(3); // Выбор игрока;
            Integer openDoor = 0;
 
            switch (pleerChoice) { // открываемая дверь ведущим
                case 0:
                    if (prizDoor == pleerChoice) openDoor = random.nextBoolean()? 1:2;
                    else openDoor = (prizDoor == 1)? 2:1;
                    break;
                case 1:
                    if (prizDoor == pleerChoice) openDoor = random.nextBoolean()? 0:2;
                    else openDoor = (prizDoor == 2)? 0:2;
                    break;
                case 2:
                    if (prizDoor == pleerChoice) openDoor = random.nextBoolean()? 0:1;
                    else openDoor = (prizDoor == 0)? 1:0;
                    break;
            }
            doors[0] = pleerChoice;
            doors[1] = prizDoor;
            doors[2] = openDoor;
        
            return doors;
        }
    }

    




