package inventorymanagement.client;

import bean.CGenUtil;
import inventorymanagement.article.ArticleManager;

import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class ClientManager  extends HServiceManager implements ClientManagerSignature{

    @Override
    public Client[] getall(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new Client(), new String[0], new String[0], connection, "");
        return cast(data);
    }
    public Client[] cast(Object[] datas) {
        Client[] clients = new Client[datas.length];
        for (int i = 0; i < datas.length; i++) {
            clients[i] = (Client) datas[i];
        }
        return clients;
    }
}
