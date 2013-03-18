package br.edu.ufcg.agendamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import br.edu.ufcg.fitnessmanagement.R;


public class GridCellCalendar extends BaseAdapter implements OnClickListener {
	private final Context _context;

	private final List<String> list;
	private static final int DAY_OFFSET = 1;
	private final String[] weekdays = new String[] { "Dom", "Seg", "Ter",
			"Qua", "Qui", "Sex", "Sáb" };
	private final String[] months = { "Janeiro", "Fevereiro", "Março", "Abril",
			"Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro",
			"Novembro", "Dezembro" };
	private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 };
	private final int month, year;
	private int daysInMonth, prevMonthDays;
	private int currentDayOfMonth;
	private int currentWeekDay;
	private Button gridcell;
	private TextView num_events_per_day;
	private final HashMap eventsPerMonthMap;
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"dd-MMM-yyyy");
	private Button selectedDayMonthYearButton;

	private String date_month_year;

	public GridCellCalendar(Context context, int textViewResourceId, int month,
			int year, Button selectedDayMonthYearButton) {
		super();
		this._context = context;
		this.list = new ArrayList<String>();
		this.month = month;
		this.year = year;
		this.selectedDayMonthYearButton = selectedDayMonthYearButton;

		Calendar calendar = Calendar.getInstance();
		setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
		setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));

		printMonth(month, year);

		eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
	}

	private String getMonthAsString(int i) {
		return months[i];
	}
	private String getMonthNumber(String month){
		int indexMonth = 0;
		for (int i = 0; i < months.length; i++) {
			if(months[i].equals(month)){
				indexMonth = i + 1;
				break;
			}
		}
		return convertDigit(indexMonth);
	}

	private String getWeekDayAsString(int i) {
		return weekdays[i];
	}

	private int getNumberOfDaysOfMonth(int i) {
		return daysOfMonth[i];
	}

	public String getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	private void printMonth(int mm, int yy) {
		int trailingSpaces = 0;
		int leadSpaces = 0;
		int daysInPrevMonth = 0;
		int prevMonth = 0;
		int prevYear = 0;
		int nextMonth = 0;
		int nextYear = 0;

		int currentMonth = mm - 1;
		String currentMonthName = getMonthAsString(currentMonth);
		daysInMonth = getNumberOfDaysOfMonth(currentMonth);


		GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);

		if (currentMonth == 11) {
			prevMonth = currentMonth - 1;
			daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
			nextMonth = 0;
			prevYear = yy;
			nextYear = yy + 1;
		} else if (currentMonth == 0) {
			prevMonth = 11;
			prevYear = yy - 1;
			nextYear = yy;
			daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
			nextMonth = 1;
		} else {
			prevMonth = currentMonth - 1;
			nextMonth = currentMonth + 1;
			nextYear = yy;
			prevYear = yy;
			daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
		}

		int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		trailingSpaces = currentWeekDay;

		if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1) {
			++daysInMonth;
		}

		for (int i = 0; i < trailingSpaces; i++) {
			list.add(String
					.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET)
							+ i)
							+ "-GREY"
							+ "-"
							+ getMonthAsString(prevMonth)
							+ "-"
							+ prevYear);
		}

		for (int i = 1; i <= daysInMonth; i++) {
			if (i == getCurrentDayOfMonth()) {
				list.add(String.valueOf(i) + "-BLUE" + "-"
						+ getMonthAsString(currentMonth) + "-" + yy);
			} else {
				list.add(String.valueOf(i) + "-WHITE" + "-"
						+ getMonthAsString(currentMonth) + "-" + yy);
			}
		}

		for (int i = 0; i < list.size() % 7; i++) {
			list.add(String.valueOf(i + 1) + "-GREY" + "-"
					+ getMonthAsString(nextMonth) + "-" + nextYear);
		}
	}

	private HashMap findNumberOfEventsPerMonth(int year, int month) {
		HashMap map = new HashMap<String, Integer>();
		return map;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) _context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.calendar_day_gridcell, parent,
					false);
		}

		gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
		gridcell.setOnClickListener(this);


		String[] day_color = list.get(position).split("-");
		String theday = day_color[0];
		String themonth = day_color[2];
		String theyear = day_color[3];
		if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null)) {
			if (eventsPerMonthMap.containsKey(theday)) {
				num_events_per_day = (TextView) row
						.findViewById(R.id.num_events_per_day);
				Integer numEvents = (Integer) eventsPerMonthMap.get(theday);
				num_events_per_day.setText(numEvents.toString());
			}
		}

		gridcell.setText(theday);
		gridcell.setTag(theday + "-" + themonth + "-" + theyear);

		if (day_color[1].equals("GREY")) {
			gridcell.setTextColor(Color.LTGRAY);
		}
		if (day_color[1].equals("WHITE")) {
			gridcell.setTextColor(Color.WHITE);
		}
		if (day_color[1].equals("BLUE")) {
			gridcell.setTextColor(Color.BLUE);
		}
		return row;
	}

	@Override
	public void onClick(View view) {
		date_month_year = (String) view.getTag();
		selectedDayMonthYearButton.setText("Data: " + date_month_year);
	}
	
	public String getDateFullFormatter(){
		String[] data = date_month_year.split("-");
		return convertDigit(Integer.parseInt(data[0])) +"/" + getMonthNumber(data[1]) + "/" + data[2];
	}
	public int getCalendarYear(){ 
		return Integer.parseInt(date_month_year.split("-")[2]);
	}
	public int geCalendarMonth(){
		return (Integer.parseInt(getMonthNumber(date_month_year.split("-")[1])) - 1);
	}
	public int getCalendarDay(){
		return Integer.parseInt(date_month_year.split("-")[0]);
	}

	public String getDateSelected(){
		return this.date_month_year;
	}
	public int getCurrentDayOfMonth() {
		return currentDayOfMonth;
	}

	private void setCurrentDayOfMonth(int currentDayOfMonth) {
		this.currentDayOfMonth = currentDayOfMonth;
	}

	public void setCurrentWeekDay(int currentWeekDay) {
		this.currentWeekDay = currentWeekDay;
	}

	public int getCurrentWeekDay() {
		return currentWeekDay;
	}
	public String convertDigit(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
}
