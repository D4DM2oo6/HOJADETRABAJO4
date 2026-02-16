import java.util.ArrayList;
import java.util.List;

public class Tokenizador {

    public List<String> tokenizar(String expresion) throws InfixException {
        if (expresion == null) throw new InfixException("Expresión nula.");

        String s = expresion.replaceAll("\\s+", "");
        List<String> tokens = new ArrayList<>();

        StringBuilder numero = new StringBuilder();
        String prev = ""; // para detectar menos unario

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean esOperador = (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
            boolean esParen = (c == '(' || c == ')');

            // Menos unario: al inicio o después de operador o '('
            if (c == '-' && (i == 0 || prev.equals("(") || esOperador(prev))) {
                numero.append(c);
                prev = "-";
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                numero.append(c);
                prev = String.valueOf(c);
                continue;
            }

            // Cierra número si veníamos construyendo
            if (numero.length() > 0) {
                tokens.add(numero.toString());
                numero.setLength(0);
            }

            if (esOperador || esParen) {
                tokens.add(String.valueOf(c));
                prev = String.valueOf(c);
            } else {
                throw new InfixException("Carácter inválido: " + c);
            }
        }

        if (numero.length() > 0) tokens.add(numero.toString());

        return tokens;
    }

    private boolean esOperador(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/") || t.equals("^");
    }
}