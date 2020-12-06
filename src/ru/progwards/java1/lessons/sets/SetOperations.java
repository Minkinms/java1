package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static void main(String[] args) {
        Set<Integer> set1 = Set.of(0, 1, 3, 5, 7);
        Set<Integer> set2 = Set.of(0, 2, 4, 6, 8, 7, 3);

        System.out.println("Объединение множеств " + union(set1, set2));
        System.out.println("Пересечение множеств " + intersection(set1, set2));
        System.out.println("Разница множеств " + difference(set1, set2));
        System.out.println("Симметрическая разность " + symDifference(set1, set2));

    }

//    1.1 - объединение множеств
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> setOut = new HashSet<>(set1);
        setOut.addAll(set2);
        return setOut;
    }

//    1.2 - пересечение множеств
    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> setOut = new HashSet<>(set1);
        setOut.retainAll(set2);
        return setOut;
    }

//    1.3 - разница множеств. Все элементы первого множества, не входящие во второе
    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> setOut = new HashSet<>(set1);
//        Set<Integer> setIntersection = new HashSet<>(intersection(set1, set2));
        setOut.removeAll(intersection(set1, set2));
        return setOut;
    }

//    1.4 - симметрическая разница. Все элементы исходных множеств, не принадлежащие одновременно обоим множествам
    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> setOut = new HashSet<>(set1);
        setOut.addAll(set2);
        setOut.removeAll(intersection(set1, set2));
/*        Set<Integer> setOut = new HashSet<>();
        Set<Integer> setdiff1 = new HashSet<>(difference(set1, set2));
        Set<Integer> setdiff2 = new HashSet<>(difference(set2, set1));
        setOut.addAll(setdiff1);
        setOut.addAll(setdiff2);*/
        return setOut;
    }
}
