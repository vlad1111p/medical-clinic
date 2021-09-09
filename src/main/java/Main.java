import dao.HibernateUtil;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        System.out.println("sdasd");


        try {


            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("sdsds");
            session.beginTransaction();


            session.close();
        } catch (Exception e) {
        e.printStackTrace();
    }

}
}
