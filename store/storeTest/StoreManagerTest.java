package store.storeTest;
import org.junit.jupiter.api.*;
import store.StoreManager;
import store.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
public class StoreManagerTest {
    StoreManager man;

    @BeforeEach
    void init(){
        man = new StoreManager();
        man.newView();
        man.newView();
    }

    @Test
    void newViewTest(){
        assertEquals("StoreView Names\n0\n1\n", man.displayStoreViews());
        man.newView();
        assertEquals("StoreView Names\n0\n1\n2\n", man.displayStoreViews());
    }

    @Test
    void displayStoreViewsTest(){
        assertEquals("StoreView Names\n0\n1\n", man.displayStoreViews());
    }

    @Test
    void removeStoreView(){
        assertNotNull(man.getCart("0"));
        assertNotNull(man.getCart("1"));
        assertNull(man.getCart("2"));

        man.removeStoreView("0");

        assertNull(man.getCart("0"));
        assertNotNull(man.getCart("1"));
        assertNull(man.getCart("2"));
    }

    @Test
    void getCartTest(){
        assertNotNull(man.getCart("0"));
        assertNotNull(man.getCart("1"));
        assertNull(man.getCart("2"));

    }

}
