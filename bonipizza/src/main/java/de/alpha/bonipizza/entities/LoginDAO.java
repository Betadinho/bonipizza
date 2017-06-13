        package de.alpha.bonipizza.entities;

        import org.hibernate.cfg.Configuration;

        import org.hibernate.HibernateException;
        import org.hibernate.Session;
        import org.hibernate.Transaction;
        import org.hibernate.SessionFactory;

        import javax.crypto.SecretKey;
        import javax.crypto.SecretKeyFactory;
        import javax.crypto.spec.PBEKeySpec;
        import java.security.spec.InvalidKeySpecException;
        import java.util.*;

        import java.security.NoSuchAlgorithmException;
        import java.security.SecureRandom;

        /**
         * Created by prechtla on 01.06.17.
         */
        public class LoginDAO {
            private static SessionFactory factory;

            public static SessionFactory getSessionFactory() {
                if (factory == null){
                    factory = new Configuration().configure().buildSessionFactory();
                    return factory;
                } else
                    return factory;
            }

            public static boolean userLoginCheck(String usrNameI, String usrPasswordI) {
                try (Session session = LoginDAO.getSessionFactory().openSession()) {
                    User user = (User)session.createQuery("from User where usrName = :param")
                            .setParameter("param", usrNameI)
                            .uniqueResult();
                    String bytes = hashPass(usrPasswordI, user.getUsrSeasoning(), 1, 256);
                    session.setProperty("user", user);
                    return (user.getUsrPassword().equals(bytes));
                } catch (HibernateException he) {
                    he.printStackTrace();
                } return false;
            } // ------- End userLoginCheck

            public static boolean registerUser(String usrNameI, String usrPasswordI) {
                try(Session session = LoginDAO.getSessionFactory().openSession()) {
                    Transaction tx = session.beginTransaction();
                    User user = (User)session.createQuery("from User where usrName = :param")
                            .setParameter("param", usrNameI)
                            .uniqueResult();
                    if (user == null) {
                        byte[] seasoning = generateSeasoning(16); //Eine für hashPass passender variable
                        User newDude = new User(0, usrNameI, hashPass(usrPasswordI, seasoning, 1, 256), seasoning);
                        session.save(newDude);
                        tx.commit();
                        return true;
                    } else return false;
                } catch (HibernateException he) {
                    he.printStackTrace();
                } return false;
            } // -------- End registerUser

            public static byte[] generateSeasoning(int lentgh) {
                final Random r = new SecureRandom();
                byte[] seasoning = new byte[lentgh];
                r.nextBytes(seasoning);

                return seasoning;
            }

            public static String hashPass(final String password, final byte[] seasoning, final int iterations, final int keyLength ) {

                try {
                    SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
                    PBEKeySpec spec = new PBEKeySpec( password.toCharArray(), seasoning, iterations, keyLength );
                    SecretKey key = skf.generateSecret( spec );

                    return Arrays.toString(key.getEncoded());


                } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
                    throw new RuntimeException( e );
                }
            }


        }
