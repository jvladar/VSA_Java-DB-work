package clientTest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JedalenClientTest {

    static private Server jettyServer;

    static private int ponPocet = 0;
    static private int utPocet = 0;

    public JedalenClientTest() {
    }

    @BeforeClass
    static public void setUp() {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/RestUnitTest/resources");

        jettyServer = new Server(9999);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "rest");

        try {
            jettyServer.start();
//            System.out.println("Jetty started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    static public void tearDown() {
        try {
//            System.out.println("Jetty stopping...");
            jettyServer.stop();
//       jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET pocet pondelok => 1
    //                       0 : asi zabudol vlozit gulas 
    @Test
    public void UT010_pon_pocet() {
        JedalenClient client = new JedalenClient();
        Integer r = null;
        try {
            r = client.getPocet(Integer.class, "pondelok");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r);
        ponPocet = r;
    }

    @Test
    public void UT011_pon_pocet() {
        assertEquals(1, ponPocet);
    }

    // GET pocet utorok => 0
    @Test
    public void UT012_ut_pocet() {
        JedalenClient client = new JedalenClient();
        Integer r = null;
        try {
            r = client.getPocet(Integer.class, "utorok");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r);
        utPocet = r;
        assertEquals(0, utPocet);
    }

    // GET pocet pre neexistujuci den => HTTP 40*, HTTP 20* , null alebo 0
    @Test
    public void UT020_XXX_pocet_1b() {
        JedalenClient client = new JedalenClient();
        Integer r = null;
        try {
            r = client.getPocet(Integer.class, "XXX");
        } catch (Exception e) {
            //neocakavana chyba");
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB:" + e.getMessage());
            }
        }
        // ak nieco vrati, musi to byt 0.
        if (r != null) {
            assertEquals(0, r.intValue());
        }
    }

    // GET ponuka pondelok
    private static Ponuka pon;

    @Test
    public void UT030_pon_menu() {
        JedalenClient client = new JedalenClient();
        pon = null;
        try {
            pon = client.getMenu(Ponuka.class, "pondelok");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pon);

    }

    @Test
    public void UT031_pon_menu() {
        assertNotNull(pon);
        assertNotNull(pon.getJedlo());
        assertEquals("zly pocet: " + pon.getJedlo().size(), 1, pon.getJedlo().size());
    }

    @Test
    public void UT032_pon_menu() {
        assertNotNull(pon);
        assertNotNull(pon.getJedlo());
        assertNotNull(pon.getJedlo().get(0));
        assertTrue("zle jedlo: " + pon.getJedlo().get(0).getNazov(), pon.getJedlo().get(0).getNazov().contains("gulas"));
    }

    @Test
    public void UT033_pon_menu() {
        assertNotNull(pon);
        assertNotNull(pon.getJedlo());
        assertNotNull(pon.getJedlo().get(0));
        assertEquals("zla cena: " + pon.getJedlo().get(0).getCena(), 3.5, pon.getJedlo().get(0).getCena(), 0.001);
    }

    // GET ponuka utorok
    private static Ponuka ut;

    @Test
    public void UT040_ut_menu() {
        JedalenClient client = new JedalenClient();
        ut = null;
        try {
            ut = client.getMenu(Ponuka.class, "utorok");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(ut);
    }

    @Test
    public void UT041_ut_menu() {
        assertNotNull(ut);
        assertEquals("zly den: " + ut.getDen(), "utorok", ut.getDen());
    }

    // GET 1. jedlo v pondelok => gulas 3.5
    private static Jedlo pon1 = null;

    @Test
    public void UT050_pon_jedlo1() {

        JedalenClient client = new JedalenClient();
        pon1 = null;
        try {
            pon1 = client.getJedlo(Jedlo.class, "pondelok", "1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pon1);
    }

    @Test
    public void UT051_pon_jedlo1() {
        assertNotNull(pon1);
        assertTrue("zly nazov: " + pon1.getNazov(), pon1.getNazov().contains("gulas"));

    }

    @Test
    public void UT052_pon_jedlo1() {
        assertNotNull(pon1);
        assertEquals("zla cena: " + pon1.getCena(), 3.5, pon1.getCena(), 0.001);

    }

    // GET cislo jedla mimo rozsahu
    @Test
    public void UT060_pon_jedlo99() {
        JedalenClient client = new JedalenClient();
        Jedlo j = null;
        try {
            j = client.getJedlo(Jedlo.class, "pondelok", "99");
        } catch (Exception e) {
            //neocakavana chyba");
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB:" + e.getMessage());
            }
        }
        assertNull(j);
    }

    // POST pondelok palacinky 3.1
    @Test
    public void UT070_pon_post() {
        String r = null;
        JedalenClient client = new JedalenClient();
        Jedlo pj1 = new Jedlo("palacinky", 3.1);
        try {
            r = client.postJedlo(pj1, "pondelok");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertNotNull(r);
        assertEquals("2", r);
    }

    private static Jedlo pon2 = null;

    @Test
    public void UT080_pon_jedlo2() {

        JedalenClient client = new JedalenClient();
        pon2 = null;
        try {
            pon2 = client.getJedlo(Jedlo.class, "pondelok", "2");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pon2);
        assertTrue("zly nazov: " + pon2.getNazov(), pon2.getNazov().contains("palacinky"));
        assertEquals("zla cena: " + pon2.getCena(), 3.1, pon2.getCena(), 0.001);
    }

    // POST palacinky znovu => vrati 0 a neurobi nic (pocet jedal sa nezmeni)
    @Test
    public void UT090_pon_post_palacinky_znovu() {
        String r = null;
        JedalenClient client = new JedalenClient();
        Jedlo h = new Jedlo("palacinky", 4.9);
        try {
            r = client.postJedlo(h, "pondelok");
            ponPocet = client.getPocet(Integer.class, "pondelok");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertEquals("0", r);
        assertEquals(2, ponPocet);
    }

    // POST utorok Halusky 4.1
    private static Jedlo ut1;

    @Test
    public void UT100_ut_post_halusky() {
        String r = null;
        JedalenClient client = new JedalenClient();
        Jedlo pj1 = new Jedlo("halusky", 4.1);
        try {
            r = client.postJedlo(pj1, "utorok");
            utPocet = client.getPocet(Integer.class, "utorok");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertEquals("1", r);
        assertEquals(1, utPocet);
    }

    @Test
    public void UT110_ut_jedlo1() {

        JedalenClient client = new JedalenClient();
        ut1 = null;
        try {
            ut1 = client.getJedlo(Jedlo.class, "utorok", "1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(ut1);
    }

    @Test
    public void UT111_ut_jedlo1() {
        assertNotNull(ut1);
        assertTrue("zly nazov: " + ut1.getNazov(), ut1.getNazov().contains("halusky"));
        assertEquals("zla cena: " + ut1.getCena(), 4.1, ut1.getCena(), 0.001);

    }

    // POST utorok druhe jedlo - palacinky
    @Test
    public void UT120_ut_post_palacinky() {
        String r = null;
        JedalenClient client = new JedalenClient();
        Jedlo h = new Jedlo("palacinky", 4.9);
        try {
            r = client.postJedlo(h, "utorok");
            utPocet = client.getPocet(Integer.class, "utorok");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertEquals("2", r);
        assertEquals(2, utPocet);
    }


    @Test
    public void UT201_find_gulas() {
        String r = "";
        JedalenClient client = new JedalenClient();
        try {
            r = client.find("gulas");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertTrue("gulas: "+ r, r.contains("pondelok"));
    }

    @Test
    public void UT202_find_Palacinky() {
        String r = "";
        JedalenClient client = new JedalenClient();
        try {
            r = client.find("palacinky");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertTrue("palacinky chybaju v pondelok: " + r, r.contains("pondelok"));
        assertTrue("palacinky chybaju v utorok: " + r, r.contains("utorok"));
    }

    @Test
    public void UT203_find_Nic() {
        String r = "";
        JedalenClient client = new JedalenClient();
        try {
            r = client.find("Nic");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertTrue("NEMAME chyba: "+ r, r.contains("NEMAME"));
    }
    
    
    // DELETE - priprava 
    @Test
    public void UT300_str_post() {
        Integer r = null;
        JedalenClient client = new JedalenClient();
        Jedlo uj1 = new Jedlo("Rezen", 4.1);
        Jedlo uj2 = new Jedlo("File", 3.5);
        try {
            client.postJedlo(uj1, "streda");
            client.postJedlo(uj2, "streda");
            r = client.getPocet(Integer.class, "streda");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertNotNull(r);
        assertEquals(2, r.intValue());
    }

    // DELETE 1. jedlo a kontrola poctu jedal
    @Test
    public void UT310_str_delete() {
        Integer r = null;
        JedalenClient client = new JedalenClient();
        try {
            client.delJedlo("streda", "1");
            r = client.getPocet(Integer.class, "streda");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());

        }
        assertNotNull(r);
        assertEquals(1, r.intValue());
    }

    // kontrola posunutia poradoveho cisla
    private static Jedlo str1 = null;
    @Test
    public void UT320_str_jedlo1() {

        JedalenClient client = new JedalenClient();
        str1 = null;
        try {
            str1 = client.getJedlo(Jedlo.class, "streda", "1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(str1);
        assertTrue("zly nazov: " + str1.getNazov(), str1.getNazov().contains("File"));
        assertEquals("zla cena: " + str1.getCena(), 3.5, str1.getCena(), 0.001);
    }
    

}