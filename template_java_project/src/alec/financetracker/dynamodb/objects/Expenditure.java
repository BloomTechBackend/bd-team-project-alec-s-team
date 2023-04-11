package alec.financetracker.dynamodb.objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;




@DynamoDBTable(tableName = "expenditures")
public class Expenditure {
    public static final String CATEGORY_INDEX = "category-index";

    private String purchaseName;
    private String time;
    private String category;
    private int cost;
    @DynamoDBHashKey(attributeName = "purchaseName")
    public String getPurchaseName(){
        return purchaseName;
    }
    @DynamoDBRangeKey(attributeName = "time")
    public String getTime(){
        return time;
    }
    @DynamoDBIndexHashKey(globalSecondaryIndexName = CATEGORY_INDEX, attributeName = "category")
    public String getCategory(){
        return category;
    }
    @DynamoDBAttribute
    public int getCost(){
        return cost;
    }
    public void setPurchaseName(String name){
        this.purchaseName = name;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
    public String toString(){
        return "purchaseName: " + purchaseName + " time: " + time +
                " category: " + category + " cost " + String.valueOf(cost);

    }
}
