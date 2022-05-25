
import com.syntech.model.BoughtItems;
import com.syntech.model.ITableInfo;
import com.syntech.repository.JDBC;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sagar
 */
public class Test {

   public static JDBC jdbc = new JDBC();

    public static void main(String... args) throws Exception {
            List<ITableInfo> list = new ArrayList();
        BoughtItems item = new BoughtItems(null, 10l, 20l);
        list.add(item);
        jdbc.writeToDatabase(list);
    }

}

