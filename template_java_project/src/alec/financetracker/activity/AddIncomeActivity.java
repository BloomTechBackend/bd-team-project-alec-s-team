package alec.financetracker.activity;

import alec.financetracker.converters.ExpenditureConverter;
import alec.financetracker.dynamodb.IncomeDAO;
import alec.financetracker.dynamodb.objects.Income;
import alec.financetracker.models.results.requests.AddIncomeRequest;
import alec.financetracker.models.results.requests.AddIncomeResult;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AddIncomeActivity implements RequestHandler<AddIncomeRequest, AddIncomeResult> {
    private final IncomeDAO incomeDAO;
    private static Logger log = null;


    public AddIncomeActivity(IncomeDAO incomeDAO) {
        log = Logger.getLogger(AddIncomeActivity.class.getName());
        Regions region = Regions.US_EAST_1;
        AmazonDynamoDB client = (AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance()))
                .withRegion(region)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        this.incomeDAO = new IncomeDAO(mapper);
    }

    public AddIncomeResult handleRequest(AddIncomeRequest addIncomeRequest, Context context) {
        log.info("Recived addIncomeRequest inside activity { " + addIncomeRequest.toString() + "}");
        Income add = new Income();
        add.setIncome(addIncomeRequest.getIncome());
        add.setAmount(addIncomeRequest.getAmount());
        Income result = incomeDAO.addIncome(add);
        log.info("Result: " + result.toString());
        return AddIncomeResult.builder()
                .withIncome(result.getIncome())
                .withAmount(result.getAmount()).build();
    }
}