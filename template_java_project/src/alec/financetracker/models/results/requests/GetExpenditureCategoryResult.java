package alec.financetracker.models.results.requests;

import alec.financetracker.activity.CreateExpenditureActivity;
import alec.financetracker.dynamodb.objects.*;
import java.util.List;

public class GetExpenditureCategoryResult {

    private List<Expenditure> expenditureList;


    public GetExpenditureCategoryResult(){
    }

    public GetExpenditureCategoryResult(List<Expenditure> expenditureList){
        this.expenditureList = expenditureList;
    }
    public GetExpenditureCategoryResult(Builder builder){
        this.expenditureList = builder.expenditureList;
    }

    public void setExpenditureList(List<Expenditure> expenditureList) {
        this.expenditureList = expenditureList;
    }
    public List<Expenditure> getExpenditureList(){
        return expenditureList;
    }
    public static final class Builder{
        List<Expenditure> expenditureList;
        private Builder(){

        }
        public Builder wtihExpenditureList(List<Expenditure> expenditureList){
            this.expenditureList = expenditureList;
            return this;
        }
        public GetExpenditureCategoryResult build(){
            return new GetExpenditureCategoryResult(this);
        }
    }
}