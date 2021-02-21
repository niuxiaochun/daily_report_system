package models.validators;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Attendance;

public class AttendanceValidator {
    public static List<String> validate(Attendance a) {
        List<String> errors = new ArrayList<String>();

        String in_time_error = _validateIn_time(a.getIn_time());
        if(!in_time_error.equals("")) {
            errors.add(in_time_error);
        }

        return errors;
    }

    private static String _validateIn_time( Timestamp in_time) {
        if(in_time == null || in_time.equals("")) {
            return "出勤しておりません。";
        }

        return "";
    }
}
