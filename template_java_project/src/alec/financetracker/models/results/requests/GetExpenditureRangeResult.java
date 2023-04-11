package alec.financetracker.models.results.requests;

import alec.financetracker.dynamodb.objects.Expenditure;
import java.util.List;

public class GetExpenditureRangeResult {

    private List <Expenditure> expenditureList;


    public GetExpenditureRangeResult(){
    }

    public GetExpenditureRangeResult(List<Expenditure> expenditureList){
        this.expenditureList = expenditureList;
    }
    public GetExpenditureRangeResult(Builder builder){
        this.expenditureList = expenditureList;
    }

    public void setExpenditureList(List<Expenditure> expenditureList){
        this.expenditureList = expenditureList;
    }
    public List<Expenditure> getExpenditureList(){
        return expenditureList;
    }
    public static final class Builder{
        List<Expenditure> expenditureList;
        private Builder(){

        }
        public Builder wtihExpenditureList(List <Expenditure> expenditureList){
            this.expenditureList = expenditureList;
            return this;
        }
        public GetExpenditureRangeResult build(){
            return new GetExpenditureRangeResult(this);
        }
    }
    public String toString(){
        String val = "";
        for( Expenditure expenditure : expenditureList){
            val += expenditure + ", ";
        }
        return val;
    }
}