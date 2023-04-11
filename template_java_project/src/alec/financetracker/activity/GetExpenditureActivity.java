package alec.financetracker.activity;

import alec.financetracker.converters.ExpenditureConverter;
import alec.financetracker.dynamodb.ExpendituresDAO;
import alec.financetracker.dynamodb.objects.Expenditure;
import alec.financetracker.models.results.requests.GetExpenditureRequest;
import alec.financetracker.models.results.requests.GetExpenditureResult;

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

public class GetExpenditureActivity implements RequestHandler<GetExpenditureRequest, GetExpenditureResult> {
    private final ExpendituresDAO expenditureDAO;
    private static Logger log = null;

    public GetExpenditureActivity(ExpendituresDAO expenditureDAO) {
        log = Logger.getLogger(GetExpenditureActivity.class.getName());
        Regions region = Regions.US_EAST_1;
        AmazonDynamoDB client = (AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance()))
                .withRegion(region)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        this.expenditureDAO = new ExpendituresDAO(mapper);
    }

    public GetExpenditureResult handleRequest(GetExpenditureRequest getExpenditureRequest, Context context) {
        log.info("Recived GetExpenditureRequest inside activity { " + getExpenditureRequest.toString() + "}");
        String purchaseName = getExpenditureRequest.getPurchaseName();
        String time = getExpenditureRequest.getTime();
        Expenditure expenditure = expenditureDAO.loadExpenditure(purchaseName, time);
        log.info("expenditure object { " + expenditure.toString() + "}");
        ExpenditureConverter converter = new ExpenditureConverter();
        GetExpenditureResult result = GetExpenditureResult.builder().withExpenditure(converter.toExpenditureModel(expenditure)).build();
        log.info("Result: " + result.toString());
        return result;
    }
}