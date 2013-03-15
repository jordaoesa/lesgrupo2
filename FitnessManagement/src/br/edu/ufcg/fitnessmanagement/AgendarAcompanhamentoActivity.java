package br.edu.ufcg.fitnessmanagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import br.edu.ufcg.agendamento.Agendamento;
import br.edu.ufcg.agendamento.AgendamentoType;
import br.edu.ufcg.agendamento.GridCellCalendar;
import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fachada.AgendamentoFachada;
import br.edu.ufcg.fachada.FinancasFachada;
import br.edu.ufcg.util.FitnessManagementSingleton;

public class AgendarAcompanhamentoActivity extends Activity implements OnClickListener{

	private FinancasFachada financas;
	private Integer idAluno;

	private ImageView calendarToJournalButton;
	private Button selectedDayMonthYearButton;
	private Button currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private GridView calendarView;
	private GridCellCalendar gridCallendar;
	private Calendar _calendar;
	private int month, year;
	private final DateFormat dateFormatter = new DateFormat();
	private TextView horarioResult;
	private int hour;
	private int minute;
	private static final String dateTemplate = "MMMM yyyy";
	static final int TIME_DIALOG_ID = 999;

	private AgendamentoFachada agendamentoFachada;
	private Aluno aluno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		financas = FitnessManagementSingleton.getFinancasFachadaInstance();
		idAluno = getIntent().getIntExtra("id_aluno", -1);
		agendamentoFachada = FitnessManagementSingleton.getAgendamentoFachadaInstance();
		getAluno();
		showCalendario();
	}


	private void getAluno() {
		int idAluno = getIntent().getIntExtra("id_aluno", -1);
		aluno = FitnessManagementSingleton.getAlunoFachadaInstance().getAlunoFromId(idAluno);
	}


	private void showCalendario() {
		setContentView(R.layout.simple_calendar_view);
		setTitle("Agendar Acompanhamento");

		_calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);

		selectedDayMonthYearButton = (Button) findViewById(R.id.selectedDayMonthYear);
		selectedDayMonthYearButton.setText("Data selecionada");

		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);

		currentMonth = (Button) this.findViewById(R.id.currentMonth);

		currentMonth.setText(getDateMonthYearFormatter());

		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);

		calendarView = (GridView) this.findViewById(R.id.calendar);

		// Initialised
		gridCallendar = new GridCellCalendar(getApplicationContext(), R.id.calendar_day_gridcell, month, year,selectedDayMonthYearButton);
		gridCallendar.notifyDataSetChanged();
		calendarView.setAdapter(gridCallendar);
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarAgendarAcompanhamentoCalendar);
		Button bSalvar = (Button) findViewById(R.id.buttonSalvarAgendarAcompanhamentoCalendar);

		bVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});

		bSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				menuSalvarAgendamento();
			}
		});

	}

	private void menuSalvarAgendamento(){
		setContentView(R.layout.tela_salvar_agendamento);
		TextView dataResult = (TextView) findViewById(R.id.textViewDataResult);
		horarioResult = (TextView) findViewById(R.id.textViewHorarioResult);
		horarioResult.setText(getHourFormatter());
		dataResult.setText(gridCallendar.getDateSelected());

		Button bSalvar = (Button) findViewById(R.id.buttonSalvarMenuSalvarAgendamento);
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarMenuSalvarAgendamento);
		ImageButton bRelogio = (ImageButton) findViewById(R.id.buttonClockAgendamento);

		int selectedRadioId = ((RadioGroup) findViewById(R.id.radioGroupAgendamento)).getCheckedRadioButtonId();

		// find the radiobutton by returned id
		final RadioButton radioSexButton = (RadioButton) findViewById(selectedRadioId);

		bVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				showCalendario();
			}
		});
		bSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if(radioSexButton.getText().equals(AgendamentoType.PAGAMENTO.value())){
					agendamentoFachada.adicionaAgendamento(new Agendamento(aluno.getId(),gridCallendar.getDateFull(),AgendamentoType.PAGAMENTO));
				}else if(radioSexButton.getText().equals(AgendamentoType.TREINO.value())){
					agendamentoFachada.adicionaAgendamento(new Agendamento(aluno.getId(),gridCallendar.getDateFull(),AgendamentoType.TREINO));
				}
				salvarAgendamento();
			}
		});

		bRelogio.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				showDialog(TIME_DIALOG_ID);				
			}
		});


	}

	private void salvarAgendamento() {
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		//---get current date and time---
		Calendar calendar = Calendar.getInstance();       

		//---sets the time for the alarm to trigger---
		calendar.set(Calendar.YEAR, gridCallendar.getCalendarYear());
		calendar.set(Calendar.MONTH, gridCallendar.geCalendarMonth());
		calendar.set(Calendar.DAY_OF_MONTH, gridCallendar.getCalendarDay());                 
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ",new Locale("pt","br"));
		System.out.println("dia alarm: " + sdf.format(calendar.getTime()));
		SimpleDateFormat hourf = new SimpleDateFormat("MM:HH ",new Locale("pt","br"));
		System.out.println("HORARIO alarm: " + hour + ":" + minute + ">>>>" + calendar.getTime().getHours() + ":" + calendar.getTime().getMinutes());


		Intent intent = new Intent("br.edu.ufcg.agendamento.DisplayNotification");
		//---assign an ID of 1---
		intent.putExtra("NotifID", 1);   
		//---PendingIntent to launch activity when the alarm triggers-
		PendingIntent displayIntent = PendingIntent.getActivity(
				getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);                   
		//---sets the alarm to trigger---
		alarmManager.set(AlarmManager.RTC_WAKEUP, 
				calendar.getTimeInMillis(), displayIntent);

	}

	private String getDateFullFormatter(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ",new Locale("pt","br"));
		return sdf.format(_calendar.getTime());
	}
	private CharSequence getDateMonthYearFormatter() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM/yyyy ",new Locale("pt","br"));
		String data = sdf.format(_calendar.getTime());
		return data.substring(0,1).toUpperCase() + data.substring(1, data.length());
	}
	private String getHourFormatter(){
		GregorianCalendar gcalendar = new GregorianCalendar();
		return convertDigit(gcalendar.get(Calendar.HOUR)) + ":" +convertDigit(gcalendar.get(Calendar.MINUTE));
	}

	private void setGridCellAdapterToDate(int month, int year){
		gridCallendar = new GridCellCalendar(getApplicationContext(), R.id.calendar_day_gridcell, month, year,selectedDayMonthYearButton);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(getDateMonthYearFormatter());
		gridCallendar.notifyDataSetChanged();
		calendarView.setAdapter(gridCallendar);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:{
			TimePickerDialog timerPickerDialog = new TimePickerDialog(this, 
					timePickerListener, Integer.parseInt(getHourFormatter().split(":")[0].trim()), Integer.parseInt(getHourFormatter().split(":")[1].trim()),false);
			timerPickerDialog.setTitle("Horário do alarme");
			timerPickerDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", timerPickerDialog);
			timerPickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancelar", timerPickerDialog);
			return timerPickerDialog;
		}
		}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = 
			new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			horarioResult.setText(convertDigit(hour) + ":" + convertDigit(minute));
		}
	};

	private static String convertDigit(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

}
