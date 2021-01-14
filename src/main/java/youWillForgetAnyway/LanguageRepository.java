package youWillForgetAnyway;

import java.util.Optional;

public class LanguageRepository {

    Optional<Language> findById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Language.class,id);
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
}
