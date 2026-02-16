import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJUnit {

    // =========================
    // TESTS STACK (PILA)
    // =========================

    @Test
    void stackVector_debeSerLIFO() {
        IStack<Integer> s = new StackVector<>();
        s.push(1);
        s.push(2);
        s.push(3);

        assertEquals(3, s.pop());
        assertEquals(2, s.pop());
        assertEquals(1, s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    void stackArrayList_peekNoDebeSacar() {
        IStack<String> s = new StackArrayList<>();
        s.push("A");
        s.push("B");

        assertEquals("B", s.peek());
        assertEquals(2, s.size());
        assertEquals("B", s.pop());
    }

    @Test
    void stackPop_enVacia_lanzaEmptyStackException() {
        IStack<Integer> s = new StackVector<>();
        assertThrows(EmptyStackException.class, s::pop);
        assertThrows(EmptyStackException.class, s::peek);
    }

    @Test
    void stackLista_simple_funciona() {
        IStack<Integer> s = new StackLista<>(new SinglyLinkedList<>());
        s.push(10);
        s.push(20);

        assertEquals(20, s.pop());
        assertEquals(10, s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    void stackLista_doble_funciona() {
        IStack<Integer> s = new StackLista<>(new DoublyLinkedList<>());
        s.push(7);
        s.push(8);
        s.push(9);

        assertEquals(9, s.pop());
        assertEquals(8, s.pop());
        assertEquals(7, s.pop());
    }

    // =========================
    // TESTS CALCULADORA INFIX
    // =========================

    @Test
    void calcular_sinParentesis_respetaPrecedencia() throws Exception {
        double r = evaluarConVector("3+4*2");
        assertEquals(11.0, r, 1e-9);
    }

    @Test
    void calcular_conParentesis() throws Exception {
        double r = evaluarConVector("(3+4)*2");
        assertEquals(14.0, r, 1e-9);
    }

    @Test
    void calcular_menosUnario() throws Exception {
        double r = evaluarConVector("-5+2");
        assertEquals(-3.0, r, 1e-9);
    }

    @Test
    void calcular_potencia_asociativaDerecha() throws Exception {
        double r = evaluarConVector("2^3^2");
        assertEquals(512.0, r, 1e-9);
    }

    @Test
    void divisionPorCero_lanzaInfixException() {
        assertThrows(InfixException.class, () -> evaluarConVector("10/(5-5)"));
    }

    @Test
    void parentesisMalos_lanzaInfixException() {
        assertThrows(InfixException.class, () -> evaluarConVector("(3+2"));
    }

    // =========================
    // Helpers
    // =========================
    private double evaluarConVector(String expr) throws InfixException {
        CalculadoraInfix c = new CalculadoraInfix();
        IStack<String> ops = new StackVector<>();
        IStack<Double> vals = new StackVector<>();
        return c.evaluar(expr, ops, vals);
    }
}