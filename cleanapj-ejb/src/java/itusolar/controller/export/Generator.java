package itusolar.controller.export;

public class Generator {
    public String header(String title, String logo, String width, String height) {
        String header = "<html><head><title></title></head><body>";
        header += "<h1>"+title+"</h1>";
        header += "<br/>";
        return header;
    }

    public String header(String title, String logo) {
        return header(title, logo, "200px", "75px");
    }
}
