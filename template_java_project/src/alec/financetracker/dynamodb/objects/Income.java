package alec.financetracker.dynamodb.objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;




@DynamoDBTable(tableName = "Income")
public class Income {
    private String income;
    private int amount;
    @DynamoDBHashKey(attributeName = "income")
    public String getIncome(){
        return income;
    }
    @DynamoDBAttribute(attributeName = "amount")
    public int getAmount(){
        return amount;
    }
    public void setIncome(String income){
        this.income = income;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public String toString(){
        return "income: " + income + " amount: " + String.valueOf(amount);
    }
}
