package alec.financetracker.activity;

import alec.financetracker.converters.ExpenditureConverter;
import alec.financetracker.dynamodb.ExpendituresDAO;
import alec.financetracker.dynamodb.objects.Expenditure;
import alec.financetracker.models.results.requests.GetExpenditureRangeRequest;
import alec.financetracker.models.results.requests.GetExpenditureRangeResult;

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

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;


public class GetExpenditureRangeActivity implements RequestHandler<GetExpenditureRangeRequest, GetExpenditureRangeResult> {
    private final ExpendituresDAO expenditureDAO;
    private static Logger log = null;

    public GetExpenditureRangeActivity(ExpendituresDAO expenditureDAO) {
        log = Logger.getLogger(GetExpenditureRangeActivity.class.getName());
        Regions region = Regions.US_EAST_1;
        AmazonDynamoDB client = (AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance()))
                .withRegion(region)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        this.expenditureDAO = new ExpendituresDAO(mapper);
    }

    public GetExpenditureRangeResult handleRequest(GetExpenditureRangeRequest getExpenditureRangeRequest, Context context) {
        log.info("Recived GetExpenditureRequest inside activity { " + getExpenditureRangeRequest.toString() + "}");
        String startDate = getExpenditureRangeRequest.getStartDate();
        String endDate = getExpenditureRangeRequest.getEndDate();
        GetExpenditureRangeResult result = new GetExpenditureRangeResult();
        result.setExpenditureList(expenditureDAO.loadByRange(startDate, endDate));
        log.info("Recieved result: " + result.toString());
        return result;
    }
}