import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Ratings {
    static class Rating{
        private String username;
        private String commodityId;
        private double score;
        @JsonCreator
        public Rating(@JsonProperty("username") String username, @JsonProperty("commodityId") String commodityId, @JsonProperty("score") double score) {
            this.username = username;
            this.commodityId = commodityId;
            this.score = score;
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
        public double getScore() {
            return score;
        }
        public void setScore(int score) {
            this.score = score;
        }
    }

    private List<Rating> ratingList  = new ArrayList<Rating>();
    
    public List<Double> addRating(Rating newRating) throws Exception {
        double num = 1;
        double pervScore = 0;
        double finalScore = newRating.getScore();
        if(finalScore > 10 | finalScore < 1)
            throw new Exception("Score is out of range[1,10].");
        if(finalScore != (int)finalScore){
            throw new Exception("Score is not Integer.");
        }
        for (int i = 0 ; i < ratingList.size() ; i++) {
            Rating rt = ratingList.get(i);
            String usrn = rt.getUsername();
            String comid = rt.getCommodityId();
            if(usrn.equals(newRating.getUsername()) & comid.equals(newRating.getCommodityId())){
                pervScore = rt.getScore();
                this.ratingList.remove(i);
                finalScore -= pervScore;
                num = 0;
                break;
            }
        }
        ratingList.add(newRating);
        ArrayList<Double> retArr = new ArrayList<>();
        retArr.add(finalScore);
        retArr.add(num);
        return retArr;
    }


}
