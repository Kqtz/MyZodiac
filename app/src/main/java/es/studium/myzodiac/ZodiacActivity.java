package es.studium.myzodiac;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ZodiacActivity extends AppCompatActivity {

    // Declaración de las vistas
    private TextView textViewZodiacSignAndAge;
    private ImageView imageViewZodiacSign;

    // Constante para los mensajes de log
    private static final String TAG = "ZodiacActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodiac);

        // Inicialización de las vistas
        textViewZodiacSignAndAge = findViewById(R.id.textViewZodiacSignAndAge);
        imageViewZodiacSign = findViewById(R.id.imageViewZodiacSign);

        // Obtener el día, mes y año desde los extras del Intent
        int day = getIntent().getIntExtra("day", -1);
        int month = getIntent().getIntExtra("month", -1);
        int year = getIntent().getIntExtra("year", -1);

        // Verificar si la fecha es válida
        if (day < 1 || day > 31 || month < 0 || month > 11 || year < 1900 || year > 2100) {
            Log.d(TAG, "Fecha inválida: Día = " + day + ", Mes = " + month + ", Año = " + year);
            textViewZodiacSignAndAge.setText("Fecha inválida");
            return;
        }

        // Obtener el signo zodiacal y la edad
        String zodiacSign = getZodiacSign(day, month);
        int age = calculateAge(year);
        String zodiacInfo = zodiacSign + " - Edad: " + age;
        textViewZodiacSignAndAge.setText(zodiacInfo);

        // Asignar la imagen correspondiente al signo zodiacal
        int zodiacImageResId = getZodiacImageResource(zodiacSign);
        if (zodiacImageResId != 0) {
            imageViewZodiacSign.setImageResource(zodiacImageResId);
        } else {
            Log.e(TAG, "Error: No se encontró la imagen del signo zodiacal.");
        }
    }

    // Método para calcular la edad
    private int calculateAge(int birthYear) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - birthYear;
    }

    // Método para obtener el signo zodiacal basado en el día y el mes
    private String getZodiacSign(int day, int month) {
        if ((month == 0 && day >= 20) || (month == 1 && day <= 18)) return "Acuario";
        else if ((month == 1 && day >= 19) || (month == 2 && day <= 20)) return "Piscis";
        else if ((month == 2 && day >= 21) || (month == 3 && day <= 19)) return "Aries";
        else if ((month == 3 && day >= 20) || (month == 4 && day <= 20)) return "Tauro";
        else if ((month == 4 && day >= 21) || (month == 5 && day <= 20)) return "Géminis";
        else if ((month == 5 && day >= 21) || (month == 6 && day <= 22)) return "Cáncer";
        else if ((month == 6 && day >= 23) || (month == 7 && day <= 22)) return "Leo";
        else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return "Virgo";
        else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return "Libra";
        else if ((month == 9 && day >= 23) || (month == 10 && day <= 21)) return "Escorpio";
        else if ((month == 10 && day >= 22) || (month == 11 && day <= 21)) return "Sagitario";
        else return "Capricornio";
    }

    // Método para obtener la imagen del signo zodiacal
    private int getZodiacImageResource(String zodiacSign) {
        switch (zodiacSign) {
            case "Acuario":
                return R.drawable.acuario;
            case "Piscis":
                return R.drawable.piscis;
            case "Aries":
                return R.drawable.aries;
            case "Tauro":
                return R.drawable.tauro;
            case "Géminis":
                return R.drawable.geminis;
            case "Cáncer":
                return R.drawable.cancer;
            case "Leo":
                return R.drawable.leo;
            case "Virgo":
                return R.drawable.virgo;
            case "Libra":
                return R.drawable.libra;
            case "Escorpio":
                return R.drawable.escorpio;
            case "Sagitario":
                return R.drawable.sagitario;
            case "Capricornio":
                return R.drawable.capricornio;
            default:
                return 0;
        }
    }
}
