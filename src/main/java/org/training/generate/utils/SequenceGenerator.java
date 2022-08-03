package org.training.generate.utils;

import org.training.generate.model.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SequenceGenerator {

    private static final Logger LOGGER = LogManager.getLogger(SequenceGenerator.class.getName());

    private static final int NUMBER_OF_SEQUENCES = 5;
    private static final int NUMBER_OF_RANDOM_NUMBERS_FROM_EACH_ARRAY = 6;
    private static final int TIME_INTERVAL_FOR_EACH_ARRAY_GENERATION = 10000;
    private static final String REGEX_SQUARE_BRACKETS = "[\\[\\]<>]";

    private SequenceGenerator() {
    }

    public static List<int[]> generate5SequencesOfRandomPrimeArray(int length) {
        return IntStream.range(0, NUMBER_OF_SEQUENCES)
                .mapToObj(arr -> generateRandomPrimeArray(length))
                .collect(Collectors.toList());
    }

    public static Map<Integer, Set<Integer>> createGenerateVariantOfSequence(List<int[]> sequences) {
        return IntStream
                .range(1, NUMBER_OF_SEQUENCES + 1)
                .boxed()
                .collect(Collectors.toMap(Function.identity(),
                        i -> generateRandomSetFromArray(sequences.get(i - 1))));
    }

    public static void generateSequenceEvery10Sec(List<int[]> sequences, Data data) throws InterruptedException {

        StringBuilder sequenceViewBuilder = new StringBuilder();

        LOGGER.info("Given 5 sequences of prime numbers, 6 random numbers from\n" +
                "all arrays which generated every 10 seconds after pressing the button \"AUTO\":");

        for (int i = 0; i < NUMBER_OF_SEQUENCES; i++) {
            Thread.sleep(TIME_INTERVAL_FOR_EACH_ARRAY_GENERATION);

            List<Integer> sequenceAuto = createAutoVariantOfSequence(sequences);
            data.setAutoSequences(sequenceAuto);

            sequenceViewBuilder.append(data.getAutoSequences()).append("\n");

            String[] arrayFromSequenceView = sequenceViewBuilder.toString()
                    .replaceAll(REGEX_SQUARE_BRACKETS, " ")
                    .split(" \n");

            Map<Integer, String> sequenceViewMap = convertArrayOfSequenceViewToMap(arrayFromSequenceView);
            data.setAutoSequenceView(sequenceViewMap);

            LOGGER.info(data.getAutoSequenceView());
        }
    }

    public static Map<Integer, String> convertRandomListToMap(List<int[]> sequences) {
        String[] stringArrSequence = Arrays.deepToString(sequences.toArray())
                .replaceAll(REGEX_SQUARE_BRACKETS, " ").split(" ,");

        return convertArrayOfSequenceViewToMap(stringArrSequence);
    }

    private static List<Integer> createAutoVariantOfSequence(List<int[]> sequences) {
        int length = sequences.get(0).length;

        return IntStream
                .range(0, NUMBER_OF_RANDOM_NUMBERS_FROM_EACH_ARRAY)
                .mapToObj(x -> sequences.get(new Random().nextInt(NUMBER_OF_SEQUENCES))
                        [new Random().nextInt(length)])
                .collect(Collectors.toList());
    }

    private static Map<Integer, String> convertArrayOfSequenceViewToMap(String[] arrayOfSequenceView) {
        return IntStream
                .range(1, arrayOfSequenceView.length + 1)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> arrayOfSequenceView[i - 1]));
    }

    private static Set<Integer> generateRandomSetFromArray(int[] arr) {
        return Arrays.stream(arr)
                .mapToObj(x -> arr[new Random().nextInt(arr.length)])
                .distinct()
                .limit(NUMBER_OF_RANDOM_NUMBERS_FROM_EACH_ARRAY)
                .collect(Collectors.toSet());
    }

    private static int[] generateRandomPrimeArray(int length) {
        return IntStream
                .generate(() -> new Random().nextInt(100))
                .filter(SequenceGenerator::isPrime)
                .limit(length)
                .toArray();
    }

    private static boolean isPrime(int number) {
        return IntStream
                .rangeClosed(2, (int) (Math.sqrt(number)))
                .allMatch(i -> number % i != 0);
    }
}


