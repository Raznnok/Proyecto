public class MainActivity extends Activity implements SensorEventListener {
	
	
	 private TextView textView;

	 private SensorManager mSensorManager;

     private Sensor mStepCounterSensor;

     private Sensor mStepDetectorSensor;
	 
	 
	  protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
     textView = (TextView) findViewById(R.id.textview);

      mSensorManager = (SensorManager)        
            getSystemService(Context.SENSOR_SERVICE);
      mStepCounterSensor = mSensorManager
            .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
      mStepDetectorSensor = mSensorManager
            .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
 }
 
 
	public void onSensorChanged(SensorEvent event) {
     Sensor sensor = event.sensor;
     float[] values = event.values;
     int value = -1;
    
     if (values.length > 0) {
        value = (int) values[0];
     }

      if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
        textView.setText("Step Counter Detected : " + value);
     } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
         // For test only. Only allowed value is 1.0 i.e. for step taken
         textView.setText("Step Detector Detected : " + value);
     }
 }
 
 
	protected void onResume() {

     super.onResume();

     mSensorManager.registerListener(this, mStepCounterSensor,

           SensorManager.SENSOR_DELAY_FASTEST);      
     mSensorManager.registerListener(this, mStepDetectorSensor,

           SensorManager.SENSOR_DELAY_FASTEST);

 }

	protected void onStop() {
		super.onStop();
		mSensorManager.unregisterListener(this, mStepCounterSensor);
		mSensorManager.unregisterListener(this, mStepDetectorSensor);
 }
	 
	 
	 
	 
 }