import junit.framework.TestCase;

import java.util.List;

public class RatingsTest extends TestCase {
    Commodities commodityList = new Commodities();
    Users userList = new Users();
    Ratings ratingList = new Ratings();
    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        ratingList = null;
    }

    public void testAddRatingNotIntScore(){
        try{
            double perScore = 1.2;
            Ratings.Rating rate = new Ratings.Rating("user1", "123", perScore);
            List<Double> newScore = ratingList.addRating(rate);
        }catch (Exception e){
            assertEquals(e.getMessage(), "Score is not Integer.");
        }
    }
    public void testAddRatingOutOfRangeScore(){
        try{
            double perScore = 11;
            Ratings.Rating rate = new Ratings.Rating("user1", "123", perScore);
            List<Double> newScore = ratingList.addRating(rate);
        }catch (Exception e){
            assertEquals(e.getMessage(), "Score is out of range[1,10].");
        }
    }
    public void testAddRatingSameUser() throws Exception {
        int primeScore = 7;
        int secScore = 10;
        Ratings.Rating rate1 = new Ratings.Rating("user1", "123", primeScore);
        List<Double>addedScore = ratingList.addRating(rate1);
        Ratings.Rating rate2 = new Ratings.Rating("user1", "123", secScore);
        List<Double>addedScore2 = ratingList.addRating(rate2);
        assertEquals(addedScore2.get(0).intValue(), secScore - primeScore);
        assertEquals(addedScore2.get(1).intValue(), 0);
    }
    public void testAddRating() throws Exception {
        int primeScore = 7;
        int secScore = 10;
        Ratings.Rating rate1 = new Ratings.Rating("user1", "123", primeScore);
        List<Double>addedScore = ratingList.addRating(rate1);
        Ratings.Rating rate2 = new Ratings.Rating("user2", "123", secScore);
        List<Double>addedScore2 = ratingList.addRating(rate2);
        assertEquals(addedScore2.get(0).intValue(), secScore);
        assertEquals(addedScore2.get(1).intValue(), 1);
    }
    public void testAddRatingUsernameNotExist(){
        Store store1 = new Store();
        store1.setCommand("rateCommodity");
        store1.setJsonData("{\"username\":\"user1\", \"commodityId\":\"243423\", \"score\":\"1\"}");
        Commodity commodity1 = new Commodity("ice2", "243423", "3", 3000, "[food, meat]", 8.8, 40);
        commodityList.addCommodity(commodity1);
        store1.setCommodityList(commodityList);
        Response response = store1.impelement();
        assertEquals(response.getSuccess(), false);
        assertEquals(response.getData(), "Username doesn't exist!");
    }
    public void testAddRatingCommodityNotExist() {
        Store store1 =new Store();
        store1.setCommand("rateCommodity");
        store1.setJsonData("{\"username\":\"user1\", \"commodityId\":\"243424\"}");
        User user = new User("user1", "1234", "f.sdf@gamil.com", "1381-04-20", "sdfsfsd", 23);
        userList.addUser(user);
        store1.setUserList(userList);
        Response response = store1.impelement();
        assertEquals(response.getSuccess(), false);
        assertEquals(response.getData(), "ID doesn't exist!");
    }
}