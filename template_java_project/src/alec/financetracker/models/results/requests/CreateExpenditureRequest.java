package alec.financetracker.models.results.requests;

import alec.financetracker.activity.CreateExpenditureActivity;

public class CreateExpenditureRequest {

    private String purchaseName;
    private String time;
    private String category;
    private int cost;

    public CreateExpenditureRequest(){

    }

    public CreateExpenditureRequest(String purchaseName, String time, String category, int cost){
        this.purchaseName = purchaseName;
        this.time = time;
        this.category = category;
        this.cost = cost;
    }
    public CreateExpenditureRequest(Builder builder){
        this.purchaseName = builder.purchaseName;
        this.time = builder.time;
        this.category = builder.category;
        this.cost = cost;
    }
    public String getpurchaseName(){
        return purchaseName;
    }
    public String getTime(){
        return time;
    }
    public String getCategory(){
        return category;
    }
    public int getCost(){
        return cost;
    }
    public void setpurchaseName(String purchaseName){
        this.purchaseName = purchaseName;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder{
        private String purchaseName;
        private String time;
        private String category;
        private int cost;
        private Builder(){

        }
        public Builder wtihPurchaseName(String name){
            this.purchaseName = name;
            return this;
        }
        public Builder withTime(String time){
            this.time = time;
            return this;
        }
        public Builder withCategory(String category){
            this.category = category;
            return this;
        }
        public Builder withCost(int cost){
            this.cost = cost;
            return this;
        }
        public CreateExpenditureRequest build(){
            return new CreateExpenditureRequest(this);
        }
    }
    public String toString(){
        return "purchaseName: " + purchaseName + " Time: " + time
                + " category: " + category + " cost: " + String.valueOf(cost);
    }


}
