//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    GestorAuditoria gestor = new GestorAuditoria();

    gestor.agregarDestino(new LogArchivoLocal("/var/log/app.log"));
    gestor.agregarDestino(new LogBaseDatos("eventos_sistema"));
    gestor.agregarDestino(new LogServicioNube("https://logs.empresa.com/v1/eventos"));


    gestor.registrar("INFO", "Servidor iniciado correctamente.");


    gestor.registrar("CRITICAL", "Falla en el disco principal del servidor 3.");

    // Nivel inexistente: la base de datos lo rechaza
    gestor.registrar("DEBUG2", "Traza interna.");

    // Envío selectivo
    gestor.registrarEn("base de datos", "ERROR", "Timeout en consulta de clientes.");

    // Destino inexistente
    gestor.registrarEn("syslog", "ERROR", "Timeout en consulta de clientes.");

}
