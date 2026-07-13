package util;

import java.util.ArrayList;
import java.util.List;
import util.InterfaceUtil.SelectorInterface;

public class SelectorUtil {
	public static <T> List<T> filtra(List<T> listaOriginale, SelectorInterface<T> filtro) {
        List<T> risultato = new ArrayList<>();
        for (T elemento : listaOriginale) {
            if (filtro.test(elemento)) {
                risultato.add(elemento);
            }
        }
        return risultato;
    }
}
