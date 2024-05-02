package itusolar.controller.export;

import affichage.TableauRecherche;
import bean.CGenUtil;
import bean.ClassMAPTable;
import utilitaire.ChiffreLettre;
import utilitaire.Utilitaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Export generator.
 */
public class ExportGenerator implements ExportGeneratorSignature {
    XMLGeneratorSignature xmlGenerator;
    XMLGeneratorSignature csvGenerator;
    XLSGeneratorSignature xlsGenerator;
    PDFGeneratorSignature pdfGenerator;

    public ExportGenerator(XMLGeneratorSignature xmlGenerator, XMLGeneratorSignature csvGenerator, XLSGeneratorSignature xlsGenerator, PDFGeneratorSignature pdfGenerator) {
        this.xmlGenerator = xmlGenerator;
        this.csvGenerator = csvGenerator;
        this.xlsGenerator = xlsGenerator;
        this.pdfGenerator = pdfGenerator;
    }

    @Override
    public void export(ClassMAPTable object, HttpServletRequest request, HttpServletResponse response, String where, Connection connection) throws Exception {
        ExportParams params = new ExportParams();
        params.init(request);
        params.setAwhere(where);
        this.export(object, request, response, params, connection);
    }

    @Override
    public void export(ClassMAPTable object, HttpServletRequest request, HttpServletResponse response, ExportParams params, Connection connection) throws Exception {
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=" + this.fileName(params));
        params.setLogo(this.getPath(request, "logo.jpg"));
        params.setMiniLogo(this.getPath(request, "logo_min.jpg"));
        TableauRecherche sTable = this.searchTable(object, params, connection);
        OutputStream outputStream = response.getOutputStream();
        try {
            this.getXmlGenerator().export(outputStream, sTable, params);
            this.getCsvGenerator().export(outputStream, sTable, params);
            String footer = this.footer(sTable, params);
            params.setFooter(footer);
            this.getXlsGenerator().export(outputStream, sTable, params, request, response);
            this.getPdfGenerator().export(params, sTable, request, response);
            throw new NoExtensionFoundException();
        } catch (ExportFoundException ignored) {

        }
    }

    public String footer(TableauRecherche sTable,ExportParams params) {
        if (params.getRecap() == null) return "</body></html>";
        int number = sTable.getData().length;
        double amount = Utilitaire.stringToDouble(params.getRecap());
        return this.generateFooter(number, amount);
    }

    public String generateFooter(int number, double amount) {
        String htmlStringBasPage = "<div align='left'><h5>Arr�t� le nombre � " + Utilitaire.formaterSansVirgule(number) + "</h5>";
        if (amount > 0.) {
            htmlStringBasPage += "<h5>Arr�t� le pr�sent �tat � la somme de " + ChiffreLettre.convertRealToString(amount) + " Ariary</h5></div>";
        }
        return htmlStringBasPage;
    }

    public TableauRecherche searchTable(ClassMAPTable object, ExportParams params, Connection connection) throws Exception {
        ClassMAPTable[] c = (ClassMAPTable[]) CGenUtil.rechercher(object, null, null, connection, params.getAwhere());
        TableauRecherche tr = new TableauRecherche(c, params.getHeaders());
        tr.setLibelleAffiche(params.getHeadersLabel());
        tr.makeHtml();
        return tr;
    }

    /**
     * Gets path.
     *
     * @param request the request
     * @param imgPath the img path
     * @return the path
     */
    public String getPath(HttpServletRequest request, String imgPath) {
        return request.getServletContext().getRealPath(File.separator + "dist" + File.separator + "img" + File.separator + imgPath);
    }

    /**
     * File name string.
     *
     * @param params the params
     * @return the string
     */
    public String fileName(ExportParams params) {
        if (params.getTitle() == null) return "export";
        String title = params.getTitle().replaceAll(" ", "-");
        title = title+"-" + this.nowString() + "." + params.getExtension();
        return title;
    }

    /**
     * Now string string.
     *
     * @return the string
     */
    public String nowString() {
        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
        return fd.format(new Date());
    }

    public XLSGeneratorSignature getXlsGenerator() {
        return xlsGenerator;
    }

    public void setXlsGenerator(XLSGeneratorSignature xlsGenerator) {
        this.xlsGenerator = xlsGenerator;
    }

    public XMLGeneratorSignature getCsvGenerator() {
        return csvGenerator;
    }

    public void setCsvGenerator(XMLGeneratorSignature csvGenerator) {
        this.csvGenerator = csvGenerator;
    }

    public XMLGeneratorSignature getXmlGenerator() {
        return xmlGenerator;
    }

    public void setXmlGenerator(XMLGeneratorSignature xmlGenerator) {
        this.xmlGenerator = xmlGenerator;
    }

    public PDFGeneratorSignature getPdfGenerator() {
        return pdfGenerator;
    }

    public void setPdfGenerator(PDFGeneratorSignature pdfGenerator) {
        this.pdfGenerator = pdfGenerator;
    }
}
