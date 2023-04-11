package alec.financetracker.dynamodb;


import alec.financetracker.dynamodb.objects.Expenditure;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import alec.financetracker.Exceptions.ExpenditureNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;



import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
public class ExpendituresDAO {
    public static final String CATEGORY_INDEX = "category-index";
    private final DynamoDBMapper dynamoDBMapper;


    public ExpendituresDAO(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }
    public Expenditure getExpenditure(Expenditure expenditure){
        this.dynamoDBMapper.load(expenditure);
        return expenditure;
    }

    public Expenditure saveExpenditure(Expenditure expenditure){
        this.dynamoDBMapper.save(expenditure);
        return expenditure;
    }
    public Expenditure loadExpenditure(String purchaseName, String time){
        Expenditure expenditure = this.dynamoDBMapper.load(Expenditure.class, purchaseName, time);
        if (expenditure == null) {
            throw new ExpenditureNotFoundException();
        } else{
            return expenditure;
        }
    }
    public List<Expenditure> loadByCategory(String category){
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":category", new AttributeValue().withS(category));
        DynamoDBQueryExpression<Expenditure> queryExpression = new DynamoDBQueryExpression<Expenditure>()
                .withIndexName(CATEGORY_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("category = :category")
                .withExpressionAttributeValues(valueMap);
        List<Expenditure> categoryQueryList = dynamoDBMapper.query(Expenditure.class , queryExpression);
        return categoryQueryList;
    }
    public List<Expenditure> loadByRange(String startDate, String endDate){
        Map <String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":sk1", new AttributeValue(startDate));
        expressionAttributeValues.put(":sk2", new AttributeValue(endDate));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("#time BETWEEN :sk1 and :sk2")
                .withExpressionAttributeNames(Collections.singletonMap("#time", "time"))
                .withExpressionAttributeValues(expressionAttributeValues);
        List<Expenditure> expendituresList = dynamoDBMapper.scan(Expenditure.class, scanExpression);
        return expendituresList;
    }
    //public List<Expenditure> loadByYear(String start){
        /*Map<String, AttributeValue> expressionAtrributeValues = new HashMap<>();
        expressionAtrributeValues.put(":prefix", new AttributeValue().withS(prefix));

        DynamoDBQueryExpression <Item> queryExpression = new DynamoDBQueryExpression<Item>()
                .withKeyConditionExpression("begins_with(sort-key, :prefix)")
                .withExpressionAttributeValues(expressionAtrributeValues);

        List<Expenditures> expendituresList = this.dynamoDBMapper.query(Expenditure.class, queryExpression);
        return expendituresList;

         */
    //}
}
