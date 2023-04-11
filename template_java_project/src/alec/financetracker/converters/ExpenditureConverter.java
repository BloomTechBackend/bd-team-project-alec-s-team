package alec.financetracker.converters;

import alec.financetracker.dynamodb.objects.Expenditure;
import alec.financetracker.models.ExpenditureModel;

public class ExpenditureConverter {
    public ExpenditureModel toExpenditureModel(Expenditure expenditure){
        return ExpenditureModel.builder().withPurchaseName(expenditure.getPurchaseName())
                .withTime(expenditure.getTime()).withCategory(expenditure.getCategory())
                .withCost(expenditure.getCost()).build();
    }
}
