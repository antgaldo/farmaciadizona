package util;

import java.util.List;

import model.dto.ElementoCarrelloDTO;

public class ConvertCartJson {
	public static String convertCartJson(List<ElementoCarrelloDTO> carrello) {
	    if (carrello == null || carrello.isEmpty()) {
	        return "[]";
	    }

	    StringBuilder json = new StringBuilder();
	    json.append("[");

	    for (int i = 0; i < carrello.size(); i++) {
	        ElementoCarrelloDTO item = carrello.get(i);
	        String nome = item.getNome().replace("\"", "\\\"");
	        String nomeFarmacia = item.getNomeFarmacia().replace("\"", "\\\"");
	        String path = item.getPath() != null ? item.getPath().replace("\\", "\\\\").replace("\"", "\\\"") : "";

	        json.append("{");
	        json.append("\"idProdotto\":\"").append(item.getIdProdotto()).append("\",");
	        json.append("\"nome\":\"").append(nome).append("\",");
	        json.append("\"nomeFarmacia\":\"").append(nomeFarmacia).append("\",");
	        json.append("\"quantita\":").append(item.getQuantita()).append(",");
	        json.append("\"path\":\"").append(path).append("\",");
	        json.append("\"prezzoTot\":").append(item.getPrezzo());
	        json.append("}");

	        if (i < carrello.size() - 1) {
	            json.append(",");
	        }
	    }

	    json.append("]"); 
	    return json.toString();
	}
}
