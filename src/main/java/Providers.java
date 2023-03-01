import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class Providers {
    List<Provider> ProviderList = new ArrayList<Provider>();

    public Provider findById(String id) throws Exception {
        for (Provider prv:ProviderList) {
            String prvid = prv.getId();
            if(prvid.equals(id)){
                return prv;
            }
        }
        throw new Exception("Provider doesn't exist!");
    }

    public String addProvider(Provider newprovider){
        String newProvidername = newprovider.getName();
        String returnStr = newProvidername + " added successfully!";
        for (int i = 0 ; i < ProviderList.size() ; i++) {
            Provider usr = ProviderList.get(i);
            String name = usr.getName();
            if(name.equals(newProvidername)){
                this.ProviderList.remove(i);
                returnStr = newProvidername + " updated successfully!";
                break;
            }
        }
        this.ProviderList.add(newprovider);
        return returnStr;
    }

    public String getProviders(){
        String str = "";
        for (Provider usr: ProviderList) {
            str += usr.toString();
        }
        return str;
    }
}
