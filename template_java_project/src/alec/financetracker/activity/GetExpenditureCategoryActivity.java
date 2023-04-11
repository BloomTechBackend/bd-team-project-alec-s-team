package alec.financetracker.activity;

import alec.financetracker.converters.ExpenditureConverter;
import alec.financetracker.dynamodb.ExpendituresDAO;
import alec.financetracker.dynamodb.objects.Expenditure;
import alec.financetracker.models.results.requests.GetExpenditureCategoryRequest;
import alec.financetracker.models.results.requests.GetExpenditureCategoryResult;

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


public class GetExpenditureCategoryActivity implements RequestHandler<GetExpenditureCategoryRequest, GetExpenditureCategoryResult> {
    private final ExpendituresDAO expenditureDAO;
    private static Logger log = null;

    public GetExpenditureCategoryActivity(ExpendituresDAO expenditureDAO) {
        log = Logger.getLogger(GetExpenditureCategoryActivity.class.getName());
        Regions region = Regions.US_EAST_1;
        AmazonDynamoDB client = (AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance()))
                .withRegion(region)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        this.expenditureDAO = new ExpendituresDAO(mapper);
    }

    public GetExpenditureCategoryResult handleRequest(GetExpenditureCategoryRequest getExpenditureCategoryRequest, Context context) {
        log.info("Recived GetExpenditureRequest inside activity { " + getExpenditureCategoryRequest.toString() + "}");
        String category = getExpenditureCategoryRequest.getCategory();
        List<Expenditure> expenditureList = expenditureDAO.loadByCategory(category);
        ExpenditureConverter converter = new ExpenditureConverter();
        GetExpenditureCategoryResult result = new GetExpenditureCategoryResult();
        result.setExpenditureList(expenditureList);
        //List<expenditure> = expenditureList.stream().map(s -> GetExpenditureResult.builder().withExpenditure(converter.toExpenditureModel(s)).build())
                //.collect(Collectors.toList());
        return result;
    }
}