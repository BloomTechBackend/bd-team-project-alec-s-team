package alec.financetracker.models;

import alec.financetracker.dynamodb.objects.Expenditure;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ExpenditureModel {
    private String purchaseName;
    private String time;
    private String category;
    private int cost;


    public ExpenditureModel(){

    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public String getTime() {
        return time;
    }

    public String getCategory() {
        return category;
    }

    public int getCost() {
        return cost;
    }

    public ExpenditureModel(Builder builder){
        this.purchaseName = builder.purchaseName;
        this.time = builder.time;
        this.category = builder.category;
        this.cost = builder.cost;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder{
        private String purchaseName;
        private String time;
        private String category;
        private int cost;
        public Builder withPurchaseName(String name){
            this.purchaseName = name;
            return this;
        }
        public Builder withTime(String time){
            this.time = time;
            return this;
        }
        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }
        public Builder withCost(int cost){
            this.cost = cost;
            return this;
        }
        public ExpenditureModel build(){
            return new ExpenditureModel(this);
        }
        }
        public String toString(){
            return "purchaseName: " + purchaseName + " Time: " + time
                    + " category: " + category + " cost: " + String.valueOf(cost);
        }
}
