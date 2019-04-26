package pl.sii;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Test;

public class PopularWordsTest 
{
    private static final PopularWords testee = new PopularWords();

    @Test
    public void shouldReturnOneThounsendMostPopularWords()
    {
        //given
        List<String> wordsFrequencyListCreatedByAdamKilgarriff = getWordsFrequencyListCreatedByAdamKilgarriff();

        //when
        List<String> result = testee.findOneThousendMostPopularWords();

        //then
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 1000);
        assertEquals(result.size(), wordsFrequencyListCreatedByAdamKilgarriff.size());
        assertThat(result, is(wordsFrequencyListCreatedByAdamKilgarriff));
    }

    private List<String> getWordsFrequencyListCreatedByAdamKilgarriff() {
        throw new NotImplementedException("TODO implementation");
    }
}
