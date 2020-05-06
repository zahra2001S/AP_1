package hibernate;

import Log.Header;
import Log.Log;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Provider {
    private static final Provider PROVIDER = new Provider();
    private final SessionFactory sessionFactory = buildSessionFactory();
    private Session session;
    private boolean hasTransaction = false;

    private SessionFactory buildSessionFactory() {
        PrintStream err = System.err;
        try {
            PrintStream nullOut = new PrintStream(new File("./Log/Provider log.txt"));
            System.setErr(nullOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        System.setErr(err);
        return sessionFactory;
    }

    private Provider() {
    }

    public static Provider getInstance() {
        return PROVIDER;
    }

    public void open() {
        session = sessionFactory.openSession();
    }

    public void close() {
        if (session.isOpen())
            session.close();
    }

    public void beginTransaction() {
        if (!hasTransaction) {
            session.beginTransaction();
            hasTransaction = true;
        }
    }

    public void commit() {
        if (hasTransaction) {
            session.getTransaction().commit();
            hasTransaction = false;
        }
    }

    public void saveOrUpdate(Object o) {
        session.saveOrUpdate(o.getClass().getName(), o);
    }

    public void delete(Object o) {
        session.delete(o.getClass().getName(), o);
    }

    public Criteria createCriteria(Class c) {

        return session.createCriteria(c);
    }

    public Object fetch(Class c, java.io.Serializable id) {
        Object o = session.get(c.getName(), id);
        if (o == null)
            return null;
        ((Models) o).load();
        return o;
    }

    public List fetchAll(Class c) {
        Criteria criteria = session.createCriteria(c.getName());
        List list = criteria.list();
        for (Object o : list) {
            ((Models) o).load();
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public void saveOrUpdateList(List listId, List list) {
        listId.subList(0, listId.size()).clear();
        for (Object element : list) {
            ((Models) element).save();
            listId.add(((Models) element).getId());
        }

    }

    public void deleteList(List list) {
        for (Object o : list) ((Models) o).remove();
    }


    public List fetchList(Class target, List listId) {
        List objectList = new ArrayList();
        for (Object s : listId) {
            Models model = (Models) fetch(target, (java.io.Serializable) s);
            model.load();
            objectList.add(model);
        }
        return objectList;
    }

    public List fetchListFromFiled(Class className, String filedName, String filedValue){
        Provider provider = Provider.getInstance();
        Criteria criteriaHeader = provider.createCriteria(className);
        List<Header> headers = criteriaHeader.add(Restrictions.eq(filedName,filedValue)).list();
        return headers;
    }

    public List fetchListForLog(Class className, String filedName, Header filedValue){
        Provider provider = Provider.getInstance();
        Criteria criteriaLogger = provider.createCriteria(className);
        List logs = criteriaLogger.add(Restrictions.eq(filedName,filedValue)).list();
        return logs;
    }

    public List fetchListWithRestriction(Class className, String filedName, Header filedValue){
        Provider provider = Provider.getInstance();
        Criteria criteria = provider.createCriteria(className);
        return criteria.add(Restrictions.eq(filedName,filedValue)).list();
    }
}
