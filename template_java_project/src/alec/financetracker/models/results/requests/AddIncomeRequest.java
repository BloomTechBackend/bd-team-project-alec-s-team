package alec.financetracker.models.results.requests;

import alec.financetracker.activity.CreateExpenditureActivity;

public class AddIncomeRequest {

    private String income;
    private int amount;


    public AddIncomeRequest(){
    }

    public AddIncomeRequest(String income, int amount){
        this.income = income;
        this.amount = amount;
    }
    public AddIncomeRequest(Builder builder){
        this.income = builder.income;
        this.amount = builder.amount;
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
        public Builder wtihIncome(String income){
            this.income = income;
            return this;
        }
        public Builder withAmount(int amount){
            this.amount = amount;
            return this;
        }
        public AddIncomeRequest build(){
            return new AddIncomeRequest(this);
        }
    }
    public String toString(){
        return "income: " + income + " amount: " + String.valueOf(amount);
    }
}