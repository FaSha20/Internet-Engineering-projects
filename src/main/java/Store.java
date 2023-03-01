import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private String command;
    private String jsonDataStr;
    Response response = new Response(true, "");
    Users userList = new Users();
    Providers providerList = new Providers();
    Commodities commodityList = new Commodities();
    Ratings ratingList = new Ratings();
    Buylists buyLists = new Buylists();
    
    public Response impelement(){
        try {
            Object returnedData = "";
            ObjectMapper mapper = new ObjectMapper();

            switch (command) {
                case "addUser":
                    User user1 = mapper.readValue(jsonDataStr, User.class);
                    returnedData = userList.addUser(user1);
                    break;
                case "getUsers":
                    returnedData = userList.getUsers();
                    break;
                case "addProvider":
                    Provider provider1 = mapper.readValue(jsonDataStr, Provider.class);
                    returnedData = providerList.addProvider(provider1);
                    break;
                case "addCommodity":
                    Commodity commodity1 = mapper.readValue(jsonDataStr, Commodity.class);
                    returnedData = commodityList.addCommodity(commodity1);
                    String itsProviderId = commodity1.getProviderId();
                    Provider itsProvider = providerList.findById(itsProviderId);
                    itsProvider.addCommodity(commodity1.getId());
                    break;
                case "getCommoditiesList":
                    returnedData = commodityList.getCommodityList();
                    break;
                case "getCommodityById":
                    ID id1 = mapper.readValue(jsonDataStr, ID.class);
                    returnedData = commodityList.findById(id1.getId());
                    break;
                case "getCommoditiesByCategory":
                    Category cat1 = mapper.readValue(jsonDataStr, Category.class);
                    returnedData = commodityList.findByCategory(cat1.getCategory());
                    break;
                case "rateCommodity":
                    Ratings.Rating rate1 = mapper.readValue(jsonDataStr, Ratings.Rating.class);
                    List<Double> addedScore = ratingList.addRating(rate1);
                    String raterUsername = rate1.getUsername();
                    User rater = userList.findByUsername(raterUsername);
                    String ratedCommodityId = rate1.getCommodityId();
                    Commodity ratedCommodity = commodityList.findById(ratedCommodityId);
                    returnedData = ratedCommodity.addRate(addedScore);
                    break;
                case "addToBuyList":
                    Buylists.Buylist buyList1 = mapper.readValue(jsonDataStr, Buylists.Buylist.class);
                    User buyer = userList.findByUsername(buyList1.getUsername());
                    Commodity commodity = commodityList.findById(buyList1.getCommodityId());
                    if(commodity.getInStock() == 0){
                        throw new Exception("The commodity is not available in stock!");
                    }
                    commodity.reduceOne();
                    returnedData = buyLists.addBuylist(buyList1);
                    break;
                case "removeFromBuyList":
                    Buylists.Buylist buyList2 = mapper.readValue(jsonDataStr, Buylists.Buylist.class);
                    User buyer2 = userList.findByUsername(buyList2.getUsername());
                    Commodity commodity2 = commodityList.findById(buyList2.getCommodityId());
                    returnedData = buyLists.removeBuylist(buyList2);
                    break;
                case "getBuyList":
                    Username usernameObj = mapper.readValue(jsonDataStr, Username.class);
                    userList.findByUsername(usernameObj.getUsername());
                    ArrayList<String>ids = buyLists.getIdsByUsername(usernameObj.getUsername());
                    returnedData = commodityList.getBuyListByIds(ids);
                    break;

            }
            response = new Response(true, returnedData);

        }catch (Exception error){
            response = new Response(false, error.getMessage());
        }
        return response;
    }
    

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getJsonData() {
        return jsonDataStr;
    }

    public void setJsonData(String jsonDataStr) {
        this.jsonDataStr = jsonDataStr;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setUserList(Users userList) {
        this.userList = userList;
    }

    public void setProviderList(Providers providerList) {
        this.providerList = providerList;
    }

    public void setCommodityList(Commodities commodityList) {
        this.commodityList = commodityList;
    }

    public void setRatingList(Ratings ratingList) {
        this.ratingList = ratingList;
    }

    public void setBuyLists(Buylists buyLists) {
        this.buyLists = buyLists;
    }
    static class ID{
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
    static class Category{
        private String category;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
    static class Username{
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
