package alec.financetracker.activity;

import alec.financetracker.converters.ExpenditureConverter;
import alec.financetracker.dynamodb.IncomeDAO;
import alec.financetracker.dynamodb.objects.Income;
import alec.financetracker.models.results.requests.GetIncomeRequest;
import alec.financetracker.models.results.requests.GetIncomeResult;

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

public class GetIncomeActivity implements RequestHandler<GetIncomeRequest, GetIncomeResult> {
    private final IncomeDAO incomeDAO;
    private static Logger log = null;


    public GetIncomeActivity(IncomeDAO incomeDAO) {
        log = Logger.getLogger(GetIncomeActivity.class.getName());
        Regions region = Regions.US_EAST_1;
        AmazonDynamoDB client = (AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance()))
                .withRegion(region)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        this.incomeDAO = new IncomeDAO(mapper);
    }

    public GetIncomeResult handleRequest(GetIncomeRequest getIncomeRequest, Context context) {
        log.info("Recived GetExpenditureRequest inside activity { " + getIncomeRequest.toString() + "}");
        Income income = new Income();
        income.setIncome(getIncomeRequest.getIncome());
        Income result = new Income();
        result = incomeDAO.getIncome(income);
        log.info("Result: " + result.toString());
        return GetIncomeResult.builder()
                .withIncome(result.getIncome())
                .withAmount(result.getAmount()).build();
    }
}