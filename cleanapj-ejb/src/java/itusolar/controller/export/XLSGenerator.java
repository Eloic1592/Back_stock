package itusolar.controller.export;

import affichage.TableauRecherche;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class XLSGenerator extends Generator implements XLSGeneratorSignature{
    @Override
    public void export(OutputStream outputStream, TableauRecherche sTable, ExportParams params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (params.getExtension().compareToIgnoreCase("xls") == 0 ) {
            response.setContentType("application/vnd.ms-excel");
            String header = this.header(params.getTitle(), params.getMiniLogo());
            String content = header + "" + sTable.getHtml() + "" + params.getFooter();
            outputStream.write(content.getBytes());
            throw new ExportFoundException();
        }
    }
}
