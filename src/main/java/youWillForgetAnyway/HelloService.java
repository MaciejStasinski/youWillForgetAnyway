package youWillForgetAnyway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

class HelloService {
    static final String FALLBACK_NAME = "world";
    static final Language FALLBACK_LANGUAGE= new Language(1,"Hello","en");
    private final Logger logger = LoggerFactory.getLogger(HelloService.class);


    private LanguageRepository repository;

    HelloService(){ this(
            new LanguageRepository());
    }

    HelloService(LanguageRepository repository){
        this.repository=repository;
    }

    String prepareGreeting(String name,String language){
        Integer langId;
        try {
            langId = Optional.ofNullable(language).map(Integer::valueOf).orElse(FALLBACK_LANGUAGE.getId());
        } catch (NumberFormatException e){
            logger.warn("Non-numeric language id used: " + language);
            langId = FALLBACK_LANGUAGE.getId();
        }
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANGUAGE).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }




}
