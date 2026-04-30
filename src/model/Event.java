package model;

import java.time.LocalDate;

public class Event {

    private LocalDate date;
    private double hours;

    /**
     * Constructor.
     * Crea un evento con fecha y duración.
     * @param date fecha del evento
     * @param hours duración en horas
     * pre: date != null && hours > 0
     * post: el evento queda creado con sus atributos inicializados
     */
    public Event(LocalDate date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    /**
     * Método tipo analizador.
     * Retorna la cantidad de horas del evento.
     * @return horas del evento
     * pre: ninguno
     * post: no modifica el objeto
     */
    public double getHours() {
        return hours;
    }
}