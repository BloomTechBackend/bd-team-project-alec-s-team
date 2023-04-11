package alec.financetracker.models.results.requests;

import alec.financetracker.activity.CreateExpenditureActivity;

public class AddIncomeResult {

    private String income;
    private int amount;


    public AddIncomeResult(){
    }

    public AddIncomeResult(String income, int amount){
        this.income = income;
        this.amount = amount;
    }
    public AddIncomeResult(Builder builder){
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
    public static Builder builder(){
        return new Builder();
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
        public AddIncomeResult build(){
            return new AddIncomeResult(this);
        }
    }
    public String toString(){
        return "income: " + income + " amount: " + amount;
    }
}