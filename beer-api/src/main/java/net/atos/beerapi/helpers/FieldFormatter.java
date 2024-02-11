package net.atos.beerapi.helpers;

public class FieldFormatter {

    public String capitalizeString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
}
