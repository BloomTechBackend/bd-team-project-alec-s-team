package alec.financetracker.models.results.requests;

import alec.financetracker.activity.CreateExpenditureActivity;
import alec.financetracker.dynamodb.objects.Expenditure;
import alec.financetracker.models.ExpenditureModel;

public class CreateExpenditureResult {
    private ExpenditureModel expenditure;

    public CreateExpenditureResult(Builder builder){
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
        public CreateExpenditureResult build(){
            return new CreateExpenditureResult(this);
        }
        }
}
