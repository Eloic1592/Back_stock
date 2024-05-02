package itusolar.controller.export;

import utilitaire.Utilitaire;

import javax.servlet.http.HttpServletRequest;

public class ExportParams {
    String extension, awhere, title, recap, logo, miniLogo, footer, fileName;
    String[] headers, headersLabel;

    public void init(HttpServletRequest request) {
        this.setExtension(request.getParameter("ext"));
//        this.setAwhere(request.getParameter("awhere"));
        this.setTitle(request.getParameter("titre"));
//        this.setType(request.getParameter("donnee"));
        this.setRecap(request.getParameter("recap"));
        this.initHeaders(request);
        this.initHeadersLabel(request);
    }

    private void initHeadersLabel(HttpServletRequest request) {
        String[] headersLabel = Utilitaire.split(request.getParameter("entetelibelle"), ";");
        this.setHeadersLabel(headersLabel);
    }

    private void initHeaders(HttpServletRequest request) {
        String[] headers = Utilitaire.split(request.getParameter("entete"), ";");
        this.setHeaders(headers);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMiniLogo() {
        return miniLogo;
    }

    public void setMiniLogo(String miniLogo) {
        this.miniLogo = miniLogo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getAwhere() {
        return awhere;
    }

    public void setAwhere(String awhere) {
        this.awhere = awhere;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public String getRecap() {
        return recap;
    }

    public void setRecap(String recap) {
        this.recap = recap;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public String[] getHeadersLabel() {
        return headersLabel;
    }

    public void setHeadersLabel(String[] headersLabel) {
        this.headersLabel = headersLabel;
    }
}
