package alec.financetracker.activity;

import alec.financetracker.converters.ExpenditureConverter;
import alec.financetracker.dynamodb.ExpendituresDAO;
import alec.financetracker.dynamodb.IncomeDAO;
import alec.financetracker.dynamodb.objects.Expenditure;
import alec.financetracker.dynamodb.objects.Income;
import alec.financetracker.models.results.requests.CreateExpenditureRequest;
import alec.financetracker.models.results.requests.CreateExpenditureResult;
import alec.financetracker.models.util.ValidateData;
import alec.financetracker.Exceptions.InvalidAttributeException;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CreateExpenditureActivity implements RequestHandler<CreateExpenditureRequest, CreateExpenditureResult> {
    private final ExpendituresDAO expenditureDAO;
    private final IncomeDAO incomeDAO;
    private static Logger log = null;
    public CreateExpenditureActivity(ExpendituresDAO expenditureDAO) {
        log = Logger.getLogger(CreateExpenditureActivity.class.getName());
        Regions region = Regions.US_EAST_1;
        AmazonDynamoDB client = (AmazonDynamoDB)((AmazonDynamoDBClientBuilder)AmazonDynamoDBClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance()))
                .withRegion(region)
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        this.expenditureDAO = new ExpendituresDAO(mapper);
        this.incomeDAO = new IncomeDAO(mapper);
    }
    public CreateExpenditureResult handleRequest(CreateExpenditureRequest createExpenditureRequest, Context context){
        log.info("Recived CreateExpenditureRequest inside activity { " + createExpenditureRequest.toString() + "}");
        if (!(ValidateData.isValidDate(createExpenditureRequest.getTime()))){
            throw new InvalidAttributeException();
        }
        if(!(ValidateData.isNumericallyValidDate(createExpenditureRequest.getTime()))){
            throw new InvalidAttributeException();
        }
        Expenditure expenditure = new Expenditure();
        expenditure.setPurchaseName(createExpenditureRequest.getpurchaseName());
        expenditure.setCategory(createExpenditureRequest.getCategory());
        expenditure.setCost(createExpenditureRequest.getCost());
        expenditure.setTime(createExpenditureRequest.getTime());
        log.info("expenditure object { " + expenditure.toString() + "}");
        expenditureDAO.saveExpenditure(expenditure);
        ExpenditureConverter converter = new ExpenditureConverter();
        int toDeduct = createExpenditureRequest.getCost() * -1;
        Income income = new Income();
        income.setIncome("income");
        income.setAmount(toDeduct);
        incomeDAO.addIncome(income);
        return CreateExpenditureResult.builder().withExpenditure(converter.toExpenditureModel(expenditure)).build();
    }
}
