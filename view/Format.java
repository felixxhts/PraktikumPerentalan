package view;

import java.util.Locale;

public class Format {
    public static String rupiah(long nominal) {
        return String.format(Locale.US, "Rp %,d", nominal).replace(',', '.');
    }
}
