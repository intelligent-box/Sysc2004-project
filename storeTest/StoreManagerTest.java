package storeTest;/**
 * @author Michael Silveira
 * @studentID 101145789
 * @date March 21th 2021
 * @milestone 3
 */

import org.junit.jupiter.api.*;
import store.StoreManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests StoreManager.java
 */
public class StoreManagerTest {
    StoreManager man;

    /**
     * this method is called before each test and reinitializes man with fresh data.
     */
    @BeforeEach
    void init(){
        man = new StoreManager();
        man.newView();
        man.newView();
    }

    /**
     * this method tests newView()
     */
    @Test
    void newViewTest(){
        assertEquals("StoreView Names\n0\n1\n", man.displayStoreViews());
        man.newView();
        assertEquals("StoreView Names\n0\n1\n2\n", man.displayStoreViews());
    }

    /**
     * this method tests displayStoreViews()
     */
    @Test
    void displayStoreViewsTest(){
        assertEquals("StoreView Names\n0\n1\n", man.displayStoreViews());
    }

    /**
     * this method tests removeStoreView()
     */
    @Test
    void removeStoreViewTest(){
        assertNotNull(man.getCart("0"));
        assertNotNull(man.getCart("1"));
        assertNull(man.getCart("2"));

        man.removeStoreView("0");

        assertNull(man.getCart("0"));
        assertNotNull(man.getCart("1"));
        assertNull(man.getCart("2"));
    }

    /**
     * this method tests getCart()
     */
    @Test
    void getCartTest(){
        assertNotNull(man.getCart("0"));
        assertNotNull(man.getCart("1"));
        assertNull(man.getCart("2"));

    }

}
