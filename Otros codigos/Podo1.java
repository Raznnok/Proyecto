import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements SensorEventListener {


    // Variables
    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Localizar textView
        count = (TextView)findViewById(R.id.pasos);

        //Localizar el sensorManager
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    protected void onResume(){
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
    }

    protected void onPause(){
        super.onPause();
        activityRunning = false;
    }

    public void onSensorChanged(SensorEvent event){
        if (activityRunning) {
            count.setText(String.valueOf(event.values[0]));
        }

     }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }