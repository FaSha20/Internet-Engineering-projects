import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class CommoditiesTest extends TestCase {
    private ObjectMapper mapper = new ObjectMapper();
    private Commodities commodityList = new Commodities();
    private Commodity commodity1 = new Commodity("ice2","243425", "3",3000,"[food, meat]",8.8,40);

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testFindById() throws Exception {
        commodityList.addCommodity(commodity1);
        String testId = "243425";
        assertEquals(commodity1, commodityList.findById(testId));
    }

    @Test
    public void testFindByIdNotExist() {
        Store store1 = new Store();
        store1.setCommand("getCommodityById");
        store1.setJsonData("{\"id\":\"23424\"}");
        Commodity commodity1 = new Commodity("ice2", "243423", "3", 3000, "[food, meat]", 8.8, 40);
        commodityList.addCommodity(commodity1);
        store1.setCommodityList(commodityList);
        Response response = store1.impelement();
        assertEquals(response.getSuccess(), false);
        assertEquals(response.getData(), "ID doesn't exist!");
    }
    /*public void testFindByCategoryNotExistCat() throws JsonProcessingException {
        Commodity commodity1 = new Commodity("ice2", "243425", "3", 3000, "[food, meat]", 8.8, 0);

        commodityList.addCommodity(commodity1);
       // commodityList.addCommodity(commodity2);
       Object r = commodityList.findByCategory("food ");

        assertEquals(r,new Commodities.ComByCat(commodityList.getCommodityList()));
    }*/

    @After
    public void tearDown() throws Exception {
        mapper = null;
        commodityList = null;
        commodity1 = null;
    }

    public void testAddCommodity() {
    }
}