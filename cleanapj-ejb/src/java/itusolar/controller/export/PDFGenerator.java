package itusolar.controller.export;

import affichage.TableauRecherche;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class PDFGenerator extends Generator implements PDFGeneratorSignature{
    @Override
    public void export(ExportParams params, TableauRecherche tSearch, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (params.getExtension().compareToIgnoreCase("pdf") == 0) {
            response.setContentType("application/pdf");
            tSearch.makeHtmlPDF();
            boolean isLandScape = this.askOrientation(request);
            String path = this.getPath(request, params);
            params.setFileName(path);
            File file = new File(path);
            String header = this.header(params, tSearch);
            OutputStream outputStream = new FileOutputStream(path);
            Document document = this.document(isLandScape);
            this.toPDF(params, outputStream, document, header);
            document.close();
            outputStream.close();
            this.send(path, response);
            file.delete();
            throw new ExportFoundException();
        }
    }

    public void send(String path, HttpServletResponse response) throws IOException {
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(path);

        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    public void toPDF(ExportParams params, OutputStream outputStream, Document document, String header) throws DocumentException, IOException {
        PdfWriter.getInstance(document, outputStream);
        document.open();
        HTMLWorker htmlWorker = new HTMLWorker(document);
        String content = header + params.getFooter();
        htmlWorker.parse(new StringReader(content));
//        htmlWorker.parse(new StringReader(params.getFooter()));
//        System.out.println(header);
//        System.out.println(params.getFooter());
//        String content = header + params.getFooter();

    }

    public Document document(boolean isLandScape) {
        return isLandScape? new Document(PageSize.A4.rotate()) : new Document();
    }

    public String header(ExportParams params, TableauRecherche tSearch) throws Exception {
        String header = this.header(params.getTitle(), params.getLogo());
        header += tSearch.getHtmlPDF();
        return header;
    }

    public String getPath(HttpServletRequest request, ExportParams params) {
        return request.getServletContext().getRealPath("")+ params.getFileName();
    }

    public boolean askOrientation(HttpServletRequest request) {
        if (request.getParameter("orientation") == null) return true;
        return request.getParameter("orientation").equals("1");
    }
}
