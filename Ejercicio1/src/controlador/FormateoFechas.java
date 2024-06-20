package controlador;

import java.util.Calendar;

public class FormateoFechas {
    public static String formatearFechaAString(Calendar fecha){
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int año = fecha.get(Calendar.YEAR);
        
        String diaStr = (dia < 10) ? "0" + dia : String.valueOf(dia);
        String mesStr = (mes < 10) ? "0" + mes : String.valueOf(mes);
        String añoStr = String.valueOf(año);
        
        return diaStr + "/" + mesStr + "/" + añoStr;
    }

    public static Calendar formatearStringACalendar(String fecha){
        Calendar fechaCalendar = Calendar.getInstance();

        String partesFecha[] = fecha.split("/");

        int dia = Integer.parseInt(partesFecha[0]);
        int mes = Integer.parseInt(partesFecha[1]) - 1;
        int año = Integer.parseInt(partesFecha[2]);

        fechaCalendar.set(año, mes, dia);

        return fechaCalendar;
    }
}
