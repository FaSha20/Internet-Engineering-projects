import java.util.ArrayList;
import java.util.List;

public class Provider {
    private String name;

    private String id;

    private String registryDate;

    private List<String> commodityList = new ArrayList<String>();

    private double meanRate;

    public void addCommodity(String newCommodity){
        commodityList.add(newCommodity);
    }

    public String getName(){return name;};
    public String getId(){return id;};
    public String getRegistryDate(){return registryDate;};
   public double getMeanRate(Commodities commodities){
        Commodity com;
        double sum = 0;
        for(int i = 0; i < commodityList.size(); i++){
            try{
                com = commodities.findById(commodityList.get(i));
                sum += com.getRating();
                break;
            }catch (Exception e){
                continue;
            }
        }
        meanRate = sum / commodityList.size();
        return meanRate;
    }

    public void setName(String str){this.name = str;};
    public void setId(String str){this.id = str;};
    public void setRegistryDate(String str){this.registryDate = str;};

    public String toString(){
        return " * " + name + ", " + id ;
    }
}
