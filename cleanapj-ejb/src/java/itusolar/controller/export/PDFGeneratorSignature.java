package itusolar.controller.export;

import affichage.TableauRecherche;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PDFGeneratorSignature {
    public void export(ExportParams params, TableauRecherche tSearch, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
