package pl.sii;

import org.apache.commons.lang3.NotImplementedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PopularWords {

    public static void main(String[] args) {
        PopularWords popularWords = new PopularWords();
        Map<String, Long> result = popularWords.findOneThousandMostPopularWords();
        result.entrySet().forEach(System.out::println);
    }

    public Map<String, Long> findOneThousandMostPopularWords() {
        Map<String, Long> wordsMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/3esl.txt"))){
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                String[] words = line.toLowerCase().split(" ");
                for (int i = 0; i < words.length; i++) {
                    if (wordsMap.get(words[i]) == null) {
                        wordsMap.put(words[i], 1L);
                    } else {
                        int newValue = Integer.valueOf(String.valueOf(wordsMap.get(words[i])));
                        newValue++;
                        wordsMap.put(words[i], Long.valueOf(newValue));
                    }
                }
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        }
        catch (IOException e) {
            System.out.println("reading from file error");
        }
        Map<String, Long> result = wordsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1000)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }
}

