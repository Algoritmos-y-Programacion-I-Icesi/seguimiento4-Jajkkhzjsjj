package model;

import java.util.ArrayList;

public class Device {

    private String serial;
    private double consumption;
    private ArrayList<Event> events;

    /**
     * Constructor.
     * Crea un dispositivo con serial y consumo inicial.
     * @param serial Serial del dispositivo
     * @param consumption Consumo en KWh
     * pre: serial != null && consumption >= 0
     * post: se crea el dispositivo con lista de eventos vacía
     */
    public Device(String serial, double consumption) {
        this.serial = serial;
        this.consumption = consumption;
        events = new ArrayList<>();
    }

    /**
     * Método tipo analizador.
     * Retorna el serial del dispositivo.
     * @return serial
     * pre: ninguno
     * post: no modifica el objeto
     */
    public String getSerial() {
        return serial;
    }

    /**
     * Método tipo analizador.
     * Retorna el consumo del dispositivo.
     * @return consumo
     * pre: ninguno
     * post: no modifica el objeto
     */
    public double getConsumption() {
        return consumption;
    }

    /**
     * Método tipo modificador.
     * Actualiza el consumo del dispositivo.
     * @param consumption nuevo valor de consumo
     * pre: consumption >= 0
     * post: el consumo queda actualizado
     */
    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    /**
     * Método tipo modificador.
     * Agrega un evento al dispositivo.
     * @param e evento a agregar
     * pre: e != null
     * post: el evento queda almacenado en la lista
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Método tipo analizador.
     * Calcula la suma de horas de todos los eventos.
     * @return total de horas
     * pre: ninguno
     * post: no modifica el objeto
     */
    public double getTotalHours() {
        double total = 0;
        for (int i = 0; i < events.size(); i++) {
            total += events.get(i).getHours();
        }
        return total;
    }
}