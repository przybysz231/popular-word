package pl.sii;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PopularWordsTest {
    private static final PopularWords testee = new PopularWords();

    @Test
    public void shouldReturnOneThousandMostPopularWords() {
        //given
        Map<String, Long> wordsFrequencyListCreatedByAdamKilgarriff = getWordsFrequencyListCreatedByAdamKilgarriff();

        //when
        Map<String, Long> result = testee.findOneThousandMostPopularWords();

        //then
        assertFalse(result.isEmpty());
        assertEquals(1000, result.size());
        compareWordListsFrequency(wordsFrequencyListCreatedByAdamKilgarriff, result);
    }

    private void compareWordListsFrequency(Map<String, Long> wordsFrequencyListCreatedByAdamKilgarriff, Map<String, Long> result) {
        long totalFrequencyByKilgarriff = wordsFrequencyListCreatedByAdamKilgarriff.values().stream().reduce(0L, Long::sum);
        long totalFrequencyInAResult = result.values().stream().reduce(0L, Long::sum);
        System.out.println("totalFrequencyByKilgarriff = " + totalFrequencyByKilgarriff);
        System.out.println("totalFrequencyInAResult = " + totalFrequencyInAResult);
        result.forEach((key, value) -> {
            BigDecimal valueUsagePercentage = calculatePercentage(value, totalFrequencyInAResult);
            if (wordsFrequencyListCreatedByAdamKilgarriff.containsKey(key)) {
                BigDecimal kilgarriffUsagePercentage = calculatePercentage(wordsFrequencyListCreatedByAdamKilgarriff.get(key), totalFrequencyByKilgarriff);
                BigDecimal diff = kilgarriffUsagePercentage.subtract(valueUsagePercentage);
                System.out.println(key + "," + valueUsagePercentage + "%," + kilgarriffUsagePercentage + "%," + (new BigDecimal(0.5).compareTo(diff.abs()) > 0) + " " + diff);
            } else {
                System.out.println(key + "," + valueUsagePercentage+ "%," + " doesn't exist! cannot compare");
            }
        });

    }

    private BigDecimal calculatePercentage(double obtained, double total) {
        return new BigDecimal(obtained * 100 / total).setScale(4, RoundingMode.HALF_UP);
    }

    private Map<String, Long> getWordsFrequencyListCreatedByAdamKilgarriff() {
        Map<String, Long> wordsFreqMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/all.num"))) {
            reader.readLine();
            String line1 = null;
            while ((line1 = reader.readLine()) != null) {
                String[] freq = line1.trim().split(" ");
                wordsFreqMap.put(freq[1], Long.valueOf(freq[0]));
            }
        } catch (IOException e) {
            System.out.println("reading from file error");
        }
        return wordsFreqMap;
    }
}
