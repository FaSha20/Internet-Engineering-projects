import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public class Commodities {

    List<Commodity> commodityList  = new ArrayList<Commodity>();

    public Object getBuyListByIds(ArrayList<String> ids) throws Exception {
        List<Commodity> filteredCommodeties = new ArrayList<>();
        for(String id: ids){
            filteredCommodeties.add(this.findById(id));
        }
        BuyListOut buylist = new BuyListOut();
        buylist.setBuyList(filteredCommodeties);
        return buylist;
    }


    class ComJson{
        private List<Commodity>commoditiesList;
        @JsonCreator
        public ComJson(@JsonProperty("commoditiesListByCategory")List<Commodity> commoditiesList) {
            this.commoditiesList = commoditiesList;
        }

        public List<Commodity> getCommoditiesList() {
            return commoditiesList;
        }

        public void setCommoditiesList(List<Commodity> commoditiesList) {
            this.commoditiesList = commoditiesList;
        }
    }
    static class ComByCat{
        private List<Commodity>commoditiesListByCategory;
        @JsonCreator
        public ComByCat(@JsonProperty("commoditiesListByCategory") List<Commodity>commoditiesListByCategory){
            this.commoditiesListByCategory = commoditiesListByCategory;
        }

        public List<Commodity> getCommoditiesListByCategory() {
            return commoditiesListByCategory;
        }

        public void setCommoditiesListByCategory(List<Commodity> commoditiesListByCategory) {
            this.commoditiesListByCategory = commoditiesListByCategory;
        }
    }

    class BuyListOut{
        private List<Commodity>buyList = new ArrayList<>();

        public List<Commodity> getBuyList() {
            return buyList;
        }

        public void setBuyList(List<Commodity> buyList) {
            this.buyList = buyList;
        }
    }
    public Commodity findById(String id) throws Exception {
        for (Commodity com:commodityList) {
            String comid = com.getId();
            if(comid.equals(id)){
                return com;
            }
        }
        throw new Exception("ID doesn't exist!");
    }

    public Object findByCategory(String category) {
        ArrayList<Commodity> filteredCategories = new ArrayList<>();
        for(Commodity commodity: this.commodityList){
            if(commodity.hasCategory(category.trim())){
                filteredCategories.add(commodity);
            }
        }
        return new ComByCat(filteredCategories);
    }

    public String addCommodity(Commodity newcommodity) {
        String returnStr = newcommodity.getName() + " added successfully!";
        for (int i = 0 ; i < commodityList.size() ; i++) {
            Commodity com = commodityList.get(i);
            String id = com.getId();
            if(id.equals(newcommodity.getId())){
                this.commodityList.remove(i);
                returnStr = newcommodity.getName() + " updated successfully!";
                break;
            }
        }
        this.commodityList.add(newcommodity);
        return returnStr;
    }

    public Object getCommodityList() throws JsonProcessingException {
        ComJson commodities = new ComJson(commodityList);
        return commodities;
    }
}

