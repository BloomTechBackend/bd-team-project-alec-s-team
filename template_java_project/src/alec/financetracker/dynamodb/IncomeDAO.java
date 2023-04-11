package alec.financetracker.dynamodb;


import alec.financetracker.dynamodb.objects.Income;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import alec.financetracker.Exceptions.ExpenditureNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;



import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
public class IncomeDAO {
    private final DynamoDBMapper dynamoDBMapper;


    public IncomeDAO(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }
    public Income getIncome(Income income){
        String id = income.getIncome();
        income = this.dynamoDBMapper.load(Income.class, id);
        return income;
    }

    public Income addIncome(Income income){
        int total = 0;
        Income past = getIncome(income);
        System.out.println("INSIDE INCOMEDAO : " + past.toString());
        if (past == null){
            total = income.getAmount();
        }else {
            total = past.getAmount() + income.getAmount();
        }
        System.out.println("this is total: " + String.valueOf(total));
        income.setAmount(total);
        System.out.println("Value of object afterwards: " + income.toString());
        this.dynamoDBMapper.save(income);
        return income;
    }

}
