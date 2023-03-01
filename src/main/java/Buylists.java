import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Buylists {


    static class Buylist{
        private String username;
        private String commodityId;

        @JsonCreator
        public Buylist(@JsonProperty("username") String username,@JsonProperty("commodityId") String commodityId) {
            this.username = username;
            this.commodityId = commodityId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }
    }
    private List<Buylist> buyLists = new ArrayList<Buylist>();
    
    public String addBuylist(Buylist newBuylist) throws Exception {
        for (int i = 0 ; i < buyLists.size() ; i++) {
            Buylist bl = buyLists.get(i);
            String usrn = bl.getUsername();
            String comid = bl.getCommodityId();
            if(usrn.equals(newBuylist.getUsername()) & comid.equals(newBuylist.getCommodityId())){
                throw new Exception("This order already exists in the user's Buy list!");
            }
        }
        buyLists.add(newBuylist);
        return "Order successfully added to the user's Buy list";
    }

    public String removeBuylist(Buylist buyList) {
        for (int i = 0 ; i < buyLists.size() ; i++) {
            Buylist bl = buyLists.get(i);
            String usrn = bl.getUsername();
            String comid = bl.getCommodityId();
            if(usrn.equals(buyList.getUsername()) & comid.equals(buyList.getCommodityId())){
                buyLists.remove(i);
                return "Order successfully removed from the user's Buy list";
            }
        }
        return "Order doesn't exist in the user's Buy list";
    }

    public ArrayList<String> getIdsByUsername(String username) {
        ArrayList<String> ids = new ArrayList<>();
        for(Buylist buylist: buyLists){
            if(buylist.getUsername().equals(username)){
                ids.add(buylist.getCommodityId());
            }
        }
        return ids;
    }

}
