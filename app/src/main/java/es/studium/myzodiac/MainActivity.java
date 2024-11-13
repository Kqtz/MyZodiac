package es.studium.myzodiac;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // Declaración de las vistas
    private DatePicker selectorFecha;
    private Button botonAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de las vistas
        selectorFecha = findViewById(R.id.datePicker);
        botonAceptar = findViewById(R.id.buttonObtenerSigno);

        // Acción para el botón Aceptar
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el día, mes y año seleccionados
                int dia = selectorFecha.getDayOfMonth();
                int mes = selectorFecha.getMonth();
                int ano = selectorFecha.getYear();

                // Log para verificar los valores antes de enviar
                Log.d("MainActivity", "Fecha seleccionada: Día = " + dia + ", Mes = " + mes + ", Año = " + ano);

                // Verificar si la fecha es válida
                if (esFechaValida(ano, mes, dia)) {
                    // Enviar los datos al siguiente Activity
                    Intent intent = new Intent(MainActivity.this, ZodiacActivity.class);
                    intent.putExtra("day", dia);
                    intent.putExtra("month", mes);
                    intent.putExtra("year", ano);
                    startActivity(intent);
                } else {
                    // Mostrar un mensaje de error si la fecha no es válida
                    Toast.makeText(MainActivity.this, R.string.invalid_date, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Método para verificar si la fecha de nacimiento es válida (no futura).
     */
    private boolean esFechaValida(int año, int mes, int dia) {
        Calendar hoy = Calendar.getInstance();
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.set(año, mes, dia);
        return !fechaNacimiento.after(hoy); // Verifica que la fecha no esté en el futuro
}
}