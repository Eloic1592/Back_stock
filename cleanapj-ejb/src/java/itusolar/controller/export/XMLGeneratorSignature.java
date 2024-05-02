package itusolar.controller.export;

import affichage.TableauRecherche;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

/**
 * The interface Xml generator signature.
 */
public interface XMLGeneratorSignature {
    /**
     * Export.
     *
     * @param outputStream the output stream
     * @param tRecherche   the t recherche
     * @param params       the params
     * @throws ExportFoundException the export found exception
     * @throws IOException          the io exception
     */
    public void export(OutputStream outputStream, TableauRecherche tRecherche, ExportParams params) throws ExportFoundException, IOException;
}
