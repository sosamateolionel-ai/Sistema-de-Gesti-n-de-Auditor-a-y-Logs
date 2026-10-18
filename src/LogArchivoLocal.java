public class LogArchivoLocal implements DestinoLog {

    private String rutaArchivo;
    private int lineasEscritas;

    public LogArchivoLocal(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.lineasEscritas = 0;
    }

    @Override
    public void guardarRegistro(String nivel, String mensaje) {

        if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
            System.out.println("[Archivo] Error: no hay ruta de archivo configurada.");
            return;
        }

        lineasEscritas++;
        System.out.println("[Archivo] Línea " + lineasEscritas + " añadida a " + rutaArchivo
                + " -> " + nivel + " | " + mensaje);
    }

    @Override
    public String getNombre() {
        return "archivo local";
    }
}