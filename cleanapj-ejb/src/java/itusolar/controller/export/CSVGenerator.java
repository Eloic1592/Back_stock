package itusolar.controller.export;

import affichage.TableauRecherche;

import java.io.IOException;
import java.io.OutputStream;

public class CSVGenerator implements XMLGeneratorSignature{
    @Override
    public void export(OutputStream outputStream, TableauRecherche tRecherche, ExportParams params) throws ExportFoundException, IOException {
        if (params.getExtension().compareToIgnoreCase("csv") == 0) {
            outputStream.write(tRecherche.getExpcsv().getBytes());
            throw new ExportFoundException();
        }
    }
}
