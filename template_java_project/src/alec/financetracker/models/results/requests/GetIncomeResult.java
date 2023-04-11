package alec.financetracker.models.results.requests;

import alec.financetracker.activity.GetIncomeActivity;

public class GetIncomeResult{

    private String income;
    private int amount;


    public GetIncomeResult(){
    }

    public GetIncomeResult(String income, int amount){
        this.income = income;
        this.amount = amount;
    }
    public GetIncomeResult(Builder builder){
        this.income = builder.income;
        this.amount = builder.amount;
    }
    public static Builder builder(){
        return new Builder();
    }

    public void setIncome(String income) {
        this.income = income;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getIncome(){
        return income;
    }
    public int getAmount(){
        return amount;
    }
    public static final class Builder{
        String income;
        int amount;
        private Builder(){

        }
        public Builder withIncome(String income){
            this.income = income;
            return this;
        }
        public Builder withAmount(int amount){
            this.amount = amount;
            return this;
        }
        public GetIncomeResult build(){
            return new GetIncomeResult(this);
        }
    }
    public String toString(){
        return "income: " + income + " amount: " + amount;
    }
}