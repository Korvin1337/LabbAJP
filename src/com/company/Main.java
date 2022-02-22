package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PrimeChecker implements Runnable {
    int start;
    int end;
    List<Integer> primes;

    public PrimeChecker(int start, int end, List<Integer> primes) {
        this.start = start;
        this.end = end;
        this.primes = primes;
    }

    public void run() {
        for(int n = start; n <= end; n++) {
            boolean prime = true;

            int i = 2;
            while (i <= n / 2) {
                if (n % i == 0) {
                    prime = false;
                    break;
                }
                i++;
            }
            if (prime && n > 1) {
                primes.add(n);
            }
        }
    }
        /*System.out.println("Number of primes: " + primes.size());*/
}

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // 1.0
        List<Person> employees = List.of(
                new Person("Rudrik", "M", 24000),
                new Person("Britt-Marie", "F", 32000),
                new Person("Oskar", "M", 45000),
                new Person("Felicia", "F", 44000),
                new Person("Jonas", "M", 27000),
                new Person("Kerstin", "F", 14000),
                new Person("Douglas", "M", 18000),
                new Person("Anna", "F", 22000),
                new Person("Berra", "M", 26000),
                new Person("Sara", "F", 28000)
        );

        // 1.1

        // Men average salary
        System.out.println(
                employees
                        .stream()
                        .filter(Person -> Person.getGender().equals("M"))
                        .collect(Collectors.averagingDouble(Person::getSalary))
        );

        // Female average salary
        System.out.println(
                employees
                        .stream()
                        .filter(Person -> Person.getGender().equals("F"))
                        .collect(Collectors.averagingDouble(Person::getSalary))
        );

        // 1.2 Highest salary
                List<Person> employeeStream = employees
                        .stream()
                        .sorted(Comparator.comparing(Person::getSalary).reversed())
                        /*.limit(1)*/
                        .toList();
        System.out.println(employeeStream);

        // 1.3 Lowest salary
        System.out.println(
                employees
                        .stream()
                        .sorted(Comparator.comparing(Person::getSalary))
                        .limit(1)
                        .toList()
        );

        // 2.0
        // kör CarFactory i mappen carfactory

        // 3.0
        List<String> wordList = List.of("Kex", "Choklad", "Chips", "Godis", "Aei", "Jan Guillou");
        ArrayList<String> matchingWords = new ArrayList<String>();

        Pattern vowels = Pattern.compile("[aeiouAEIOU]");

        for(int i = 0; i < wordList.size(); i++) {
            int count = 0;
            Matcher matcher = vowels.matcher(wordList.get(i));
            while(matcher.find()){
                count++;
            }
            if(count > 1) {
                matchingWords.add(wordList.get(i));
            }
        }

        System.out.println(matchingWords);



        // 4.0
        List<Integer> primes = Collections.synchronizedList(new ArrayList<Integer>());

        Thread thread1 = new Thread(new PrimeChecker(0, 125000, primes));
        Thread thread2 = new Thread(new PrimeChecker(125001, 250000, primes));
        Thread thread3 = new Thread(new PrimeChecker(250001, 375000, primes));
        Thread thread4 = new Thread(new PrimeChecker(375001, 500000, primes));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        /*System.out.println(primes);*/
        System.out.println("Number of primes: " + primes.size());
    }
}
