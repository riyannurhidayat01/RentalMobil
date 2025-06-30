/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 *
 * @author ASUS
 */
public class LanguageUtil {
    private static ResourceBundle bundle;

    public static void setLanguage(String lang) {
        Locale locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("resources.lang", locale);
    }

    public static String get(String key) {
        return bundle.getString(key);
    }
}
