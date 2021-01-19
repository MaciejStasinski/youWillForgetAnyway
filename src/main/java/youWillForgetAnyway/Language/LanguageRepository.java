package youWillForgetAnyway.Language;

import youWillForgetAnyway.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class LanguageRepository {

    List<Language> findAll(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from Language ",Language.class).list();

        transaction.commit();
        session.close();
        return result;
    }

    public Optional<Language> findById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = Optional.ofNullable(session.get(Language.class,id));

        transaction.commit();
        session.close();
        return result;
    }
}
