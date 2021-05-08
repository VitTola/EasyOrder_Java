package com.tola.easy_orders;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateControls {

    public static boolean ValidateEamil(EditText editText) {
        if (editText.getText().toString().length() == 0) {
            editText.setError("Please enter email address!");
            return false;
        } else {
            String regex = "[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(editText.getText());
            if (!matcher.matches()) {
                editText.setError("Invalid email address!");
                return false;
            }
            return true;
        }
    }
    public static boolean ValidatePassword(EditText editText) {
        if (editText.getText().toString().length() == 0) {
            editText.setError("Please enter password!");
            return false;
        } else {
            String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(editText.getText());
            if (!matcher.matches()) {
                editText.setError("Invalid password!");
                return false;
            }
            return true;
        }
    }
}