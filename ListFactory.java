public class ListFactory {

    // tipoLista: 1=Simple, 2=Doble
    public static <T> IList<T> crearLista(int tipoLista) {
        if (tipoLista == 1) return new SinglyLinkedList<>();
        if (tipoLista == 2) return new DoublyLinkedList<>();
        throw new IllegalArgumentException("Tipo de lista inválido: " + tipoLista);
    }
}