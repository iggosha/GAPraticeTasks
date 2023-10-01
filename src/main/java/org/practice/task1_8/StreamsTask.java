package org.practice.task1_8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsTask {

    //Задача - найти разработчиков с уникальными языками программирования, используя Stream API.
    //Для данного примера ожидаемый результат [Наташа, Элла].
    public static void main(String[] args) {
        StreamsTask task = new StreamsTask();

        List<String> duplicatedLanguages = task.getDevs()
                .flatMap(dev -> dev.getLanguages().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(langAmount -> langAmount.getValue() < 2)
                .map(Map.Entry::getKey)
                .toList();

        List<Developer> developersWithDuplicatedLanguages = task.getDevs()
                .filter(dev -> dev.getLanguages().stream().anyMatch(duplicatedLanguages::contains))
                .toList();
        developersWithDuplicatedLanguages.forEach(dev -> System.out.println(dev.getName()));
    }

    private Stream<Developer> getDevs() {
        Developer dev1 = new Developer("Наташа", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Эрнест", Arrays.asList("Java", "Python"));
        Developer dev3 = new Developer("Элла", Arrays.asList("С#", "Python", "JavaScript"));
        Developer dev4 = new Developer("Георгий", Arrays.asList("Go", "C#", "JavaScript"));
        Developer dev5 = new Developer("Марат", Arrays.asList("Python", "C#", "JavaScript"));
        return Stream.of(dev1, dev2, dev3, dev4, dev5);
    }

}
