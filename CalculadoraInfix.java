import java.util.List;

public class CalculadoraInfix {

    private final Tokenizador tokenizador = new Tokenizador();

    public double evaluar(String expresion, IStack<String> pilaOps, IStack<Double> pilaVals) throws InfixException {
        String postfix = infixAPostfix(expresion, pilaOps);
        return evaluarPostfix(postfix, pilaVals);
    }

    public String infixAPostfix(String expresion, IStack<String> pilaOps) throws InfixException {
        pilaOps.clear();

        List<String> tokens = tokenizador.tokenizar(expresion);
        StringBuilder salida = new StringBuilder();

        for (String t : tokens) {
            if (esNumero(t)) {
                salida.append(t).append(' ');
            } else if (t.equals("(")) {
                pilaOps.push(t);
            } else if (t.equals(")")) {
                while (!pilaOps.isEmpty() && !pilaOps.peek().equals("(")) {
                    salida.append(pilaOps.pop()).append(' ');
                }
                if (pilaOps.isEmpty()) throw new InfixException("Paréntesis desbalanceados.");
                pilaOps.pop(); // quitar "("
            } else if (esOperador(t)) {
                while (!pilaOps.isEmpty()
                        && esOperador(pilaOps.peek())
                        && ((esAsociativoIzq(t) && precedencia(pilaOps.peek()) >= precedencia(t))
                            || (!esAsociativoIzq(t) && precedencia(pilaOps.peek()) > precedencia(t)))) {
                    salida.append(pilaOps.pop()).append(' ');
                }
                pilaOps.push(t);
            } else {
                throw new InfixException("Token inválido: " + t);
            }
        }

        while (!pilaOps.isEmpty()) {
            String op = pilaOps.pop();
            if (op.equals("(") || op.equals(")")) throw new InfixException("Paréntesis desbalanceados.");
            salida.append(op).append(' ');
        }

        return salida.toString().trim();
    }

    public double evaluarPostfix(String postfix, IStack<Double> pilaVals) throws InfixException {
        pilaVals.clear();

        if (postfix == null || postfix.isEmpty()) throw new InfixException("Postfix vacío.");

        String[] tokens = postfix.split("\\s+");

        for (String t : tokens) {
            if (esNumero(t)) {
                pilaVals.push(Double.parseDouble(t));
            } else if (esOperador(t)) {
                if (pilaVals.size() < 2) throw new InfixException("Faltan operandos para el operador " + t);
                double b = pilaVals.pop();
                double a = pilaVals.pop();
                pilaVals.push(aplicarOperador(t, a, b));
            } else {
                throw new InfixException("Token inválido en postfix: " + t);
            }
        }

        if (pilaVals.size() != 1) throw new InfixException("Expresión inválida (sobran valores).");
        return pilaVals.pop();
    }

    private double aplicarOperador(String op, double a, double b) throws InfixException {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new InfixException("División por cero.");
                return a / b;
            case "^": return Math.pow(a, b);
            default: throw new InfixException("Operador desconocido: " + op);
        }
    }

    private boolean esNumero(String t) {
        try {
            Double.parseDouble(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean esOperador(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/") || t.equals("^");
    }

    private int precedencia(String op) {
        switch (op) {
            case "^": return 3;
            case "*":
            case "/": return 2;
            case "+":
            case "-": return 1;
            default:  return 0;
        }
    }

    // ^ suele ser asociativo a la derecha
    private boolean esAsociativoIzq(String op) {
        return !op.equals("^");
    }
}