package alec.financetracker.models.results.requests;

import alec.financetracker.activity.CreateExpenditureActivity;

public class GetExpenditureCategoryRequest {

    private String category;


    public GetExpenditureCategoryRequest(){
    }

    public GetExpenditureCategoryRequest(String category){
        this.category = category;
    }
    public GetExpenditureCategoryRequest(Builder builder){
        this.category = builder.category;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public static final class Builder{
        String category;
        private Builder(){

        }
        public Builder withCategory(String category){
            this.category = category;
            return this;
        }
        public GetExpenditureCategoryRequest build(){
            return new GetExpenditureCategoryRequest(this);
        }
    }
    public String toString(){
        return "category: " + category;
    }
}