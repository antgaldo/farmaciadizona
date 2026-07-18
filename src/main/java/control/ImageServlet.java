package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/uploads/*")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String uploadPath;

    @Override
    public void init() throws ServletException {
        uploadPath = System.getenv("UPLOAD_PATH");

        if (uploadPath == null || uploadPath.isBlank()) {
            throw new ServletException("UPLOAD_PATH non configurata");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fileName = request.getPathInfo();

        if (fileName == null || fileName.equals("/")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // rimuove lo slash iniziale
        fileName = fileName.substring(1);

        Path filePath = Path.of(uploadPath, fileName);

        File file = filePath.toFile();

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String mimeType = Files.probeContentType(filePath);

        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);

        try (InputStream in = Files.newInputStream(filePath);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}