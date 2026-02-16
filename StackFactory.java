public class StackFactory {

    /*
      grupo:
        1 = "pila con stack/arreglo" (Vector/ArrayList)
        2 = "pila con lista" (simple/doble)
      opcion:
        si grupo=1 -> 1=Vector, 2=ArrayList
        si grupo=2 -> 1=ListaSimple, 2=ListaDoble
    */
    public static <T> IStack<T> crearStack(int grupo, int opcion) {

        if (grupo == 1) {
            if (opcion == 1) return new StackVector<>();
            if (opcion == 2) return new StackArrayList<>();
            throw new IllegalArgumentException("Opción inválida para grupo 1: " + opcion);
        }

        if (grupo == 2) {
            IList<T> lista = ListFactory.crearLista(opcion);
            return new StackLista<>(lista);
        }

        throw new IllegalArgumentException("Grupo inválido: " + grupo);
    }
}