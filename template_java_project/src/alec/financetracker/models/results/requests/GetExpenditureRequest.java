package alec.financetracker.models.results.requests;

import alec.financetracker.activity.CreateExpenditureActivity;

public class GetExpenditureRequest {

    private String purchaseName;
    private String time;


    public GetExpenditureRequest(){
    }

    public GetExpenditureRequest(String purchaseName, String time, String category, int cost ){
        this.purchaseName = purchaseName;
        this.time = time;
    }
    public GetExpenditureRequest(Builder builder){
        this.purchaseName = builder.purchaseName;
        this.time = builder.time;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getPurchaseName(){
        return purchaseName;
    }
    public String getTime(){
        return time;
    }
    public static final class Builder{
        String purchaseName;
        String time;
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
        public GetExpenditureRequest build(){
            return new GetExpenditureRequest(this);
        }
    }
    public String toString(){
        return "purchaseName: " + purchaseName + " Time: " + time;
    }
}