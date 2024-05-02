package itusolar.controller.export;

import affichage.TableauRecherche;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public interface XLSGeneratorSignature {
    public void export(OutputStream outputStream, TableauRecherche tRecherche, ExportParams params, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
