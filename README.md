# Сборщик проектов "Maven"

## 1. Установка на ОС Windows

1. С ресурса https://maven.apache.org/ из раздела download скачать архив с дистрибутивом Maven (apache-maven-3.9.7-bin.zip)
2. Распаковать в какой-нибудь стандартный каталог например  --> D:\Program Files\apache-maven-3.5.3\bin
3. Прописать в переменные среды: 
- для пользователя --> Переменная: M2 Значение: D:\Program Files\apache-maven-3.9.7 и Переменная: M2_HOME Значение: D:\Program Files\apache-maven-3.9.7
- в системную переменную PATH добавить D:\Program Files\apache-maven-3.5.3\bin

## 2. Проверка установки и формированние проекта

Для контроля установки в терминале необходимо выполнить команду:

```
$ mvn -v
Apache Maven 3.9.7 (8b094c9513efc1b9ce2d952b3b9c8eaedaf8cbf0)
Maven home: D:\Program Files\apache-maven-3.9.7
Java version: 20, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-20
Default locale: ru_RU, platform encoding: UTF-8
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

Для определения архитипа проекта служит команда - **_mvn archetype: generate_**
```
$ mvn archetype:generate
[INFO] Scanning for projects...
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/plugin....
```
После этого начинается формирование проекта в интерактивном режиме.

Выбор архитипа проекта - соглашаемся по умолчанию это - 2154: remote -> org.apache.maven.archetypes:maven-archetype-quickstart (An archetype which contains a sample Maven project.)
```
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 2154:
```

Выбор версии архитипа
```
Choose org.apache.maven.archetypes:maven-archetype-quickstart version:
1: 1.0-alpha-1
2: 1.0-alpha-2
3: 1.0-alpha-3
4: 1.0-alpha-4
5: 1.0
6: 1.1
7: 1.3
8: 1.4
Choose a number: 8:
```
По умолчанию самая свежая - 1.4

Определяем groupId:
```
Define value for property 'groupId': ru.gb
```
Определяем название проекта
```
Define value for property 'artifactId': maven_demo
```
Оставляем по умолчанию:
```
Define value for property 'version' 1.0-SNAPSHOT: :
```
Имя пакета оставляем по умолчанию:
```
Define value for property 'package' ru.gb: :
```
Подтверждаем конфигурационные данные:
```
Confirm properties configuration:
groupId: ru.gb
artifactId: maven_demo
version: 1.0-SNAPSHOT
package: ru.gb
 Y: :
```

```
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: maven-archetype-quickstart:1.4
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: ru.gb
[INFO] Parameter: artifactId, Value: maven_demo
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Parameter: package, Value: ru.gb
[INFO] Parameter: packageInPathFormat, Value: ru/gb
[INFO] Parameter: package, Value: ru.gb
[INFO] Parameter: groupId, Value: ru.gb
[INFO] Parameter: artifactId, Value: maven_demo
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Project created from Archetype in dir: E:\Obrazovanie\Programming\REPOZITORY\JDK_DZ6\maven_demo
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  19:30 min
[INFO] Finished at: 2024-06-08T11:40:58+03:00
[INFO] ------------------------------------------------------------------------
```

## 3. Подключение зависимостей и сборка проекта

На сайте https://mvnrepository.com/ находим требуемую зависимость:
например:
```
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```
и вставляем этот код в файл pom.xml в раздел <depenences>.


Сборка проекта осуществляется при помощи команды: mvn package.
```
$ mvn package
[INFO] Scanning for projects...
[INFO]
[INFO] --------------------------< ru.gb:maven_demo >--------------------------
[INFO] Building maven_demo 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
Downloading from central: https......
...
...
[INFO] Building jar: E:\Obrazovanie\Programming\REPOZITORY\JDK_DZ6\maven_demo\target\maven_demo-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  14.741 s
[INFO] Finished at: 2024-06-08T12:10:07+03:00
[INFO] ------------------------------------------------------------------------
```

## 4. Добавление кода программы для демонстрации "парадокса Монти-Холла"
```
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
```

```
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
```

Повторная сборка проекта:
```
$ mvn package
[INFO] Scanning for projects...
...
...
[INFO] Building jar: E:\Obrazovanie\Programming\REPOZITORY\JDK_DZ6\maven_demo\target\maven_demo-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.488 s
[INFO] Finished at: 2024-06-08T12:47:44+03:00
[INFO] ------------------------------------------------------------------------
```