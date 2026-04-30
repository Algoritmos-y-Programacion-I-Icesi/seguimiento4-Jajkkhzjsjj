package model;

import java.time.LocalDate;

public class Controller {

    private Device[][] deviceMatrix; // nombre correcto

    public Controller() {
        deviceMatrix = new Device[12][4];
    }

    /**
     * Método tipo modificador.
     * Agrega un dispositivo en el piso indicado si hay espacio y el serial no existe.
     * @param serial Serial único del dispositivo
     * @param consumption Consumo en KWh
     * @param floor Piso donde se ubicará (0-11)
     * @return true si se agregó correctamente, false en caso contrario
     * pre: serial != null && floor >= 0 && floor < 12
     * post: si retorna true, el dispositivo queda almacenado en la matriz
     */
    public boolean addDevice(String serial, double consumption, int floor) {

        if (searchDevice(serial) != null) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (deviceMatrix[floor][i] == null) {
                deviceMatrix[floor][i] = new Device(serial, consumption);
                return true;
            }
        }

        return false;
    }

    /**
     * Método tipo analizador.
     * Busca un dispositivo por su serial.
     * @param serial Serial del dispositivo
     * @return el dispositivo si existe, null si no
     * pre: serial != null
     * post: no modifica el estado del sistema
     */
    public Device searchDevice(String serial) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {
                if (deviceMatrix[i][j] != null &&
                    deviceMatrix[i][j].getSerial().equals(serial)) {
                    return deviceMatrix[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Método tipo modificador.
     * Registra un evento a un dispositivo existente.
     * @param serial Serial del dispositivo
     * @param date Fecha del evento
     * @param hours Duración en horas del evento
     * @return true si se agregó el evento, false si no existe el dispositivo
     * pre: serial != null && date != null && hours > 0
     * post: si retorna true, el evento queda agregado al dispositivo
     */
    public boolean addEvent(String serial, LocalDate date, double hours) {

        Device d = searchDevice(serial);

        if (d != null) {
            d.addEvent(new Event(date, hours));
            return true;
        }

        return false;
    }

    /**
     * Método tipo modificador.
     * Actualiza el valor de consumo de un dispositivo.
     * @param serial Serial del dispositivo
     * @param newConsumption Nuevo valor de consumo
     * @return true si se actualizó, false si no existe el dispositivo
     * pre: serial != null && newConsumption >= 0
     * post: si retorna true, el consumo del dispositivo cambia
     */
    public boolean updateConsumption(String serial, double newConsumption) {

        Device d = searchDevice(serial);

        if (d != null) {
            d.setConsumption(newConsumption);
            return true;
        }

        return false;
    }

    /**
     * Método tipo analizador.
     * Calcula el consumo total de un dispositivo.
     * @param serial Serial del dispositivo
     * @return el consumo total o -1 si no existe el dispositivo
     * pre: serial != null
     * post: no modifica el estado del sistema
     */
    public double calculateTotalConsumption(String serial) {

        Device d = searchDevice(serial);

        if (d != null) {
            return d.getTotalHours() * d.getConsumption();
        }

        return -1;
    }

    /**
     * Método tipo analizador.
     * Obtiene los dispositivos cuyo consumo es mayor a 0.3 KWh.
     * @return String con serial y consumo de los dispositivos que requieren cambio
     * pre: ninguno
     * post: no modifica el estado del sistema
     */
    public String devicesToChange() {

        String result = "";

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {

                if (deviceMatrix[i][j] != null &&
                    deviceMatrix[i][j].getConsumption() > 0.3) {

                    result += "Serial: " + deviceMatrix[i][j].getSerial() +
                              " Consumo: " + deviceMatrix[i][j].getConsumption() + "\n";
                }
            }
        }

        return result;
    }
}