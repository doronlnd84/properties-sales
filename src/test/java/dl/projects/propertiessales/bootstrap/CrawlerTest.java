package dl.projects.propertiessales.bootstrap;

import dl.projects.propertiessales.repositories.PropertySaleRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CrawlerTest {

    //Logger logger = LoggerFactory.getLogger(CrawlerTest.class);
    @Mock
    PropertySaleRepository homeSaleRepository;

    @InjectMocks
     Crawler crawler;

    @Before
    void setUp() {
    }

    @Test
    void isLastCharPositive() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            crawler.isLastCharPositive("");
        });
        assertTrue( crawler.isLastCharPositive("001"));
        assertFalse( crawler.isLastCharPositive("000"));


    }

    @Test
    void isNumeric() {
        assertTrue( crawler.isNumeric("1"));
        assertFalse( crawler.isLastCharPositive("one"));
        System.out.println("FINISH IS NUMARIC!!!!!!!!!!!!!!!!!!!!!!1");
    }
//    @Test
//    @ParameterizedTest
//    @NullAndEmptySource
//    @ValueSource(strings = {"", "  "})
//    void isEmptyTest(String input) {
//        assertTrue(crawler.isEmpty(input));
//     //   logger.warn(()->"logging in  isEmptyTest");
//    }
}