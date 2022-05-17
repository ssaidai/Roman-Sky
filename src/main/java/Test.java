import com.scraper.Dynasty;
import com.scraper.Scraper;

public class Test {
    public static void main(String[] args) {
        Scraper scraper = new Scraper();

        scraper.fetchEmperors(Dynasty.IMPERATORI_ADOTTIVI);
    }
}
