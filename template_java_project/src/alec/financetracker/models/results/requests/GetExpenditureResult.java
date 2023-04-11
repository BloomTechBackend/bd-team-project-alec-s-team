package alec.financetracker.models.results.requests;

import alec.financetracker.activity.GetExpenditureActivity;
import alec.financetracker.dynamodb.objects.Expenditure;
import alec.financetracker.models.ExpenditureModel;

public class GetExpenditureResult {
    private ExpenditureModel expenditure;

    public GetExpenditureResult(Builder builder){
        this.expenditure = builder.expenditure;
    }
    public ExpenditureModel getExpenditure(){
        return expenditure;
    }
    public static Builder builder(){
        return new Builder();
    }
    public static final class Builder {
        private ExpenditureModel expenditure;

        public Builder withExpenditure(ExpenditureModel expenditure){
            this.expenditure = expenditure;
            return this;
        }
        public GetExpenditureResult build(){
            return new GetExpenditureResult(this);
        }
    }
    public String toString(){
        return expenditure.toString();
    }
}
