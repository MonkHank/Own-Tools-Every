



public class MainActivity extends Activity {

	private EditText et_main_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_main_show = (EditText) findViewById(R.id.et_main_show);


		IntentFilter filter = new IntentFilter();
		filter.addAction("com.android.server.scannerservice.broadcast");
		
		registerReceiver(receiver, filter);

	}

	BroadcastReceiver receiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String stringExtra = intent.getStringExtra("scannerdata");
		}
	};
}
