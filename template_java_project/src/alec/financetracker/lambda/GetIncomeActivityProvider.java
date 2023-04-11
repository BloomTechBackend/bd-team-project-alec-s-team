package alec.financetracker.lambda;

import alec.financetracker.activity.GetIncomeActivity;
import alec.financetracker.dynamodb.IncomeDAO;
import alec.financetracker.models.results.requests.GetIncomeRequest;
import alec.financetracker.models.results.requests.GetIncomeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class GetIncomeActivityProvider implements RequestHandler<GetIncomeRequest, GetIncomeResult>{
    private static Logger log = null;
    private IncomeDAO incomeDAO;

    public GetIncomeActivityProvider(){
        log = Logger.getLogger(GetIncomeActivityProvider.class.getName());
    }

    @Override
    public GetIncomeResult handleRequest(final GetIncomeRequest getIncomeRequest, Context context){
        log.info("Recived GetExpenditureRequest{ " + getIncomeRequest.toString() + "}");
        return getIncomeActivityObject().handleRequest(getIncomeRequest, context);
    }
    private GetIncomeActivity getIncomeActivityObject(){
        GetIncomeActivity getIncomeActivity = new GetIncomeActivity(incomeDAO);
        return getIncomeActivity;
    }


}