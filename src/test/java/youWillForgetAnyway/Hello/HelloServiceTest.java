package youWillForgetAnyway.Hello;

import org.junit.Test;
import youWillForgetAnyway.Language.Language;
import youWillForgetAnyway.Language.LanguageRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private static final String FALLBACK_ID_WELCOME = "Hola";

    @Test
    public void test_null_name_prepareGreeting_returnsGreetingWithFallbackName() throws Exception {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        //when
        var result=SUT.prepareGreeting(null,-1);
        //then
        assertEquals( WELCOME +" "+ HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() throws Exception {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        var name = "test";
        //when
        var result = SUT.prepareGreeting(name,-1);
        //then
        assertEquals(WELCOME+" " + name +"!",result);
    }
//-------------------------------------
    @Test
    public void test_prepareGreeting_nonExistingLanguage_returnsGreetingWithFallbackLang() throws Exception {
        //given
        var mockRepository = new LanguageRepository(){
            @Override
            public Optional<Language> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);

        //when
        var result = SUT.prepareGreeting(null,-1);
        //then
        assertEquals(HelloService.FALLBACK_LANGUAGE.getWelcomeMsg()+" " + HelloService.FALLBACK_NAME +"!",result);
    }
//---------------------------------------
    @Test
    public void test_prepareGreeting_null_Language_returnsGreetingWithFallbackIdLanguage() throws Exception {
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);

        //when
        var result = SUT.prepareGreeting(null,null);
        //then
        assertEquals(FALLBACK_ID_WELCOME+" " + HelloService.FALLBACK_NAME +"!",result);
    }

    private LanguageRepository fallbackLangIdRepository() {
        return new LanguageRepository() {
            @Override
            public Optional<Language> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANGUAGE.getId())) {
                    return Optional.of(new Language(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LanguageRepository alwaysReturningHelloRepository(){
        return new LanguageRepository() {
            @Override
            public Optional<Language> findById(Integer id) {
                return Optional.of(new Language(null, WELCOME, null));
            }
        };
    }
}
