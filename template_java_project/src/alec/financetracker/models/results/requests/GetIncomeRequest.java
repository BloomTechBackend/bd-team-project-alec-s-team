package alec.financetracker.models.results.requests;

import alec.financetracker.activity.CreateExpenditureActivity;

public class GetIncomeRequest {

    private String income;
    private String amount;


    public GetIncomeRequest(){
    }

    public GetIncomeRequest(String income, String amount){
        this.income = income;
        this.amount = amount;
    }
    public GetIncomeRequest(Builder builder){
        this.income = builder.income;
        this.amount = builder.amount;
    }

    public void setIncome(String income) {
        this.income = income;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getIncome(){
        return income;
    }
    public String getAmount(){
        return amount;
    }
    public static final class Builder{
        String income;
        String amount;
        private Builder(){

        }
        public Builder wtihIncome(String income){
            this.income = income;
            return this;
        }
        public Builder withAmount(String amount){
            this.amount = amount;
            return this;
        }
        public GetIncomeRequest build(){
            return new GetIncomeRequest(this);
        }
    }
    public String toString(){
        return "income: " + income + " amount: " + amount;
    }
}