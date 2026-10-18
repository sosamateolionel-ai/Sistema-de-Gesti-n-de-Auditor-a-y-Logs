import java.util.ArrayList;
import java.util.List;

public class GestorAuditoria {

    private List<DestinoLog> destinos;

    public GestorAuditoria() {
        this.destinos = new ArrayList<>();
    }

    public void agregarDestino(DestinoLog destino) {
        destinos.add(destino);
        System.out.println("Destino registrado: " + destino.getNombre());
    }

    // Difusión masiva: el mismo registro a todos los destinos
    public void registrar(String nivel, String mensaje) {

        System.out.println("\n=== Registro " + nivel + ": \"" + mensaje + "\" ===");

        if (destinos.isEmpty()) {
            System.out.println("Gestor: no hay destinos registrados.");
            return;
        }

        for (DestinoLog destino : destinos) {
            destino.guardarRegistro(nivel, mensaje);
        }
    }

    // Difusión selectiva: solo a un destino, buscado por nombre
    public void registrarEn(String nombreDestino, String nivel, String mensaje) {

        System.out.println("\n=== Registro selectivo en " + nombreDestino + " ===");

        for (DestinoLog destino : destinos) {
            if (destino.getNombre().equals(nombreDestino)) {
                destino.guardarRegistro(nivel, mensaje);
                return;
            }
        }

        System.out.println("Gestor: el destino \"" + nombreDestino + "\" no está registrado.");
    }
}