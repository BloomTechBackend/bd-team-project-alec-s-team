package alec.financetracker.lambda;

import alec.financetracker.activity.AddIncomeActivity;
import alec.financetracker.dynamodb.IncomeDAO;
import alec.financetracker.models.results.requests.AddIncomeRequest;
import alec.financetracker.models.results.requests.AddIncomeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class AddIncomeActivityProvider implements RequestHandler<AddIncomeRequest, AddIncomeResult>{
    private static Logger log = null;
    private IncomeDAO incomeDAO;

    public AddIncomeActivityProvider(){
        log = Logger.getLogger(AddIncomeActivityProvider.class.getName());
    }

    @Override
    public AddIncomeResult handleRequest(final AddIncomeRequest addIncomeRequest, Context context){
        log.info("Recived GetExpenditureRequest{ " + addIncomeRequest.toString() + "}");
        return addIncomeActivityObject().handleRequest(addIncomeRequest, context);
    }
    private AddIncomeActivity addIncomeActivityObject(){
        AddIncomeActivity addIncomeActivity = new AddIncomeActivity(incomeDAO);
        return addIncomeActivity;
    }


}