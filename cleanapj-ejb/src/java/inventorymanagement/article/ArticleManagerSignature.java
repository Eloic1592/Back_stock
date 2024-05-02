package inventorymanagement.article;



import java.sql.Connection;

public interface ArticleManagerSignature {
    public void create(Article article, Connection connection) throws Exception;
    public Article[] getall(Connection connection) throws Exception;
    public Listearticle getOne(String idarticle,Connection connection) throws Exception;
    public ArticlePageList contentlist(Connection connection) throws Exception;

}
