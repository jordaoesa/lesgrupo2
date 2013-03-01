package br.edu.ufcg.fitnessmanagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import br.edu.ufcg.agendamento.GridCellCalendar;
import br.edu.ufcg.fachada.FinancasFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.os.Bundle;
import android.app.Activity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class AgendarAcompanhamentoActivity extends Activity implements OnClickListener{

	private FinancasFachada financas;
	private Integer idAluno;

	private ImageView calendarToJournalButton;
	private Button selectedDayMonthYearButton;
	private Button currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private GridView calendarView;
	private GridCellCalendar adapter;
	private Calendar _calendar;
	private int month, year;
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "MMMM yyyy";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agendar_acompanhamento);
		setTitle("Agendar Acompanhamento");

		financas = FitnessManagementSingleton.getFinancasFachadaInstance();
		idAluno = getIntent().getIntExtra("id_aluno", -1);

		menuAgendamento();
	}


	private void showCalendario() {
		setContentView(R.layout.simple_calendar_view);
		_calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);

		selectedDayMonthYearButton = (Button) findViewById(R.id.selectedDayMonthYear);
		selectedDayMonthYearButton.setText("Data selecionada");

		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);

		currentMonth = (Button) this.findViewById(R.id.currentMonth);

		currentMonth.setText(getDateFormatter());

		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);

		calendarView = (GridView) this.findViewById(R.id.calendar);

		// Initialised
		adapter = new GridCellCalendar(getApplicationContext(), R.id.calendar_day_gridcell, month, year,selectedDayMonthYearButton);
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarAgendarAcompanhamentoCalendar);


		bVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				menuAgendamento();
			}
		});
		
	}


	private CharSequence getDateFormatter() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM/yyyy ",new Locale("pt","br"));
		String data = sdf.format(_calendar.getTime());
		return data.substring(0,1).toUpperCase() + data.substring(1, data.length());
	}


	private void menuAgendamento() {
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarAgendarAcompanhamento);
		Button bAgendar = (Button) findViewById(R.id.buttonAgendarAgendarAcompanhamento);


		bAgendar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				showCalendario();

				//TODO FAZ A CHAMADA DO METODO COM A FUNCOES QUE TU QUER AQUI IRVILE E DEIXA MEU EDIT TEXT AQUI EM BAIXO.. kkk =P


//				EditText etValor = (EditText) findViewById(R.id.editTextValorAgendarAcompanhamento);
//				Double valor = null;
//				try{
//					valor = Double.parseDouble(etValor.getText().toString());
//				}catch(NumberFormatException ne){
//					valor = 0D;
//				}
//				financas.addDivida(idAluno, valor);
//				finish();
			}
		});
		bVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void setGridCellAdapterToDate(int month, int year){
		adapter = new GridCellCalendar(getApplicationContext(), R.id.calendar_day_gridcell, month, year,selectedDayMonthYearButton);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(getDateFormatter());
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_agendar_acompanhamento, menu);
		return true;
	}

	@Override
	public void onClick(View v){
		if (v == prevMonth)	{
			if (month <= 1)	{
				month = 12;
				year--;
			}
			else{
				month--;
			}
			setGridCellAdapterToDate(month, year);
		}
		if (v == nextMonth)	{
			if (month > 11)	{
				month = 1;
				year++;
			}
			else{
				month++;
			}
			setGridCellAdapterToDate(month, year);
		}

	}

}
