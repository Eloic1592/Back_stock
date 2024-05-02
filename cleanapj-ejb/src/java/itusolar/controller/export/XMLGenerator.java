package itusolar.controller.export;

import affichage.TableauRecherche;

import java.io.IOException;
import java.io.OutputStream;

public class XMLGenerator implements XMLGeneratorSignature{
    @Override
    public void export(OutputStream outputStream, TableauRecherche tRecherche, ExportParams params) throws ExportFoundException, IOException {
        if (params.getExtension().compareToIgnoreCase("xml") == 0) {
            outputStream.write(tRecherche.getExpxml().getBytes());
            throw new ExportFoundException();
        }
    }
}
