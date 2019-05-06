package pl.sii;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

public class PopularWords 
{
    public static void main( String[] args )
    {
        PopularWords popularWords = new PopularWords();
        List<String> result = popularWords.findOneThousandMostPopularWords();
        result.stream().forEach(System.out::println);
    }

    public List<String> findOneThousandMostPopularWords() {
        throw new NotImplementedException("TODO implementation");
    }
}
