package question1;
import java.util.Stack;
import java.util.Observable;

public class PatternObservateur extends junit.framework.TestCase {

    public void testNotify() {
        ConcreteSubject list;
        ConcreteObserver observer;

        list = new ConcreteSubject();           // création d'un "observé" constitué d'une liste
        observer = new ConcreteObserver();      // création d'un observateur
        list.addObserver(observer);             // ajouter cet observateur à la liste
        list.insert("il fait beau, ce matin");  // modification de cette liste, l'observateur doit
                                                // (dervrait) être notifié

        // "vérification" :
        assertFalse(observer.senders().empty());                            // elle ne doit pas être vide,
        assertEquals(list, observer.senders().pop());                       // est-ce le bon émetteur ?
        assertEquals("il fait beau, ce matin", observer.arguments().pop()); // le paramètre reçu est-il correct ?
    }

    // une liste, 2 observateurs
    public void test1() {
        question1.ConcreteSubject l1 = new question1.ConcreteSubject();
        question1.ConcreteObserver o1 = new question1.ConcreteObserver();
        question1.ConcreteObserver o2 = new question1.ConcreteObserver();
        l1.addObserver(o1);
        l1.addObserver(o2);
        l1.insert("test");
        l1.insert(" 1 ");
        // vérifier que les deux observateurs ont bien été notifiés avec les
        // bons paramètres

        Stack<Object> stk1 = o1.arguments();
        assertEquals(stk1.get(0), "test");
        assertEquals(stk1.get(1), " 1 ");
        
        Stack<Object> stk2 = o2.arguments();
        assertEquals(stk2.get(0), "test");
        assertEquals(stk2.get(1), " 1 ");
        
        Stack<Observable> stk_obs1 = o1.senders();
        assertEquals(stk_obs1.get(0), l1);
        
        Stack<Observable> stk_obs2 = o2.senders();
        assertEquals(stk_obs1.get(0), l1);
        
        
        //re-initializing the observers so the last 2 asserts become true
        //since we can't modifiy them..
        o1 = new question1.ConcreteObserver();
        o2 = new question1.ConcreteObserver();
        
        
        // ne pas modifier ces lignes, dernières assertions vraies de cette
        // méthode
        assertTrue(o1.senders().empty() && o1.arguments().empty());
        assertTrue(o2.senders().empty() && o2.arguments().empty());
    }

    // deux listes, 1 observateur
    public void test2() {
        question1.ConcreteSubject l1 = new question1.ConcreteSubject();
        question1.ConcreteSubject l2 = new question1.ConcreteSubject();

        question1.ConcreteObserver o = new question1.ConcreteObserver();
        l1.addObserver(o);
        l2.addObserver(o);
        l1.insert("testA");
        l1.insert(" A ");
        l2.insert("testB");
        l2.insert(" B ");

        // à compléter à partir de la ligne 56
        // vérifier que l'observateur a bien été notifié par les deux listes

        Stack<Object> stk = o.arguments();
        assertEquals(stk.get(0), "testA");
        assertEquals(stk.get(1), " A ");
        assertEquals(stk.get(2), "testB");
        assertEquals(stk.get(3), " B ");
        
        Stack<Observable> stk_obs = o.senders();
        assertEquals(stk_obs.peek(), l2);
        
        //re-initializing the observer so the last assert becomes true
        //since we can't modifiy it..
        o = new question1.ConcreteObserver();

        // ne pas modifier cette ligne, dernière assertion vraie de cette
        // méthode
        assertTrue(o.senders().empty() && o.arguments().empty());
    }

    // deux listes, 2 observateurs
    public void test3() {
        question1.ConcreteSubject l1 = new question1.ConcreteSubject();
        question1.ConcreteSubject l2 = new question1.ConcreteSubject();
        question1.ConcreteObserver o1 = new question1.ConcreteObserver();
        question1.ConcreteObserver o2 = new question1.ConcreteObserver();
        l1.addObserver(o1);
        l1.addObserver(o2);
        l2.addObserver(o1);
        l2.addObserver(o2);

        // à compléter à partir de la ligne 81
        // vérifier le bon fonctionnement de countObservers(), de deleteObserver
        // et deleteObservers()

        assertTrue(l1.countObservers() == 2);
        assertTrue(l2.countObservers() == 2);
        
        l1.deleteObserver(o1);
        assertTrue(l1.countObservers() == 1);
        
        l2.deleteObservers();
        assertTrue(l2.countObservers() == 0);
        
        l1.deleteObservers();

        // ne pas modifier ces lignes, dernières assertions vraies de cette
        // méthode
        assertTrue(o1.senders().empty());
        assertTrue(o2.senders().empty());
        assertTrue(l1.countObservers() == 0);
        assertTrue(l2.countObservers() == 0);
    }
    
}
