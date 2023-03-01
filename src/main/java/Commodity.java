import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Commodity {
    private String name;

    private String id;

    private String providerId;

    private int price;

    private String categories;
    private List<String> categoryList = new ArrayList<>();

    private double rating;

    private int numOfCommodities;

    private int inStock;
    @JsonCreator
    public Commodity(@JsonProperty("name")String name, @JsonProperty("id")String id, @JsonProperty("providerId")String providerId, @JsonProperty("price")int price, @JsonProperty("categories")String categories, @JsonProperty("rating")double rating, @JsonProperty("inStock")int inStock) {
        this.name = name;
        this.id = id;
        this.providerId = providerId;
        this.price = price;
        StringTokenizer tokStr = new StringTokenizer(categories, "[,]");
        String tok1,tok2;
        tok1 = tokStr.nextToken();
        tok2 = tokStr.nextToken();
        this.categoryList.add(tok1.trim());
        this.categoryList.add(tok2.trim());
        this.categories = categories;
        this.rating = rating;
        this.inStock = inStock;
        this.numOfCommodities = 0;
    }

    public void reduceOne(){
        this.inStock -= 1;
    }

    public boolean hasCategory(String category){
        for(String cat: categoryList){
            if (cat.equals(category)){
                return true;
            }
        }
        return false;
    }
    public String addRate(List<Double> addedRate){
        double sum = this.rating * this.numOfCommodities + addedRate.get(0);
        this.numOfCommodities += addedRate.get(1);
        this.rating = sum / this.numOfCommodities;
        return "Scoring applied.";
    }

    public String getName(){return name;};
    public String getId(){return id;};
    public String getProviderId(){return providerId;};
    public int getPrice(){return price;};
    public String getCategories(){return categories;};
    public double getRating(){return rating;};
    public int getInStock(){return inStock;};
}

