import junit.framework.TestCase;

public class BuylistsTest extends TestCase {
    Commodities commodityList = new Commodities();
    Users userList = new Users();
    Buylists buyList = new Buylists();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        commodityList = null;
        userList = null;
        buyList = null;
    }

    public void testAddBuyListUserNotExist() {
        Store store1 = new Store();
        store1.setCommand("addToBuyList");
        store1.setJsonData("{\"username\":\"user2\", \"commodityId\":\"243424\"}");
        Commodity commodity1 = new Commodity("ice2", "243424", "3", 3000, "[food, meat]", 8.8, 40);
        commodityList.addCommodity(commodity1);
        store1.setCommodityList(commodityList);
        Response response = store1.impelement();
        assertEquals(response.getSuccess(), false);
        assertEquals(response.getData(), "Username doesn't exist!");
    }

    public void testAddBuyListCommodityNotExist() {
        Store store1 =new Store();
        store1.setCommand("addToBuyList");
        store1.setJsonData("{\"username\":\"user1\", \"commodityId\":\"243424\"}");
        User user = new User("user1", "1234", "f.sdf@gamil.com", "1381-04-20", "sdfsfsd", 23);
        userList.addUser(user);
        store1.setUserList(userList);
        Response response = store1.impelement();

        assertEquals(response.getSuccess(), false);
        assertEquals(response.getData(), "ID doesn't exist!");
    }

    public void testAddBuyListNotInStock() {
        Store store1 = new Store();
        store1.setCommand("addToBuyList");
        store1.setJsonData("{\"username\":\"user1\", \"commodityId\":\"243425\"}");
        Commodity commodity1 = new Commodity("ice2", "243425", "3", 3000, "[food, meat]", 8.8, 0);
        commodityList.addCommodity(commodity1);
        store1.setCommodityList(commodityList);
        User user = new User("user1", "1234", "f.sdf@gamil.com", "1381-04-20", "sdfsfsd", 23);
        userList.addUser(user);
        store1.setUserList(userList);
        Response response = store1.impelement();
        assertEquals(response.getSuccess(), false);
        assertEquals(response.getData(), "The commodity is not available in stock!");
    }

    public void testAddBuyListAlreadyExist() {
        try {
            Buylists.Buylist testBuylist = new Buylists.Buylist("user1", "243425");
            buyList.addBuylist(testBuylist);
            buyList.addBuylist(testBuylist);
        }catch (Exception e){
            assertEquals(e.getMessage(), "This order already exists in the user's Buy list!");
        }
    }

    public void testAddBuyList() throws Exception {
        Buylists.Buylist testBuylist = new Buylists.Buylist("user1", "243425");
        String returnStr = buyList.addBuylist(testBuylist);
        assertEquals(returnStr, "Order successfully added to the user's Buy list");
    }
}