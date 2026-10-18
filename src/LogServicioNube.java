public class LogServicioNube implements DestinoLog {

    private String endpoint;

    public LogServicioNube(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public void guardarRegistro(String nivel, String mensaje) {

        // Regla propia: la nube solo recibe lo importante para ahorrar tráfico
        if (!nivel.equals("ERROR") && !nivel.equals("CRITICAL")) {
            System.out.println("[Nube] Registro de nivel " + nivel + " omitido (solo se envían ERROR y CRITICAL).");
            return;
        }

        System.out.println("[Nube] POST " + endpoint + " -> {\"nivel\":\"" + nivel
                + "\",\"mensaje\":\"" + mensaje + "\"}");
    }

    @Override
    public String getNombre() {
        return "servicio en la nube";
    }
}