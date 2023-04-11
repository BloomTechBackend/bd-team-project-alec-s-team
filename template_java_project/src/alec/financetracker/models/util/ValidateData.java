package alec.financetracker.models.util;


import java.util.HashMap;
import java.util.Set;

import java.util.regex.Pattern;



public final class ValidateData {
    private static final String regex = "^\\d{4}/\\d{2}/\\d{2}$";
    private static final Pattern VALID_DATE = Pattern.compile(regex);
    public ValidateData(){}
    public static boolean isValidDate(final String dateToValidate){
        return VALID_DATE.matcher(dateToValidate).find();
    }
    public static boolean isNumericallyValidDate(final String dateToValidate){
        HashMap<String, Integer> months = new HashMap<>();
        months.put("01", 31);
        months.put("02", 29);
        months.put("03", 31);
        months.put("04", 30);
        months.put("05", 31);
        months.put("06", 30);
        months.put("07", 31);
        months.put("08", 31);
        months.put("09", 30);
        months.put("10", 31);
        months.put("11", 30);
        months.put("12", 31);
        //2020/04/43
        if (months.get(dateToValidate.substring(5,7)) >= Integer.parseInt(dateToValidate.substring(8,10))){
            return true;
        }else{
            return false;
        }

    }
}