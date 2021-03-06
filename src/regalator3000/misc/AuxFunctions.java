package regalator3000.misc;

public class AuxFunctions {
	
	/*0 -> year; 1 -> month; 2 -> day*/
	public static int getFieldFromDate(String dateText,int timeField){
		if (timeField >=0 && timeField <= 2){
			char splitter = dateText.charAt(4); //El char despres de 2017X...
			String[] valoresData =  dateText.split(Character.toString(splitter));
			return Integer.parseInt(valoresData[timeField]);
		}
		return -1;
	}
	
	public static String formatDateFromValues(int year, int month, int day, String separator){
		String monthStr,dayStr;
		if (day < 10){
			dayStr = "0" + Integer.toString(day);
		}
		else {
			dayStr = Integer.toString(day);
		}
		if (month < 10) {
			monthStr = "0" + Integer.toString(month);
		}
		else {
			monthStr = Integer.toString(month);
		}
		return (Integer.toString(year) + separator + monthStr + separator + dayStr);
	}

	public static int getMonthLengthDays(int month, int anyo){
		switch (month){
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			return 31;
		case 2:
			//Sacado de wikipedia: un año es bisiesto si es divisible entre cuatro y (no es divisible entre 100 ó es divisible entre 400).
			if (anyo % 4 == 0 && ( !(anyo % 100 == 0) || anyo % 400 == 0) ){
				return 29;
			}
			return 28;
		default:
			return 30;
		}
	}
}
