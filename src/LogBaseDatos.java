import java.util.Arrays;
import java.util.List;

public class LogBaseDatos implements DestinoLog {

    private String tabla;
    private List<String> nivelesValidos = Arrays.asList("INFO", "WARN", "ERROR", "CRITICAL");

    public LogBaseDatos(String tabla) {
        this.tabla = tabla;
    }

    @Override
    public void guardarRegistro(String nivel, String mensaje) {

        if (!nivelesValidos.contains(nivel)) {
            System.out.println("[BD] Error: el nivel \"" + nivel + "\" no es válido, registro rechazado.");
            return;
        }

        System.out.println("[BD] INSERT INTO " + tabla + " (nivel, mensaje) VALUES ('"
                + nivel + "', '" + mensaje + "');");
    }

    @Override
    public String getNombre() {
        return "base de datos";
    }
}