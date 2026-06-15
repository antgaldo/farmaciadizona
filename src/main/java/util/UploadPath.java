package util;
import jakarta.servlet.http.Part;
import java.util.UUID;

public class UploadPath {
	public String buildUniqueFileName(Part part) {
		String originalName = part.getSubmittedFileName();
		String extension;
		if (originalName.contains(".")) {
		    extension = originalName.substring(originalName.lastIndexOf("."));
		} else {
		    extension = "";
		}
		return UUID.randomUUID() + extension;
	}
}
