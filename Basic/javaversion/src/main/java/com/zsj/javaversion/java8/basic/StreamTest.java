package com.zsj.javaversion.java8.basic;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zsj
 * @date 2019-04-19  11:57
 */
public class StreamTest {

    public static <LocalDateTime> void main(String[] args) {
        String[] strArr = new String[]{"a", "b", "c","a"};
        List<String> list = Arrays.asList(strArr);
        Stream<String> stream = Arrays.stream(strArr);

        //stream.filter((e) -> e.equals("a")).collect(Collectors.toList()).forEach(System.out::println);

        //stream.map(s -> s+"_s").forEach(System.out::println);

        //Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 5, 6)).forEach(System.out::println);

        //stream.distinct().forEach(System.out::println);

        //stream.limit(1).forEach(System.out::println);

        //Stream.of("a", "a", "b", "c").skip(2).forEach(System.out::println);


        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        Stream.of(3, 2, 1).sorted(Integer::compareTo).forEach(System.out::println);

        Stream<Integer> stream1 = Stream.of(3, 2, 1);
        boolean match = stream1.allMatch(integer -> integer > 5);
        System.out.println(match);


        //创建Optional


        Clock clock = Clock.system(ZoneId.of("Asia/Shanghai"));
        Instant instant = clock.instant();
        System.out.println(instant.toString());
        Clock clock1 = Clock.systemDefaultZone();
        long millis = clock1.millis();
        System.out.println(millis);

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString());

        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        System.out.println(now.toString());

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime.toString());


    }
}
