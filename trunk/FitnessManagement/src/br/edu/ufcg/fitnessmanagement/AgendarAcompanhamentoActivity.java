package br.edu.ufcg.fitnessmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.SubMenu;
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
import br.edu.ufcg.util.Message;
import br.edu.ufcg.util.Utils;

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
				if(isDateValid()){
					menuSalvarAgendamento();
				}else{
					Builder builder  = Utils.showMessage(AgendarAcompanhamentoActivity.this,Message.ALERT, "Atenção", "Data inválida.");
					builder.show();
				}
			}
		});

	}

	private void menuSalvarAgendamento(){
		setContentView(R.layout.tela_salvar_agendamento);
		TextView dataResult = (TextView) findViewById(R.id.textViewDataResult);
		horarioResult = (TextView) findViewById(R.id.textViewHorarioResult);
		horarioResult.setText(getCurrentHourFormatter());
		dataResult.setText(gridCallendar.getDateSelected());

		Button bSalvar = (Button) findViewById(R.id.buttonSalvarMenuSalvarAgendamento);
		Button bVoltar = (Button) findViewById(R.id.buttonVoltarMenuSalvarAgendamento);
		ImageButton bRelogio = (ImageButton) findViewById(R.id.buttonClockAgendamento);

		bVoltar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				showCalendario();
			}
		});
		bSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				int selectedRadioId = ((RadioGroup) findViewById(R.id.radioGroupAgendamento)).getCheckedRadioButtonId();
				final RadioButton radioSexButton = (RadioButton) findViewById(selectedRadioId);
				if(radioSexButton.getText().equals(AgendamentoType.PAGAMENTO.value())){
					agendamentoFachada.adicionaAgendamento(aluno.getId(),gridCallendar.getDateFullFormatter(),AgendamentoType.PAGAMENTO.value(), Long.toString(getCalendarSelected().getTimeInMillis()));
				}else if(radioSexButton.getText().equals(AgendamentoType.TREINO.value())){
					agendamentoFachada.adicionaAgendamento(aluno.getId(),gridCallendar.getDateFullFormatter(),AgendamentoType.TREINO.value(), Long.toString(getCalendarSelected().getTimeInMillis()));
				}

				Builder dialog = Utils.showMessage(AgendarAcompanhamentoActivity.this,Message.CONFIRM, "Informação", "Agendameto salvo com sucesso.");
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();
						salvarAgendamento();
					}
				});
				dialog.show();
			}
		});

		bRelogio.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				showDialog(TIME_DIALOG_ID);				
			}
		});


	}
	private Calendar getCalendarSelected(){
		Calendar calendar = Calendar.getInstance();       

		calendar.set(Calendar.YEAR, gridCallendar.getCalendarYear());
		calendar.set(Calendar.MONTH, gridCallendar.geCalendarMonth());
		calendar.set(Calendar.DAY_OF_MONTH, gridCallendar.getCalendarDay());                 
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		return calendar;
	}

	private void salvarAgendamento() {
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		Intent intent = new Intent("br.edu.ufcg.agendamento.DisplayNotification");
		intent.putExtra("NotifID", aluno.getId());   
		PendingIntent displayIntent = PendingIntent.getActivity(
				getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);                   
		alarmManager.set(AlarmManager.RTC_WAKEUP, 
				getCalendarSelected().getTimeInMillis(), displayIntent);

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
	private String getCurrentHourFormatter(){
		GregorianCalendar gcalendar = new GregorianCalendar();
		hour = Integer.parseInt(gridCallendar.convertDigit(gcalendar.get(Calendar.HOUR_OF_DAY)));
		minute = Integer.parseInt(gridCallendar.convertDigit(gcalendar.get(Calendar.MINUTE)));
		return gridCallendar.convertDigit(hour) + ":" + gridCallendar.convertDigit(minute);
	}

	private void setGridCellAdapterToDate(int month, int year){
		gridCallendar = new GridCellCalendar(getApplicationContext(), R.id.calendar_day_gridcell, month, year,selectedDayMonthYearButton);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(getDateMonthYearFormatter());
		gridCallendar.notifyDataSetChanged();
		calendarView.setAdapter(gridCallendar);
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
					timePickerListener, Integer.parseInt(getCurrentHourFormatter().split(":")[0].trim()), Integer.parseInt(getCurrentHourFormatter().split(":")[1].trim()),false);
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
			horarioResult.setText(gridCallendar.convertDigit(hour) + ":" + gridCallendar.convertDigit(minute));
		}
	};

	public boolean isDateValid(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",new Locale("pt","br"));
		formatter.setLenient(false);
		try {
			if(gridCallendar != null ){
				Date hoje = formatter.parse(formatter.format(new Date()));
				Date dateSelected = formatter.parse(gridCallendar.getDateFullFormatter());
				if(dateSelected.equals(hoje)){
					return true;
				}
				else if(dateSelected.before(Calendar.getInstance().getTime())){
					return false;
				}else if(dateSelected.after(Calendar.getInstance().getTime())){
					return true;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}





	private final int VOLTAR = 1;
	private final int AJUDA = 2;

	private final int agendarAcompanhamento=3;
	String ajuda = "Ajuda";
	String voltar = "Voltar";


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//			// Inflate the menu; this adds items to the action bar if it is present.
		//			getMenuInflater().inflate(R.menu.activity_main, menu);
		//			return true;


		boolean r = super.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu);
		//menu.add(0, AJUDA, 0, ajuda).setIcon(R.drawable.alert);
		menu.add(0, VOLTAR, 0, voltar).setIcon(R.drawable.back);


		SubMenu menuAjuda = menu.addSubMenu(ajuda);
		menuAjuda.add(0, agendarAcompanhamento, 0, "Agendar Acompanhamento");
		menuAjuda.setIcon(R.drawable.help);

		return r;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){

		switch(item.getItemId()){


		case VOLTAR: mensagemExibir("Voltar", "Voltando",R.drawable.back); finish();  break;
		case AJUDA: mensagemExibir("Ajuda", "Agendar Acompanhamento",R.drawable.help); break;
		case agendarAcompanhamento: mensagemExibir("Agendar Acompanhamento", "Selecione uma data valida e clique em salvar, logo apos selecione um horario clicando no icone do relogio e selecione uma opcao, pagamento ou agendamento, escreva alguma observacao e salve.\n" +
				"Essa atividade agendada aparecera na tela de Historico Agendamento, voce recebera um alerta no celular para lembrar do agendamento.",R.drawable.help); break;
		}

		return super.onOptionsItemSelected(item);
	}

	public void mensagemExibir(String titulo, String texto,int icon){
		AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
		mensagem.setTitle(titulo);
		mensagem.setIcon(icon);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("OK", null);
		mensagem.show();
	}


}
